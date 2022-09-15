package project.app_viagem.service.cadastro;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import project.app_viagem.model.Passageiro;
import project.app_viagem.model.Viagem;
import project.app_viagem.model.dto.PassageiroDTO;
import project.app_viagem.model.dto.ViagemInfoDTO;
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

    public ViagemInfoDTO cadastrarViagem(Long passageiro_id, Long viagem_id) {
        Optional<Passageiro> passageiro = passageiroRepository.findById(passageiro_id);
        Optional<Viagem> viagem = viagemRepository.findById(viagem_id);

        if (passageiro.isPresent() && viagem.isPresent()) {
//            passageiro.get().getViagens().add(viagem.get());
//            passageiroRepository.save(passageiro.get());
            viagem.get().getPassageiros().add(passageiro.get());
            viagemRepository.save(viagem.get());

            return modelMapper.map(viagem.get(), ViagemInfoDTO.class);
        }
        return null;
    }

    public ViagemInfoDTO verViagemCadastrada(Long passageiro_id, Long viagem_id) {
        Optional<Passageiro> passageiro = passageiroRepository.findById(passageiro_id);

        if (passageiro.isPresent()) {
            PassageiroDTO passageiroDTO = modelMapper.map(passageiro.get(), PassageiroDTO.class);
            if (passageiroDTO.getViagens().get(viagem_id.intValue() - 1) != null)
                return passageiroDTO.getViagens().get(viagem_id.intValue() - 1);
        }
        return null;
    }

    public List<ViagemInfoDTO> listarViagensCadastradas(Long passageiro_id) {
        Optional<Passageiro> passageiro = passageiroRepository.findById(passageiro_id);

        if (passageiro.isPresent()) {
            PassageiroDTO passageiroDTO = modelMapper.map(passageiro.get(), PassageiroDTO.class);
            return passageiroDTO.getViagens();
        }
        return null;
    }

    public boolean removerViagemCadastrada(Long passageiro_id, Long viagem_id) {
        Optional<Passageiro> passageiro = passageiroRepository.findById(passageiro_id);
        Optional<Viagem> viagem = viagemRepository.findById(viagem_id);

        if (passageiro.isPresent() && viagem.isPresent())
            if (passageiro.get().getViagens().contains(viagem.get())) {
                passageiro.get().getViagens().remove(viagem_id.intValue() - 1);
                passageiroRepository.save(passageiro.get());

                return true;
            }
        return false;
    }

    public boolean deletarViagensCadastradas(Long passageiro_id) {
        Optional<Passageiro> passageiro = passageiroRepository.findById(passageiro_id);

        if (passageiro.isPresent() && !passageiro.get().getViagens().isEmpty()) {
            passageiro.get().getViagens().clear();
            passageiroRepository.save(passageiro.get());

            return true;
        }
        return false;
    }

}
