package br.com.richer.torcedor.service.impl;

import br.com.richer.torcedor.controller.exception.TorcedorCadastradoException;
import br.com.richer.torcedor.domain.Campanha;
import br.com.richer.torcedor.domain.Torcedor;
import br.com.richer.torcedor.entity.CampanhaEntity;
import br.com.richer.torcedor.entity.TimeCoracaoEntity;
import br.com.richer.torcedor.entity.TorcedorEntity;
import br.com.richer.torcedor.repository.TorcedorRepository;
import br.com.richer.torcedor.service.CampanhaClientService;
import br.com.richer.torcedor.service.ConversorService;
import br.com.richer.torcedor.service.TorcedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TorcedorServiceImpl implements TorcedorService {

    private TorcedorRepository torcedorRepository;
    private ConversorService conversorService;
    private CampanhaClientService campanhaClientService;

    @Autowired
    public TorcedorServiceImpl(TorcedorRepository torcedorRepository, ConversorService conversorService, CampanhaClientService campanhaClientService) {
        this.torcedorRepository = torcedorRepository;
        this.conversorService = conversorService;
        this.campanhaClientService = campanhaClientService;
    }

    @Override
    @Transactional
    public Torcedor criarTorcedor(Torcedor torcedor) {
        final Optional<TorcedorEntity> optionalTorcedorEntity = torcedorRepository.findAllByEmail(torcedor.getEmail());
        TimeCoracaoEntity timeCoracaoEntity = null;
        if (!optionalTorcedorEntity.isPresent()) {
            TorcedorEntity torcedorEntity = conversorService.convertTorcedorToTorcedorEntity(torcedor);
            TorcedorEntity torcedorNovo = torcedorRepository.save(torcedorEntity);
            final List<Campanha> campanhas = campanhaClientService.listaCampanhasAtivasPorTimeCoracao(torcedorNovo.getTimeCoracaoEntity().getId());
            List<CampanhaEntity> campanhaEntities = new ArrayList<>();
            campanhas.forEach(campanha -> {
                campanhaEntities.add(conversorService.convertCampanhaToCampanhaEntity(campanha));
            });
            torcedorNovo.setCampanhas(campanhaEntities);
            final TorcedorEntity torcedorNovoComCampanhas = torcedorRepository.save(torcedorNovo);
            return conversorService.convertTorcedorEntityToTorcedor(torcedorNovoComCampanhas);
        } else if (optionalTorcedorEntity.isPresent() && optionalTorcedorEntity.get().getCampanhas().size() == 0) {
            final TorcedorEntity torcedorEntity = optionalTorcedorEntity.get();
            final List<Campanha> campanhasAtivas = campanhaClientService.listaCampanhasAtivasPorTimeCoracao(torcedorEntity.getTimeCoracaoEntity().getId());

            campanhasAtivas.forEach(campanhaAtiva -> {
                torcedorEntity.getCampanhas().add(conversorService.convertCampanhaToCampanhaEntity(campanhaAtiva));
            });

            final TorcedorEntity torcedorEntitySalvo = torcedorRepository.save(torcedorEntity);
            return conversorService.convertTorcedorEntityToTorcedor(torcedorEntitySalvo);
        } else {
            throw new TorcedorCadastradoException("Torcedor já está cadastrado na base de dados");
        }

    }

}
