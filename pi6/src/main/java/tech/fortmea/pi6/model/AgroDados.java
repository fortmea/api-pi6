package tech.fortmea.pi6.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Lob;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
@Embeddable
public class AgroDados {
    
    @Column(length = 1000)
    private String tratosCulturais;
    
    @Column(length = 1000)
    private String cultivo;
    
    @Column(length = 1000)
    private String materialMetodos;
    
    @Column(length = 1000)
    private String adubacao;
    
    @Column(length = 1000)
    private String praga;
    
    @Column(length = 1000)
    private String irrigacao;
}
