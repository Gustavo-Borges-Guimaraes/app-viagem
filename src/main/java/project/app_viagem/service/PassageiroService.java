package project.app_viagem.service;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
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

    public PassageiroDTO verPassageiro(Long passageiro_id) {
        if (passageiroRepository.findById(passageiro_id).isPresent())
            return modelMapper.map(passageiroRepository.findById(passageiro_id).get(), PassageiroDTO.class);

        return null;
    }

    public List<PassageiroDTO> listarPassageiros() {
        if (!passageiroRepository.findAll().isEmpty())
            return passageiroRepository.findAll().stream()
                    .map(passageiroDTO -> modelMapper.map(passageiroDTO, PassageiroDTO.class))
                    .collect(Collectors.toList());

        return null;
    }

    public Passageiro atualizarPassageiro(Passageiro passageiro_att, Long passageiro_id) {
        Optional<Passageiro> passageiro = passageiroRepository.findById(passageiro_id);

        if (passageiro.isPresent()) {
            passageiro.get().setPessoa(pessoaService.atualizaPessoa(passageiro.get().getPessoa(), passageiro_att.getPessoa()));

            passageiroRepository.save(passageiro.get());

            return passageiro.get();
        }
        return null;
    }

    public Passageiro substituirPassageiro(Passageiro passageiro_att, Long passageiro_id) {
        if (passageiroRepository.existsById(passageiro_id)) {
            passageiro_att.setId(passageiro_id);
            passageiroRepository.save(passageiro_att);

            return passageiro_att;
        }
        return null;
    }

    public boolean excluirPassageiro(Long passageiro_id) {
        if (passageiroRepository.existsById(passageiro_id)) {
            passageiroRepository.deleteById(passageiro_id);

            return true;
        }
        return false;
    }

    public boolean deletarPassageiros() {
        if (!passageiroRepository.findAll().isEmpty()) {
            passageiroRepository.deleteAll();

            return true;
        }
        return false;
    }

}
