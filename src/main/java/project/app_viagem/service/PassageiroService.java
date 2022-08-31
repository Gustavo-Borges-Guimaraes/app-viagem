package project.app_viagem.service;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import project.app_viagem.model.Passageiro;
import project.app_viagem.model.dto.PassageiroDTO;
import project.app_viagem.repository.PassageiroRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PassageiroService {

    private PassageiroRepository passageiroRepository;
    private PessoaService pessoaService;
    private ModelMapper modelMapper;

    public Passageiro criarPassageiro(Passageiro passageiro) {
        return passageiroRepository.save(passageiro);
    }

    public ResponseEntity<PassageiroDTO> verPassageiro(Long passageiro_id) {

        Optional<Passageiro> passageiro = passageiroRepository.findById(passageiro_id);

        PassageiroDTO passageiroDTO = modelMapper.map(passageiro.get(), PassageiroDTO.class);

        return ResponseEntity.ok(passageiroDTO);
    }

    public ResponseEntity<List<PassageiroDTO>> listarPassageiros() {

        List<Passageiro> passageiro = passageiroRepository.findAll();

        if (!passageiroRepository.findAll().isEmpty())
            return ResponseEntity.ok(passageiro
                    .stream().map(passageiroDTO -> modelMapper.map(passageiroDTO, PassageiroDTO.class))
                    .collect(Collectors.toList()));

        return ResponseEntity.notFound().build();
    }

    public ResponseEntity<Passageiro> atualizarPassageiro(Passageiro passageiro_att, Long passageiro_id) {
        if (passageiroRepository.existsById(passageiro_id)) {
            Passageiro passageiro = passageiroRepository.findById(passageiro_id).get();

            passageiro.setPessoa(pessoaService.atualizaPessoa(passageiro.getPessoa(), passageiro_att.getPessoa()));

            passageiroRepository.save(passageiro);

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
