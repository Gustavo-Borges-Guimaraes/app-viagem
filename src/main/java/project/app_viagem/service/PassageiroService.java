package project.app_viagem.service;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import project.app_viagem.model.Passageiro;
import project.app_viagem.repository.PassageiroRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class PassageiroService {

    private PassageiroRepository passageiroRepository;
    private PessoaService pessoaService;

    public Passageiro criarPassageiro(Passageiro passageiro) {
        return passageiroRepository.save(passageiro);
    }

    public ResponseEntity<Passageiro> verPassageiro(Long passageiro_id) {
        return passageiroRepository.findById(passageiro_id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<List<Passageiro>> listarPassageiros() {
        if (!passageiroRepository.findAll().isEmpty())
            return ResponseEntity.ok(passageiroRepository.findAll());

        return ResponseEntity.notFound().build();
    }

    public ResponseEntity<Passageiro> atualizarPassageiro(Passageiro passageiro_att, Long passageiro_id) {
        if (passageiroRepository.existsById(passageiro_id)) {
            Passageiro passageiro = passageiroRepository.findById(passageiro_id).get();

            passageiro.setPessoa(pessoaService.atualizaPessoa(passageiro.getPessoa(), passageiro_att.getPessoa()));

            return ResponseEntity.ok(passageiro);
        }
        return ResponseEntity.notFound().build();
    }

    public ResponseEntity<Passageiro> substituirPassageiro(Passageiro passageiro_att, Long passageiro_id) {
        if (passageiroRepository.existsById(passageiro_id)) {
            passageiro_att.setId(passageiro_id);
            passageiroRepository.save(passageiro_att);

            return ResponseEntity.ok(passageiro_att);
        }
        return ResponseEntity.notFound().build();
    }

    public ResponseEntity<Void> excluirPassageiro(Long passageiro_id) {
        if (passageiroRepository.existsById(passageiro_id)) {
            passageiroRepository.deleteById(passageiro_id);

            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    public ResponseEntity<Void> deletarPassageiros() {
        if (!passageiroRepository.findAll().isEmpty()) {
            passageiroRepository.deleteAll();

            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

}
