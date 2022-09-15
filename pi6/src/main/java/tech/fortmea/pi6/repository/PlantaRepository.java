package tech.fortmea.pi6.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tech.fortmea.pi6.model.Planta;

@Repository
public interface PlantaRepository extends JpaRepository<Planta, Long>{
    List<Planta> findDistinctByNomeCientificoContaining(String nomeCientifico);
    
    @Query("SELECT p FROM Planta p WHERE (LOWER(:Pnome)) MEMBER p.nome")
    List<Planta> findByNomeIn(@Param("Pnome") String nome);
    
}
