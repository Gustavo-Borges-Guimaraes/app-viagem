package project.app_viagem.service;

import org.springframework.stereotype.Service;
import project.app_viagem.model.Pessoa;

@Service
public class PessoaService {

    public Pessoa atualizaPessoa(Pessoa pessoa, Pessoa pessoa_att) {
        if (!pessoa_att.getNome().isEmpty())
            pessoa.setNome(pessoa_att.getNome());

        if (!pessoa_att.getCpf().isEmpty())
            pessoa.setCpf(pessoa_att.getCpf());

        if (!pessoa_att.getNumero().isEmpty())
            pessoa.setNumero(pessoa_att.getNumero());

        if (!pessoa_att.getEmail().isEmpty())
            pessoa.setEmail(pessoa_att.getEmail());

        return pessoa;
    }

}
