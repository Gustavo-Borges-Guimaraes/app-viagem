package project.app_viagem.model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Viagem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String distancia;

    private String localInicio;

    private String localFim;

    private String dataInicio;

    private String dataFim;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "passageiro_viagem",
            joinColumns = @JoinColumn(name = "viagem_id"),
            inverseJoinColumns = @JoinColumn(name = "passageiro_id")
    )
    private List<Passageiro> passageiros;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "motorista_id")
    private Motorista motorista;
}