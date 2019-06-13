package br.com.richer.campanha.service.impl;

import br.com.richer.campanha.domain.Campanha;
import br.com.richer.campanha.entity.CampanhaEntity;
import br.com.richer.campanha.service.ConversorService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ConversorServiceImpl implements ConversorService {

    @Override
    public CampanhaEntity convertCampanhaToCampanhaEntity(Campanha campanha) {
        CampanhaEntity entity = new CampanhaEntity();

        entity.setDataVigencia(campanha.getDataVigencia());
        entity.setIdTimeCoracao(campanha.getIdTimeCoracao());
        entity.setNomeCampanha(campanha.getNomeCampanha());

        return entity;
    }

    @Override
    public Campanha convertCampanhaEntityToCampanha(CampanhaEntity entity) {
       Campanha campanha = new Campanha();
       campanha.setDataVigencia(entity.getDataVigencia());
       campanha.setIdTimeCoracao(entity.getIdTimeCoracao());
       campanha.setNomeCampanha(entity.getNomeCampanha());

       return campanha;
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
}
