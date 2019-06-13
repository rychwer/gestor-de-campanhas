package br.com.richer.campanha.domain;

import br.com.richer.campanha.config.LocalDateDeserializer;
import br.com.richer.campanha.config.LocalDateSerializer;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
public class Campanha {

    @NotNull
    @JsonProperty("nome_campanha")
    private String nomeCampanha;

    @NotNull
    @JsonProperty("id_time_coracao")
    private Long idTimeCoracao;

    @NotNull
    @JsonProperty("data_vigencia")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate dataVigencia;

}
