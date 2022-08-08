package project.app_viagem.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import project.app_viagem.model.Motorista;
import project.app_viagem.repository.MotoristaRepository;

import java.util.List;

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

    public Motorista atualizarMotorista(Motorista motorista_att) {
        Motorista motorista = motoristaRepository.findById(motorista_att.getId()).get();

        if (!motorista_att.getCredenciais().isEmpty())
            motorista.setCredenciais(motorista_att.getCredenciais());

        if (!motorista_att.getPessoa().getNome().isEmpty())
            motorista.getPessoa().setNome(motorista_att.getPessoa().getNome());

        if (!motorista_att.getPessoa().getCpf().isEmpty())
            motorista.getPessoa().setCpf(motorista_att.getPessoa().getCpf());

        if (!motorista_att.getPessoa().getNumero().isEmpty())
            motorista.getPessoa().setNumero(motorista_att.getPessoa().getNumero());

        if (!motorista_att.getPessoa().getEmail().isEmpty())
            motorista.getPessoa().setEmail(motorista_att.getPessoa().getEmail());

        return motoristaRepository.save(motorista);
    }

    public Motorista substituirMotorista(Motorista motorista) {
        return motoristaRepository.save(motorista);
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
