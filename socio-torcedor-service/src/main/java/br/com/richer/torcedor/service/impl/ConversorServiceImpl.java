package br.com.richer.torcedor.service.impl;

import br.com.richer.torcedor.domain.Campanha;
import br.com.richer.torcedor.domain.Torcedor;
import br.com.richer.torcedor.entity.CampanhaEntity;
import br.com.richer.torcedor.entity.TimeCoracaoEntity;
import br.com.richer.torcedor.entity.TorcedorEntity;
import br.com.richer.torcedor.service.ConversorService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ConversorServiceImpl implements ConversorService {

    @Override
    public Torcedor convertTorcedorEntityToTorcedor(TorcedorEntity torcedorEntity) {

        Torcedor torcedor = new Torcedor();
        torcedor.setDataNascimento(torcedorEntity.getDataNascimento());
        torcedor.setEmail(torcedorEntity.getEmail());
        torcedor.setNomeCompleto(torcedorEntity.getNomeCompleto());
        torcedor.setTimeCoracao(torcedorEntity.getTimeCoracaoEntity().getNomeTimeCoracao());
        torcedor.setCampanhas(convertListCampanhaEntityToListCampanha(torcedorEntity.getCampanhas()));

        return torcedor;
    }

    @Override
    public TorcedorEntity convertTorcedorToTorcedorEntity(Torcedor torcedor) {
        TorcedorEntity torcedorEntity = new TorcedorEntity();

        torcedorEntity.setDataNascimento(torcedor.getDataNascimento());
        torcedorEntity.setEmail(torcedor.getEmail());
        torcedorEntity.setNomeCompleto(torcedor.getNomeCompleto());

        TimeCoracaoEntity timeCoracaoEntity = new TimeCoracaoEntity();
        timeCoracaoEntity.setNomeTimeCoracao(torcedor.getTimeCoracao());

        torcedorEntity.setTimeCoracaoEntity(timeCoracaoEntity);

        return torcedorEntity;
    }

    @Override
    public CampanhaEntity convertCampanhaToCampanhaEntity(Campanha campanha) {
        CampanhaEntity entity = new CampanhaEntity();

        entity.setDataVigencia(campanha.getDataVigencia());
        entity.setIdTimeCoracao(campanha.getIdTimeCoracao());
        entity.setNomeCampanha(campanha.getNomeCampanha());

        return entity;
    }

    @Override
    public List<Campanha> convertListCampanhaEntityToListCampanha(List<CampanhaEntity> campanhaEntityList) {
        List<Campanha> campanhas = new ArrayList<>();
        campanhaEntityList.forEach(item -> {
            final Campanha campanha = convertCampanhaEntityToCampanha(item);
            campanhas.add(campanha);
        });
        return campanhas;
    }

    @Override
    public Campanha convertCampanhaEntityToCampanha(CampanhaEntity entity) {
        Campanha campanha = new Campanha();
        campanha.setDataVigencia(entity.getDataVigencia());
        campanha.setIdTimeCoracao(entity.getIdTimeCoracao());
        campanha.setNomeCampanha(entity.getNomeCampanha());

        return campanha;
    }

}
