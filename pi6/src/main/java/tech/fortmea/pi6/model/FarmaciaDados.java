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

    @Lob
    @Column
    private String utilizacao;

    @Lob
    @Column
    private String terapeutico;

    @Lob
    @Column
    private String contraindicacao;
    @Lob
    @Column
    private String fonte;
    
    @Lob
    @Column
    private String beneficios;

    @ElementCollection
    @Lob
    @Column
    private List<String> modoDeUsar;
}
