package tech.fortmea.pi6.model;

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
public class AgroDados {
    private String tratosCulturais;
    private String cultivo;
    private String materialMetodos;
    private String adubacao;
    private String praga;
    private String irrigacao;
}
