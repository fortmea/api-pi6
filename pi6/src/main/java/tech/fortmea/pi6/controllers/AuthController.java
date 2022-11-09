package tech.fortmea.pi6.controllers;

import org.springframework.beans.factory.annotation.Autowired;

import tech.fortmea.pi6.repository.authKeyRepository;

public class AuthController {
    @Autowired
    authKeyRepository authKeyRepo;

    /*public Boolean checkAuth(String md5){
        return authKeyRepo.findByMd5(md5);
    }*/
}
