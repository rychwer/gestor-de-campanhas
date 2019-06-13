package br.com.richer.torcedor.controller.exception;

import lombok.Getter;

@Getter
public class ServicoIndisponivelException extends RuntimeException {

    private static final long serialVersionUID = -821081187115534867L;

    private Object objetoRetorno;

    public ServicoIndisponivelException(String mensagem) {
        super(mensagem);
    }

    public ServicoIndisponivelException(String mensagem, Object objeto) {
        super(mensagem);
        this.objetoRetorno = objeto;
    }
}
