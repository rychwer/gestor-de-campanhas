package br.com.richer.torcedor.service;

import br.com.richer.torcedor.domain.Torcedor;
import org.springframework.stereotype.Service;

@Service
public interface TorcedorService {

    public Torcedor criarTorcedor(Torcedor torcedor);

}
