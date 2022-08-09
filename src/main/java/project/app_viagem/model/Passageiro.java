package project.app_viagem.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Passageiro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "pessoa_id")
    private Pessoa pessoa;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "viagem_id")
    private Viagem viagem;

}
