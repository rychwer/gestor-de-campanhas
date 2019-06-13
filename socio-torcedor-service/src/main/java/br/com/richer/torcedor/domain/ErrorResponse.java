package br.com.richer.torcedor.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
@AllArgsConstructor
public class ErrorResponse {

    private String mensagem;

    private Object objetoRetorno;

    public ErrorResponse(String mensagem) {
        this.mensagem = mensagem;
    }


}
