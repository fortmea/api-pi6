package tech.fortmea.pi6.model;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

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
@Entity
@Table(name = "Planta")
public class Planta {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column
    @NonNull
    private String nomeCientifico;

    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime dateTime;

    @ElementCollection
    private List<String> nome;

    @Column
    private FarmaciaDados farmaciaDados;

    @Column 
    @Nullable
    private AgroDados agroDados;
}
