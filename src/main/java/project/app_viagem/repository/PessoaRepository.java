package project.app_viagem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.app_viagem.model.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

}
