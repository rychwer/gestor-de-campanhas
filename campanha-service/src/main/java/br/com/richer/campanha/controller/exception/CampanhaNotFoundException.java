package br.com.richer.campanha.controller.exception;

public class CampanhaNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 6538710215598916268L;

    public CampanhaNotFoundException(String mensagem) {
        super(mensagem);
    }
}
