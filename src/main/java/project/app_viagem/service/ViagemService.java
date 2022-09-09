package project.app_viagem.service;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import project.app_viagem.model.Viagem;
import project.app_viagem.model.dto.ViagemDTO;
import project.app_viagem.repository.ViagemRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ViagemService {

    private ViagemRepository viagemRepository;

    private ModelMapper modelMapper;

    public Viagem criarViagem(Viagem viagem) {
        return viagemRepository.save(viagem);
    }

    public ViagemDTO verViagem(Long viagem_id) {
        if (viagemRepository.findById(viagem_id).isPresent())
            return modelMapper.map(viagemRepository.findById(viagem_id).get(), ViagemDTO.class);

        return null;
    }

    public List<ViagemDTO> listarViagems() {
        if (!viagemRepository.findAll().isEmpty())
            return viagemRepository.findAll().stream()
                    .map(viagemDTO -> modelMapper.map(viagemDTO, ViagemDTO.class))
                    .collect(Collectors.toList());

        return null;
    }

    public Viagem atualizarViagem(Viagem viagem_att, Long viagem_id) {
        if (viagemRepository.findById(viagem_id).isPresent()) {
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

            return viagem;
        }
        return null;
    }

    public Viagem substituirViagem(Viagem viagem_att, Long viagem_id) {
        if (viagemRepository.existsById(viagem_id)) {
            viagem_att.setId(viagem_id);
            viagemRepository.save(viagem_att);

            return viagem_att;
        }
        return null;
    }

    public boolean excluirViagem(Long viagem_id) {
        if (viagemRepository.existsById(viagem_id)) {
            viagemRepository.deleteById(viagem_id);

            return true;
        }
        return false;
    }

    public boolean deletarViagems() {
        if (!viagemRepository.findAll().isEmpty()) {
            viagemRepository.deleteAll();

            return true;
        }
        return false;
    }

}
