package br.com.richer.campanha.service;

import br.com.richer.campanha.domain.Campanha;
import br.com.richer.campanha.entity.CampanhaEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ConversorService {

    CampanhaEntity convertCampanhaToCampanhaEntity(Campanha campanha);

    Campanha convertCampanhaEntityToCampanha(CampanhaEntity entity);

    List<Campanha> convertListCampanhaEntityToListCampanha(List<CampanhaEntity> campanhaEntityList);

}
