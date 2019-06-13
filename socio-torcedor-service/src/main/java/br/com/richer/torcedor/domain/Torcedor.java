package br.com.richer.torcedor.domain;

import br.com.richer.torcedor.config.LocalDateDeserializer;
import br.com.richer.torcedor.config.LocalDateSerializer;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Data
public class Torcedor {

    @JsonProperty("nome_completo")
    @NotNull
    private String nomeCompleto;

    @NotNull
    private String email;

    @NotNull
    @JsonProperty("data_nascimento")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate dataNascimento;

    @NotNull
    @JsonProperty("time_coracao")
    private String timeCoracao;

    private List<Campanha> campanhas;

}
