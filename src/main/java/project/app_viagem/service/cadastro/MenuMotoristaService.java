package project.app_viagem.service.cadastro;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import project.app_viagem.model.Motorista;
import project.app_viagem.model.Viagem;
import project.app_viagem.model.dto.MotoristaDTO;
import project.app_viagem.model.dto.ViagemInfoDTO;
import project.app_viagem.repository.MotoristaRepository;
import project.app_viagem.repository.ViagemRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class MenuMotoristaService {

    private MotoristaRepository motoristaRepository;
    private ViagemRepository viagemRepository;
    private ModelMapper modelMapper;

    public Viagem cadastrarViagem(@PathVariable("motorista_id") Long motorista_id, @PathVariable("viagem_id") Long viagem_id) {
        Optional<Motorista> motorista = motoristaRepository.findById(motorista_id);
        Optional<Viagem> viagem = viagemRepository.findById(viagem_id);

        if (motorista.isPresent() && viagem.isPresent()) {
            motorista.get().getViagens().add(viagem.get());
            motoristaRepository.save(motorista.get());
        }

        return motorista.get().getViagens().get(viagem_id.intValue() - 1);
    }

    public ResponseEntity<ViagemInfoDTO> verViagemCadastrada(Long motorista_id, Long viagem_id) {
        Optional<Motorista> motorista = motoristaRepository.findById(motorista_id);

        MotoristaDTO motoristaDTO = modelMapper.map(motorista.get(), MotoristaDTO.class);

        return ResponseEntity.ok(motoristaDTO.getViagens().get(viagem_id.intValue() - 1));
    }

    public ResponseEntity<List<ViagemInfoDTO>> listarViagensCadastradas(Long motorista_id) {

        Optional<Motorista> motorista = motoristaRepository.findById(motorista_id);

        MotoristaDTO motoristaDTO = modelMapper.map(motorista.get(), MotoristaDTO.class);

        return ResponseEntity.ok(motoristaDTO.getViagens());
    }

    public ResponseEntity<Void> removerViagemCadastrada(Long motorista_id, Long viagem_id) {
        Optional<Motorista> motorista = motoristaRepository.findById(motorista_id);

        if (motorista.isPresent() && motorista.get().getViagens().contains(viagemRepository.findById(viagem_id).get())) {
            motorista.get().getViagens().remove(viagem_id.intValue() - 1);
            motoristaRepository.save(motorista.get());

            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    public ResponseEntity<Void> deletarViagensCadastradas(Long motorista_id) {
        Optional<Motorista> motorista = motoristaRepository.findById(motorista_id);

        if (motorista.isPresent() && !motorista.get().getViagens().isEmpty()) {
            motorista.get().getViagens().clear();
            motoristaRepository.save(motorista.get());

            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }


}