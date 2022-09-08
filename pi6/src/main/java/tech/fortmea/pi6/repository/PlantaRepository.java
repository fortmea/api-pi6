package tech.fortmea.pi6.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tech.fortmea.pi6.model.Planta;

@Repository
public interface PlantaRepository extends JpaRepository<Planta, Long>{
    List<Planta> findByNomeContainingOrNomeCientificoContaining(String nome, String nomeCientifico);
}
