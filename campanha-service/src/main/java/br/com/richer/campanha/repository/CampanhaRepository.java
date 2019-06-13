package br.com.richer.campanha.repository;

import br.com.richer.campanha.entity.CampanhaEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CampanhaRepository extends CrudRepository<CampanhaEntity, Long> {

    void deleteById(Long idCampanha);

    List<CampanhaEntity> findAllByIdTimeCoracao(Long idTimeCoracao);

    @Query("SELECT c FROM CampanhaEntity c WHERE c.idTimeCoracao = :idTimeCoracao and c.dataVigencia >= :hoje ORDER BY c.dataVigencia ASC")
    List<CampanhaEntity> findAllCampanhasAtivasPorTimeCoracao(@Param("idTimeCoracao") Long idTimeCoracao, @Param("hoje") LocalDate hoje);

    @Query("SELECT c FROM CampanhaEntity c WHERE c.dataVigencia >= :hoje ORDER BY c.dataVigencia ASC")
    List<CampanhaEntity> findAllCampanhasAtivas(@Param("hoje") LocalDate hoje);

}
