package project.app_viagem.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Motorista {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "pessoa_id")
    private Pessoa pessoa;

    private String credenciais;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "viagem_id")
    private List<Viagem> viagens;

}
