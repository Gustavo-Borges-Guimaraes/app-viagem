package project.app_viagem.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import project.app_viagem.model.Viagem;
import project.app_viagem.repository.ViagemRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ViagemService {

    private ViagemRepository viagemRepository;

    public Viagem criarViagem(Viagem viagem) {
        return viagemRepository.save(viagem);
    }

    public Viagem verViagem(Long viagem_id) {
        return viagemRepository.findById(viagem_id).get();
    }

    public List<Viagem> listarViagems() {
        return viagemRepository.findAll();
    }

    public Viagem atualizarViagem(Viagem viagem_att, Long viagem_id) {
        Optional<Viagem> optionalViagem = viagemRepository.findById(viagem_id);

        if (optionalViagem.isPresent()) {
            Viagem viagem = optionalViagem.get();

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

            return viagemRepository.save(viagem);
        }
        return null;
    }

    public Viagem substituirViagem(Viagem viagem_att, Long viagem_id) {
        Optional<Viagem> optionalViagem = viagemRepository.findById(viagem_id);

        if (optionalViagem.isPresent()) {
            viagem_att.setId(viagem_id);

            return viagemRepository.save(viagem_att);
        }
        return null;
    }

    public String excluirViagem(Long viagem_id) {
        viagemRepository.deleteById(viagem_id);

        return "Viagem de ID: " + viagem_id + " excluido com sucesso!";
    }

    public String deletarViagems() {

        viagemRepository.deleteAll();

        return "Todos viagems deletados com sucesso!";
    }
}
