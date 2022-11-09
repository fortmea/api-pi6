package tech.fortmea.pi6.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tech.fortmea.pi6.model.AuthKey;


@Repository
public interface authKeyRepository extends JpaRepository<AuthKey, Long>{
    Boolean existsBymd5(String md5);
    public static String findByMd5(String md5){
        return "true";
    }
}