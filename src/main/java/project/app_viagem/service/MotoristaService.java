package project.app_viagem.service;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import project.app_viagem.model.Motorista;
import project.app_viagem.repository.MotoristaRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class MotoristaService {

    private MotoristaRepository motoristaRepository;
    private PessoaService pessoaService;

    public Motorista criarMotorista(Motorista motorista) {
        return motoristaRepository.save(motorista);
    }

    public ResponseEntity<Motorista> verMotorista(Long motorista_id) {
        return motoristaRepository.findById(motorista_id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<List<Motorista>> listarMotoristas() {
        if (!motoristaRepository.findAll().isEmpty())
            return ResponseEntity.ok(motoristaRepository.findAll());

        return ResponseEntity.notFound().build();
    }

    public ResponseEntity<Motorista> atualizarMotorista(Motorista motorista_att, Long motorista_id) {

        if (motoristaRepository.existsById(motorista_id)) {
            Motorista motorista = motoristaRepository.findById(motorista_id).get();

            if (!motorista_att.getCredenciais().isEmpty())
                motorista.setCredenciais(motorista_att.getCredenciais());

            motorista.setPessoa(pessoaService.atualizaPessoa(motorista.getPessoa(), motorista_att.getPessoa()));
            motoristaRepository.save(motorista);

            return ResponseEntity.ok(motorista);
        }
        return ResponseEntity.notFound().build();
    }

    public ResponseEntity<Motorista> substituirMotorista(Motorista motorista_att, Long motorista_id) {

        if (motoristaRepository.existsById(motorista_id)) {
            motorista_att.setId(motorista_id);
            motoristaRepository.save(motorista_att);

            return ResponseEntity.ok(motorista_att);
        }
        return ResponseEntity.notFound().build();
    }

    public ResponseEntity<Void> excluirMotorista(Long motorista_id) {
        if (motoristaRepository.existsById(motorista_id)) {
            motoristaRepository.deleteById(motorista_id);

            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    public ResponseEntity<Void> deletarMotoristas() {
        if (!motoristaRepository.findAll().isEmpty()) {
            motoristaRepository.deleteAll();

            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

}
