package tech.fortmea.pi6.controllers;

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

import tech.fortmea.pi6.repository.ImagemRepository;
import tech.fortmea.pi6.model.ImageReqDTO;
import tech.fortmea.pi6.model.Imagem;
import tech.fortmea.pi6.model.ReqDTO;

@RestController
@CrossOrigin
@RequestMapping("/imagem")
public class ImagemController {

    @Autowired
    ImagemRepository imgRepo;

    @GetMapping("/plantaid/{id}")
    public ResponseEntity<List<Imagem>> listaPorIdPlanta(@PathVariable String id) {
        Long lid = Long.valueOf(id);
        return ResponseEntity.ok(imgRepo.findAllByPlantaId(lid));
    }

    @PutMapping("/incluir")
    public ResponseEntity<String> AdicionarImagem(@RequestBody Imagem img) {
        imgRepo.save(img);
        return ResponseEntity.ok("Adicionada com sucesso!");
    }

    @DeleteMapping("/remover")
    public ResponseEntity<String> RemoverImagem(@RequestBody ReqDTO req) {
        imgRepo.deleteById(req.getId());
        return ResponseEntity.ok("Removido com sucesso!");
    }
    @GetMapping("/favorita/planta/{id}")
    public ResponseEntity<Imagem> getFavorita(@PathVariable String id) {
        Long lid = Long.valueOf(id);
        try {
            return ResponseEntity.ok(imgRepo.findByPlantaIdAndFavorita(lid, true));
        } catch (Exception e) {
            return ResponseEntity.ok(new Imagem());
        }
    }
    @PatchMapping("/favorita")
    public ResponseEntity<String> atualizaFavorita(@RequestBody ImageReqDTO req) {
        Imagem img = imgRepo.findByPlantaIdAndFavorita(req.getPlantaid(), true);
        if(img != null){
            img.setFavorita(false);
            imgRepo.save(img);
        }
        Imagem nFavorita = imgRepo.findById(req.getImgid()).get();
        nFavorita.setFavorita(true);
        imgRepo.save(nFavorita);
        return ResponseEntity.ok("Atualizado com sucesso!");
    }
}