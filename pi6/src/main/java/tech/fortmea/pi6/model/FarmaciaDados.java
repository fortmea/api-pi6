package tech.fortmea.pi6.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
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
public class FarmaciaDados {
    
    @Column(length = 1000)
    private String utilizacao;
    
    @Column(length = 1000)
    private String terapeutico;
    
    @Column(length = 1000)
    private String contraindicacao;
    @ElementCollection
    private List<String> modoDeUsar;
}
