package br.com.richer.torcedor.repository;

import br.com.richer.torcedor.entity.TorcedorEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TorcedorRepository extends CrudRepository<TorcedorEntity, Long> {

    Optional<TorcedorEntity> findAllByEmail(@Param("email") String email);

}
