package project.app_viagem.model.dto;

import lombok.Data;
import project.app_viagem.model.Pessoa;

import java.util.List;

@Data
public class MotoristaDTO {

    private Pessoa pessoa;

    private String credenciais;

    private List<ViagemInfoDTO> viagens;

}
