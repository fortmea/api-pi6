package tech.fortmea.pi6.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tech.fortmea.pi6.model.ImageReqDTO;
import tech.fortmea.pi6.model.Imagem;
import tech.fortmea.pi6.model.Planta;
import tech.fortmea.pi6.model.ReqDTO;
import tech.fortmea.pi6.repository.ImagemRepository;
import tech.fortmea.pi6.repository.PlantaRepository;

@RestController
@CrossOrigin
@RequestMapping("/planta")
public class PlantaController {
    @Autowired
    PlantaRepository plantaRepository;
    @Autowired
    ImagemRepository imgRepo;

    @GetMapping("/")
    public ResponseEntity<List<Planta>> inicio() {
        return ResponseEntity.ok(plantaRepository.findAll());
    }

    @PutMapping("/incluir")
    public ResponseEntity<Planta> incluir(@RequestBody Planta planta) {
        plantaRepository.save(planta);
        return ResponseEntity.ok(planta);
    }

    @DeleteMapping("/remover")
    public ResponseEntity<String> remover(@RequestBody ReqDTO req) {
        List<Imagem> imagens = new ArrayList<Imagem>();
        imagens = imgRepo.findAllByPlantaId(req.getId());
        for (int i = 0; i < imagens.size(); i++) {
            imgRepo.deleteById(imagens.get(i).getId());
        }
        plantaRepository.deleteById(req.getId());

        return ResponseEntity.ok("Removido com sucesso!");
    }

    @GetMapping("/id/{id}")
    public Planta retornaPlanta(@PathVariable String id) {
        Long lid = Long.valueOf(id);
        try {
            return plantaRepository.findById(lid).get();
        } catch (Exception e) {
            return new Planta();
        }
    }

    @GetMapping("/nomep/{nome}")
    public List<Planta> retornaPlantaNomePopular(@PathVariable String nome) {
        try {
            return plantaRepository.findByNomeIn(nome);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ArrayList<Planta>();
        }
    }

    @GetMapping("/nome/{nome}")
    public List<Planta> retornaPlantaNome(@PathVariable String nome) {
        List<Planta> Plantas = new ArrayList<Planta>();
        try {
            Plantas = plantaRepository.findDistinctByNomeCientificoContaining(nome);
            List<Planta> pPop = plantaRepository.findByNomeIn(nome.toLowerCase());
            for (int i = 0; i < pPop.size(); i++) {
                Plantas.add(pPop.get(i));
            }
            return Plantas;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ArrayList<Planta>();
        }
    }

    @PatchMapping("/atualizar")
    public ResponseEntity<String> atualizaPlanta(@RequestBody Planta planta) {
        plantaRepository.save(planta);
        return ResponseEntity.ok("Atualizado com sucesso!");
    }
    
 
}