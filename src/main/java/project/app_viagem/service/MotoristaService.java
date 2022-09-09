package project.app_viagem.service;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import project.app_viagem.model.Motorista;
import project.app_viagem.model.dto.MotoristaDTO;
import project.app_viagem.repository.MotoristaRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class MotoristaService {

    private MotoristaRepository motoristaRepository;
    private PessoaService pessoaService;
    private ModelMapper modelMapper;

    public Motorista criarMotorista(Motorista motorista) {
        return motoristaRepository.save(motorista);
    }

    public MotoristaDTO verMotorista(Long motorista_id) {
        if (motoristaRepository.findById(motorista_id).isPresent())
            return modelMapper.map(motoristaRepository.findById(motorista_id).get(), MotoristaDTO.class);

        return null;
    }

    public List<MotoristaDTO> listarMotoristas() {
        if (!motoristaRepository.findAll().isEmpty())
            return motoristaRepository.findAll().stream()
                    .map(motoristaDTO -> modelMapper.map(motoristaDTO, MotoristaDTO.class))
                    .collect(Collectors.toList());

        return null;
    }

    public Motorista atualizarMotorista(Motorista motorista_att, Long motorista_id) {
        Optional<Motorista> motorista = motoristaRepository.findById(motorista_id);

        if (motorista.isPresent()) {
            if (!motorista_att.getCredenciais().isEmpty())
                motorista.get().setCredenciais(motorista_att.getCredenciais());

            motorista.get().setPessoa(pessoaService.atualizaPessoa(motorista.get().getPessoa(), motorista_att.getPessoa()));
            motoristaRepository.save(motorista.get());

            return motorista.get();
        }
        return null;
    }

    public Motorista substituirMotorista(Motorista motorista_att, Long motorista_id) {
        if (motoristaRepository.existsById(motorista_id)) {
            motorista_att.setId(motorista_id);
            motoristaRepository.save(motorista_att);

            return motorista_att;
        }
        return null;
    }

    public boolean excluirMotorista(Long motorista_id) {
        if (motoristaRepository.existsById(motorista_id)) {
            motoristaRepository.deleteById(motorista_id);

            return true;
        }
        return false;
    }

    public boolean deletarMotoristas() {
        if (!motoristaRepository.findAll().isEmpty()) {
            motoristaRepository.deleteAll();

            return true;
        }
        return false;
    }

}
