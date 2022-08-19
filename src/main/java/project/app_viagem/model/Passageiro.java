package project.app_viagem.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Passageiro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "pessoa_id", nullable = false)
    private Pessoa pessoa;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "viagem_id")
    private List<Viagem> viagens;

}
