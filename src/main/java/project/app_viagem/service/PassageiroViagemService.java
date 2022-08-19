package project.app_viagem.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import project.app_viagem.model.Passageiro;
import project.app_viagem.model.Viagem;
import project.app_viagem.repository.PassageiroRepository;
import project.app_viagem.repository.ViagemRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PassageiroViagemService {

    PassageiroRepository passageiroRepository;
    ViagemRepository viagemRepository;

    public List<Viagem> cadastrarViagem(Long passageiro_id, Long viagem_id) {
        Optional<Passageiro> passageiro = passageiroRepository.findById(passageiro_id);
        Optional<Viagem> viagem = viagemRepository.findById(viagem_id);

        if(passageiro.isPresent() && viagem.isPresent()) {
            passageiro.get().getViagens().add(viagem.get());
            passageiroRepository.save(passageiro.get());
        }

        return passageiro.get().getViagens();
    }

    public Viagem verViagem(Long passageiro_id, Long viagem_id) {
        return passageiroRepository.findById(passageiro_id).get().getViagens().get(viagem_id.intValue() - 1);
    }

    public String removerViagem(Long passageiro_id, Long viagem_id) {
        passageiroRepository.findById(passageiro_id).get().getViagens().remove(viagem_id.intValue() - 1);
        passageiroRepository.save(passageiroRepository.findById(passageiro_id).get());

        return "Viagem de ID: " + viagem_id + " removida com sucesso!";
    }

}
