package project.app_viagem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.app_viagem.model.Passageiro;

@Repository
public interface PassageiroRepository extends JpaRepository<Passageiro, Long> {

}
