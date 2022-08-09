package project.app_viagem.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import project.app_viagem.model.Motorista;
import project.app_viagem.model.Pessoa;
import project.app_viagem.repository.MotoristaRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class MotoristaService {

    private MotoristaRepository motoristaRepository;

    public Motorista criarMotorista(Motorista motorista) {
        return motoristaRepository.save(motorista);
    }

    public Motorista verMotorista(Long motorista_id) {
        return motoristaRepository.findById(motorista_id).get();
    }

    public List<Motorista> listarMotoristas() {
        return motoristaRepository.findAll();
    }

    public Motorista atualizarMotorista(Motorista motorista_att, Long motorista_id) {
        Optional<Motorista> optionalMotorista = motoristaRepository.findById(motorista_id);

        if (optionalMotorista.isPresent()) {
            Motorista motorista = optionalMotorista.get();

            if (!motorista_att.getCredenciais().isEmpty())
                motorista.setCredenciais(motorista_att.getCredenciais());

            Pessoa pessoa = motorista.getPessoa();
            Pessoa pessoa_att = motorista_att.getPessoa();

            if (!pessoa_att.getNome().isEmpty())
                pessoa.setNome(pessoa_att.getNome());

            if (!pessoa_att.getCpf().isEmpty())
                pessoa.setCpf(pessoa_att.getCpf());

            if (!pessoa_att.getNumero().isEmpty())
                pessoa.setNumero(pessoa_att.getNumero());

            if (!pessoa_att.getEmail().isEmpty())
                pessoa.setEmail(pessoa_att.getEmail());

            motorista.setPessoa(pessoa);

            return motoristaRepository.save(motorista);
        }
        return null;
    }

    public Motorista substituirMotorista(Motorista motorista_att, Long motorista_id) {
        Optional<Motorista> optionalMotorista = motoristaRepository.findById(motorista_id);

        if (optionalMotorista.isPresent()) {
            Motorista motorista = optionalMotorista.get();

            motorista_att.getPessoa().setId(motorista.getPessoa().getId());
            motorista_att.setId(motorista_id);

            return motoristaRepository.save(motorista_att);
        }
        return null;
    }

    public String excluirMotorista(Long motorista_id) {
        motoristaRepository.deleteById(motorista_id);

        return "Motorista de ID: " + motorista_id + " excluido com sucesso!";
    }

    public String deletarMotoristas() {

        motoristaRepository.deleteAll();

        return "Todos motoristas deletados com sucesso!";
    }

}
