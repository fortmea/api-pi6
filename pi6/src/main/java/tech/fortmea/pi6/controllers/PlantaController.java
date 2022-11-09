package tech.fortmea.pi6.controllers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.fortmea.pi6.utils.stringUtils;
import net.bytebuddy.asm.Advice.Return;
import tech.fortmea.pi6.controllers.AuthController;
import tech.fortmea.pi6.model.ImageReqDTO;
import tech.fortmea.pi6.model.Imagem;
import tech.fortmea.pi6.model.Planta;
import tech.fortmea.pi6.model.ReqDTO;
import tech.fortmea.pi6.repository.ImagemRepository;
import tech.fortmea.pi6.repository.PlantaRepository;
import tech.fortmea.pi6.repository.authKeyRepository;

@RestController
@CrossOrigin
@RequestMapping("/planta")
public class PlantaController {
    @Autowired
    PlantaRepository plantaRepository;
    @Autowired
    ImagemRepository imgRepo;

    @Autowired
    authKeyRepository authKeyRepo;

    AuthController authController = new AuthController();

    @GetMapping("/")
    public ResponseEntity<List<Planta>> inicio() {
        return ResponseEntity.ok(plantaRepository.findAll());
    }

    @PutMapping("/incluir")
    public ResponseEntity<Planta> incluir(@RequestBody Planta planta, @RequestHeader String Authorization) {
        System.out.println(Authorization);
        System.out.println(authKeyRepository.findByMd5(Authorization));
        Planta p = new Planta();
        if (authKeyRepository.findByMd5(Authorization) != null) {
            plantaRepository.save(planta);
            return ResponseEntity.ok(planta);
        } else {
            return ResponseEntity.ok(p);
        }
    }

    @DeleteMapping("/remover")
    public ResponseEntity<String> remover(@RequestBody ReqDTO req, @RequestHeader("Authorization") String auth) {
        if (authKeyRepository.findByMd5(auth) != null) {
            List<Imagem> imagens = new ArrayList<Imagem>();
            imagens = imgRepo.findAllByPlantaId(req.getId());
            for (int i = 0; i < imagens.size(); i++) {
                imgRepo.deleteById(imagens.get(i).getId());
            }
            plantaRepository.deleteById(req.getId());

            return ResponseEntity.ok("Removido com sucesso!");
        } else {
            return ResponseEntity.status(403).body("não autorizado");
        }
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
        // System.out.println(nome);
        List<Planta> Plantas = new ArrayList<Planta>();
        List<Planta> pPop = plantaRepository.findAll();
        try {
            for (int i = 0; i < pPop.size() - 1; i++) {
                Planta pAtual = pPop.get(i);
                for (int j = 0; j < pAtual.getNome().size() - 1; j++) {
                    if (stringUtils.containsIgnoreCase(pAtual.getNome().get(j), nome)) {
                        Plantas.add(pAtual);
                    }
                }

            }
            Set<Planta> set = new HashSet<>(Plantas);
            Plantas.clear();
            Plantas.addAll(set);
            return Plantas;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ArrayList<Planta>();
        }
    }

    @PatchMapping("/atualizar")
    public ResponseEntity<String> atualizaPlanta(@RequestBody Planta planta,
            @RequestHeader("Authorization") String auth) {
        if (authKeyRepository.findByMd5(auth) != null) {
            plantaRepository.save(planta);
            return ResponseEntity.ok("Atualizado com sucesso!");
        } else {
            return ResponseEntity.status(403).body("não autorizado");
        }
    }

}