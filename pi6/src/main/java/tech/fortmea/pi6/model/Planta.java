package tech.fortmea.pi6.model;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

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
    private String nomeCientifico;

    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime dateTime;

    @ElementCollection
    private List<String> nome;

    @ElementCollection
    private List<String> topicosAgro;

    @ElementCollection
    private List<String> topicosFarma;

    @ElementCollection
    private List<Imagem> imagens;

}
