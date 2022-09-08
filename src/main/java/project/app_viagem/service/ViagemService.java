package project.app_viagem.service;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import project.app_viagem.model.Passageiro;
import project.app_viagem.model.Viagem;
import project.app_viagem.model.dto.PassageiroDTO;
import project.app_viagem.model.dto.ViagemDTO;
import project.app_viagem.repository.ViagemRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ViagemService {

    private ViagemRepository viagemRepository;

    private ModelMapper modelMapper;

    public Viagem criarViagem(Viagem viagem) {
        return viagemRepository.save(viagem);
    }

    public ResponseEntity<ViagemDTO> verViagem(Long viagem_id) {
        Optional<Viagem> passageiro = viagemRepository.findById(viagem_id);

        ViagemDTO viagemDTO = modelMapper.map(passageiro.get(), ViagemDTO.class);

        return ResponseEntity.ok(viagemDTO);
    }

    public ResponseEntity<List<Viagem>> listarViagems() {
        if (!viagemRepository.findAll().isEmpty())
            return ResponseEntity.ok(viagemRepository.findAll());

        return ResponseEntity.notFound().build();
    }

    public ResponseEntity<Viagem> atualizarViagem(Viagem viagem_att, Long viagem_id) {
        if (viagemRepository.existsById(viagem_id)) {
            Viagem viagem = viagemRepository.findById(viagem_id).get();

            if (!viagem_att.getDistancia().isEmpty())
                viagem.setDistancia(viagem_att.getDistancia());

            if (!viagem_att.getLocalInicio().isEmpty())
                viagem.setLocalInicio(viagem_att.getLocalInicio());

            if (!viagem_att.getLocalFim().isEmpty())
                viagem.setLocalFim(viagem_att.getLocalFim());

            if (!viagem_att.getDataInicio().isEmpty())
                viagem.setDataInicio(viagem_att.getDataInicio());

            if (!viagem_att.getDataFim().isEmpty())
                viagem.setDataFim(viagem_att.getDataFim());

            if (!viagem_att.getPassageiros().isEmpty())
                viagem.setPassageiros(viagem_att.getPassageiros());

            viagemRepository.save(viagem);

            return ResponseEntity.ok(viagem);
        }
        return ResponseEntity.notFound().build();
    }

    public ResponseEntity<Viagem> substituirViagem(Viagem viagem_att, Long viagem_id) {
        if (viagemRepository.existsById(viagem_id)) {
            viagem_att.setId(viagem_id);
            viagemRepository.save(viagem_att);

            return ResponseEntity.ok(viagem_att);
        }
        return ResponseEntity.notFound().build();
    }

    public ResponseEntity<Void> excluirViagem(Long viagem_id) {
        if (viagemRepository.existsById(viagem_id)) {
            viagemRepository.deleteById(viagem_id);

            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    public ResponseEntity<Void> deletarViagems() {
        if (!viagemRepository.findAll().isEmpty()) {
            viagemRepository.deleteAll();

            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

}
