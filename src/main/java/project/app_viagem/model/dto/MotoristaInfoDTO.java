package project.app_viagem.model.dto;

import lombok.Data;
import project.app_viagem.model.Pessoa;

@Data
public class MotoristaInfoDTO {

    private Pessoa pessoa;

    private String credencial;

}
