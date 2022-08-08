package project.app_viagem.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Viagem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String localInicio;

    private String localFim;

    private LocalDate dataInicio;

    private LocalDate dataFim;

    @OneToMany
    @JoinColumn(name = "id_passageiros")
    private List<Passageiro> passageiros = new ArrayList<>();
}
