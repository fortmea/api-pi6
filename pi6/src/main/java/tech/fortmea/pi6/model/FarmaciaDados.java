package tech.fortmea.pi6.model;

import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;

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
    private String utilizacao;
    private String terapeutico;
    private String contraindicacao;
    @ElementCollection
    private List<String> modoDeUsar;
}
