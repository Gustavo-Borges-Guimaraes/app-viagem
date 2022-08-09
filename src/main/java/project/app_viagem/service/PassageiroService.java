package project.app_viagem.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import project.app_viagem.model.Passageiro;
import project.app_viagem.model.Pessoa;
import project.app_viagem.model.Viagem;
import project.app_viagem.repository.PassageiroRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PassageiroService {

    private PassageiroRepository passageiroRepository;

    public Passageiro criarPassageiro(Passageiro passageiro) {
        return passageiroRepository.save(passageiro);
    }

    public Passageiro verPassageiro(Long passageiro_id) {
        return passageiroRepository.findById(passageiro_id).get();
    }

    public List<Passageiro> listarPassageiros() {
        return passageiroRepository.findAll();
    }

    public Passageiro atualizarPassageiro(Passageiro passageiro_att, Long passageiro_id) {
        Optional<Passageiro> optionalPassageiro = passageiroRepository.findById(passageiro_id);

        if (optionalPassageiro.isPresent()) {
            Passageiro passageiro = optionalPassageiro.get();

            Pessoa pessoa = passageiro.getPessoa();
            Pessoa pessoa_att = passageiro_att.getPessoa();

            if (!pessoa_att.getNome().isEmpty())
                pessoa.setNome(pessoa_att.getNome());

            if (!pessoa_att.getCpf().isEmpty())
                pessoa.setCpf(pessoa_att.getCpf());

            if (!pessoa_att.getNumero().isEmpty())
                pessoa.setNumero(pessoa_att.getNumero());

            if (!pessoa_att.getEmail().isEmpty())
                pessoa.setEmail(pessoa_att.getEmail());

            passageiro.setPessoa(pessoa);

            return passageiroRepository.save(passageiro);
        }
        return null;
    }

    public Passageiro substituirPassageiro(Passageiro passageiro_att, Long passageiro_id) {
        Optional<Passageiro> optionalPassageiro = passageiroRepository.findById(passageiro_id);

        if (optionalPassageiro.isPresent()) {
            Passageiro passageiro = optionalPassageiro.get();

            passageiro_att.getPessoa().setId(passageiro.getPessoa().getId());
            passageiro_att.setId(passageiro_id);

            return passageiroRepository.save(passageiro_att);
        }
        return null;
    }

    public String excluirPassageiro(Long passageiro_id) {
        passageiroRepository.deleteById(passageiro_id);

        return "Passageiro de ID: " + passageiro_id + " excluido com sucesso!";
    }

    public String deletarPassageiros() {

        passageiroRepository.deleteAll();

        return "Todos passageiros deletados com sucesso!";
    }

}
