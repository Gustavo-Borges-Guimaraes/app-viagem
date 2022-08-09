package project.app_viagem.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    //@Column(name = "cpf", unique = true)
    private String cpf;

    private String email;

    private String numero;

}
