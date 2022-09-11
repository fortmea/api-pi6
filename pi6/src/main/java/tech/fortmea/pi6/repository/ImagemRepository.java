package tech.fortmea.pi6.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tech.fortmea.pi6.model.Imagem;

@Repository
public interface ImagemRepository extends JpaRepository<Imagem, Long>{
    List<Imagem> findAllByPlantaId(Long id);
}
