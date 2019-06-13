package br.com.richer.torcedor.controller.exception;

public class TorcedorCadastradoException extends RuntimeException {

    private static final long serialVersionUID = -720932500856275263L;

    public TorcedorCadastradoException(String mensagem) {
        super(mensagem);
    }
}
