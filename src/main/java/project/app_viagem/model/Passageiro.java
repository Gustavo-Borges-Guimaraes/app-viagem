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

    @OneToOne(cascade = CascadeType.PERSIST, optional = false)
    @JoinColumn(name = "pessoa_id", nullable = false, unique = true)
    private Pessoa pessoa;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "passageiro_viagem",
            joinColumns = @JoinColumn(name = "passageiro_id"),
            inverseJoinColumns = @JoinColumn(name = "viagem_id")
    )
    private List<Viagem> viagens;

}