package br.com.richer.torcedor.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table
@Data
public class TimeCoracaoEntity {


    public TimeCoracaoEntity() {

    }

    public TimeCoracaoEntity(String nomeTimeCoracao) {
        this.nomeTimeCoracao = nomeTimeCoracao;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomeTimeCoracao;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TimeCoracaoEntity that = (TimeCoracaoEntity) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
