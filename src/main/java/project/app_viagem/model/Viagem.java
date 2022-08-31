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

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "passageiros_id")
    private List<Passageiro> passageiros = new ArrayList<>();

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "motorista_id")
    private Motorista motorista;
}