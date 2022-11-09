package tech.fortmea.pi6.controllers;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tech.fortmea.pi6.model.Usuario;
import tech.fortmea.pi6.model.AuthKey;
import tech.fortmea.pi6.repository.UsuarioRepository;
import tech.fortmea.pi6.repository.authKeyRepository;

@RestController
@CrossOrigin
@RequestMapping("/usuario")
public class UsuarioController {
    @Autowired
    UsuarioRepository userRepo;
    @Autowired
    authKeyRepository authKeyRepo;
    public static String getMd5(String input) {
        try {

            // Static getInstance method is called with hashing MD5
            MessageDigest md = MessageDigest.getInstance("MD5");

            // digest() method is called to calculate message digest
            // of an input digest() return array of byte
            byte[] messageDigest = md.digest(input.getBytes());

            // Convert byte array into signum representation
            BigInteger no = new BigInteger(1, messageDigest);

            // Convert message digest into hex value
            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        }

        // For specifying wrong message digest algorithms
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    @Scheduled(fixedRate = 3600000)
    public void deleteNonActiveProducts() {
    
        List<AuthKey> keys = authKeyRepo.findAll(); 
        keys.forEach(key -> authKeyRepo.deleteById(key.getId()));
    }

    @PostMapping("/login")
    public ResponseEntity<String> fazerLogin(@RequestBody Usuario user) {
        Usuario found = userRepo.findByNomeAndSenha(user.getNome(), getMd5(user.getSenha()));
        if (found != null) {
            String md5 = getMd5(found.getNome() + found.getSenha());
            AuthKey key = new AuthKey();
            key.setMd5(md5);
            key.setUserId(found.getId());
            // authKeyRepo.save(key);
            return ResponseEntity.ok(md5);
        } else {
            return ResponseEntity.status(403).body("usuário não encontrado");
        }

    }

    @DeleteMapping("/logout")
    public ResponseEntity<String> logout(@RequestBody String md5){
        System.out.println(md5);
       /*  AuthKey found = authKeyRepository.findByMd5(md5);
        if(found !=null){
            authKeyRepo.delete(found);
            return ResponseEntity.ok("Logout com sucesso!");
        }else{
            return ResponseEntity.status(400).body("não encontrado");
        }
        */
        return ResponseEntity.ok("Logout com sucesso!");
    }
}
