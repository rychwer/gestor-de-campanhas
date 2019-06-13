package br.com.richer.campanha.controller.exception;

public class CampanhaDataInformadaInvalidaException extends RuntimeException {

    private static final long serialVersionUID = 2952288284516037834L;

    public CampanhaDataInformadaInvalidaException(String mensagem) {
        super(mensagem);
    }
}
