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

    @Lob
    @Column
    private String tratosCulturais;

    @Lob
    @Column
    private String cultivo;

    @Lob
    @Column
    private String materialMetodos;

    @Lob
    @Column
    private String adubacao;

    @Lob
    @Column
    private String praga;

    @Lob
    @Column
    private String irrigacao;
}
