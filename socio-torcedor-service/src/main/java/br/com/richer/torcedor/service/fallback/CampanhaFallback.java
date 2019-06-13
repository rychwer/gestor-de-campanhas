package br.com.richer.torcedor.service.fallback;

import br.com.richer.torcedor.domain.Campanha;
import br.com.richer.torcedor.service.CampanhaClientService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CampanhaFallback implements CampanhaClientService {

    @Override
    public List<Campanha> listaCampanhasAtivasPorTimeCoracao(Long idTimeCoracao) {
        return new ArrayList<Campanha>();
    }
}
