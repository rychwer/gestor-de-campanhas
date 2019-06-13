package br.com.richer.campanha.service;

import br.com.richer.campanha.domain.Campanha;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CampanhaService {

    void criarCampanha(Campanha campanha);

    Campanha alterarCampanha(Long idCampanha, Campanha campanha);

    void deletarCampanha(Long idCampanha);

    Campanha consultarCampanha(Long idCampanha);

    List<Campanha> listarCampanhasAtivas();

    List<Campanha> listarCampanhasAtivasPorTimeCoracao(Long idTimeCoracao);

}
