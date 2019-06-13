package br.com.richer.torcedor.service;

import br.com.richer.torcedor.domain.Campanha;
import br.com.richer.torcedor.domain.Torcedor;
import br.com.richer.torcedor.entity.CampanhaEntity;
import br.com.richer.torcedor.entity.TorcedorEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ConversorService {

    Torcedor convertTorcedorEntityToTorcedor(TorcedorEntity torcedorEntity);

    TorcedorEntity convertTorcedorToTorcedorEntity(Torcedor torcedor);

    CampanhaEntity convertCampanhaToCampanhaEntity(Campanha campanha);

    List<Campanha> convertListCampanhaEntityToListCampanha(List<CampanhaEntity> campanhaEntityList);

    Campanha convertCampanhaEntityToCampanha(CampanhaEntity entity);

}
