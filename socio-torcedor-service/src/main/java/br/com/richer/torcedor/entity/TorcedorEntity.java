package br.com.richer.torcedor.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "torcedor")
@Data
public class TorcedorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomeCompleto;

    private String email;

    private LocalDate dataNascimento;

    @OneToOne(cascade = CascadeType.ALL)
    private TimeCoracaoEntity timeCoracaoEntity;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name="torcedor_has_campanha", joinColumns=
            {@JoinColumn(name="torcedor_id")}, inverseJoinColumns=
            {@JoinColumn(name="campanha_id")})
    private List<CampanhaEntity> campanhas;

    public TorcedorEntity() {
        this.campanhas = new ArrayList<>();
        this.timeCoracaoEntity = new TimeCoracaoEntity();
    }

}
