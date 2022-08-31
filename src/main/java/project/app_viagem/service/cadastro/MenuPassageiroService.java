package project.app_viagem.service.cadastro;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import project.app_viagem.model.Passageiro;
import project.app_viagem.model.Viagem;
import project.app_viagem.model.dto.PassageiroDTO;
import project.app_viagem.model.dto.ViagemDTO;
import project.app_viagem.repository.PassageiroRepository;
import project.app_viagem.repository.ViagemRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class MenuPassageiroService {

    private PassageiroRepository passageiroRepository;
    private ViagemRepository viagemRepository;

    private ModelMapper modelMapper;

    public Viagem cadastrarViagem(Long passageiro_id, Long viagem_id) {
        Optional<Passageiro> passageiro = passageiroRepository.findById(passageiro_id);
        Optional<Viagem> viagem = viagemRepository.findById(viagem_id);

        if (passageiro.isPresent() && viagem.isPresent()) {
            passageiro.get().getViagens().add(viagem.get());
            passageiroRepository.save(passageiro.get());
        }

        return passageiro.get().getViagens().get(viagem_id.intValue() - 1);
    }

    public ResponseEntity<ViagemDTO> verViagemCadastrada(Long passageiro_id, Long viagem_id) {
        Optional<Passageiro> passageiro = passageiroRepository.findById(passageiro_id);

        PassageiroDTO passageiroDTO = modelMapper.map(passageiro.get(), PassageiroDTO.class);

        return ResponseEntity.ok(passageiroDTO.getViagens().get(viagem_id.intValue() - 1));
    }

    public ResponseEntity<List<ViagemDTO>> listarViagensCadastradas(Long passageiro_id) {

        Optional<Passageiro> passageiro = passageiroRepository.findById(passageiro_id);

        PassageiroDTO passageiroDTO = modelMapper.map(passageiro.get(), PassageiroDTO.class);

        return ResponseEntity.ok(passageiroDTO.getViagens());
    }

    public ResponseEntity<Void> removerViagemCadastrada(Long passageiro_id, Long viagem_id) {
        Optional<Passageiro> passageiro = passageiroRepository.findById(passageiro_id);

        if (passageiro.isPresent() && passageiro.get().getViagens().contains(viagemRepository.findById(viagem_id).get())) {
            passageiro.get().getViagens().remove(viagem_id.intValue() - 1);
            passageiroRepository.save(passageiro.get());

            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    public ResponseEntity<Void> deletarViagensCadastradas(Long passageiro_id) {
        Optional<Passageiro> passageiro = passageiroRepository.findById(passageiro_id);

        if (passageiro.isPresent() && !passageiro.get().getViagens().isEmpty()) {
            passageiro.get().getViagens().clear();
            passageiroRepository.save(passageiro.get());

            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

}
