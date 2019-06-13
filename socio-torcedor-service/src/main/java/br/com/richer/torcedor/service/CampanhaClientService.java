package br.com.richer.torcedor.service;

import br.com.richer.torcedor.domain.Campanha;
import br.com.richer.torcedor.service.fallback.CampanhaFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "campanha-service", url = "http://localhost:8080", fallback = CampanhaFallback.class)
public interface CampanhaClientService {

    @GetMapping("/campanha/time/{idTimeCoracao}")
    List<Campanha> listaCampanhasAtivasPorTimeCoracao(@PathVariable("idTimeCoracao") Long idTimeCoracao);

}
