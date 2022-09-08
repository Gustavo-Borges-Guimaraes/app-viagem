package project.app_viagem.model.dto;

import lombok.Data;
import project.app_viagem.model.Motorista;
import project.app_viagem.model.Passageiro;

import java.util.ArrayList;
import java.util.List;

@Data
public class ViagemDTO {

    private String distancia;

    private String localInicio;

    private String localFim;

    private String dataInicio;

    private String dataFim;

    private List<PassageiroInfoDTO> passageiros = new ArrayList<>();

    private MotoristaInfoDTO motorista;

}
