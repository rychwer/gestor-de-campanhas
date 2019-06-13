package br.com.richer.campanha.service.impl;

import br.com.richer.campanha.controller.exception.CampanhaDataInformadaInvalidaException;
import br.com.richer.campanha.controller.exception.CampanhaNotFoundException;
import br.com.richer.campanha.domain.Campanha;
import br.com.richer.campanha.entity.CampanhaEntity;
import br.com.richer.campanha.queue.CampanhaQueueSender;
import br.com.richer.campanha.repository.CampanhaRepository;
import br.com.richer.campanha.service.CampanhaService;
import br.com.richer.campanha.service.ConversorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class CampanhaServiceImpl implements CampanhaService {

    private CampanhaRepository campanhaRepository;

    private ConversorService conversorService;

    private CampanhaQueueSender campanhaQueueSender;

    @Autowired
    public CampanhaServiceImpl(CampanhaRepository campanhaRepository, ConversorService conversorService, CampanhaQueueSender campanhaQueueSender) {
        this.campanhaRepository = campanhaRepository;
        this.conversorService = conversorService;
        this.campanhaQueueSender = campanhaQueueSender;
    }

    @Override
    public void criarCampanha(Campanha campanha) {

        if (campanha.getDataVigencia().compareTo(LocalDate.now()) < 0) {
            throw new CampanhaDataInformadaInvalidaException("A data informada é menor que a data atual");
        }

        CampanhaEntity campanhaEntity = conversorService.convertCampanhaToCampanhaEntity(campanha);
        List<CampanhaEntity> listCampanhaEntity = campanhaRepository.findAllCampanhasAtivas(LocalDate.now());

        for (int i = 0; i < listCampanhaEntity.size(); i++) {
            final CampanhaEntity campanhaEntity1 = listCampanhaEntity.get(i);
            campanhaEntity1.setDataVigencia(campanhaEntity1.getDataVigencia().plusDays(1));

            int x = 0;
            while (x < listCampanhaEntity.size()) {
                if(x == i) {
                    x++;
                    continue;
                }
                final CampanhaEntity campanhaEntity2 = listCampanhaEntity.get(x);
                if (campanhaEntity1.getDataVigencia().compareTo(campanhaEntity2.getDataVigencia()) == 0 || campanhaEntity1.getDataVigencia().compareTo(campanhaEntity.getDataVigencia()) == 0) {
                    campanhaEntity1.setDataVigencia(campanhaEntity1.getDataVigencia().plusDays(1));
                    campanhaQueueSender.send(campanhaEntity1);
                    x = 0;
                } else {
                    x++;
                }
            }

        }

        listCampanhaEntity.add(campanhaEntity);
        campanhaRepository.saveAll(listCampanhaEntity);
    }

    @Override
    public Campanha alterarCampanha(Long idCampanha, Campanha campanha) {

        if (campanha.getDataVigencia().compareTo(LocalDate.now()) < 0) {
            throw new CampanhaDataInformadaInvalidaException("A data informada é menor que a data atual");
        }

        final Optional<CampanhaEntity> optionalCampanhaEntity = campanhaRepository.findById(idCampanha);

        if (optionalCampanhaEntity.isPresent()) {
            final CampanhaEntity campanhaEntity = optionalCampanhaEntity.get();
            campanhaEntity.setNomeCampanha(campanha.getNomeCampanha());
            campanhaEntity.setDataVigencia(campanha.getDataVigencia());
            campanhaEntity.setIdTimeCoracao(campanha.getIdTimeCoracao());
            return conversorService.convertCampanhaEntityToCampanha(campanhaRepository.save(campanhaEntity));
        }

        throw new CampanhaNotFoundException("Campanha informada não existe e por isso não pode ser alterada.");
    }

    @Override
    @Transactional
    public void deletarCampanha(Long idCampanha) {
        campanhaRepository.deleteById(idCampanha);
    }

    @Override
    public Campanha consultarCampanha(Long idCampanha) {
        final Optional<CampanhaEntity> optionalCampanhaEntity = campanhaRepository.findById(idCampanha);

        if (optionalCampanhaEntity.isPresent()) {
            return conversorService.convertCampanhaEntityToCampanha(optionalCampanhaEntity.get());
        }

        throw new CampanhaNotFoundException("A campanha informada não foi encontrada.");
    }

    @Override
    public List<Campanha> listarCampanhasAtivas() {
        return conversorService.convertListCampanhaEntityToListCampanha(campanhaRepository.findAllCampanhasAtivas(LocalDate.now()));
    }

    @Override
    public List<Campanha> listarCampanhasAtivasPorTimeCoracao(Long idTimeCoracao) {
        return conversorService.convertListCampanhaEntityToListCampanha(campanhaRepository.findAllCampanhasAtivasPorTimeCoracao(idTimeCoracao, LocalDate.now()));
    }

}
