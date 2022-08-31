package project.app_viagem.controller.cadastro;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.app_viagem.model.Viagem;
import project.app_viagem.model.dto.ViagemDTO;
import project.app_viagem.service.cadastro.MenuMotoristaService;

import java.util.List;

@RestController
@RequestMapping("/menu/motorista/{motorista_id}")
@AllArgsConstructor
public class MenuMotoristaController {

    public MenuMotoristaService menuMotoristaService;

    @PostMapping("/{viagem_id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Viagem cadastrarMotorista(@PathVariable("motorista_id") Long motorista_id, @PathVariable("viagem_id") Long viagem_id) {
        return menuMotoristaService.cadastrarViagem(motorista_id, viagem_id);
    }

    @GetMapping("/{viagem_id}")
    public ResponseEntity<ViagemDTO> verViagemCadastrada(@PathVariable("motorista_id") Long motorista_id, @PathVariable("viagem_id") Long viagem_id) {
        return menuMotoristaService.verViagemCadastrada(motorista_id, viagem_id);
    }

    @GetMapping
    public ResponseEntity<List<ViagemDTO>> listarViagensCadastradas(@PathVariable("motorista_id") Long motorista_id) {
        return menuMotoristaService.listarViagensCadastradas(motorista_id);
    }

    @DeleteMapping("/{viagem_id}")
    public ResponseEntity<Void> removerViagemCadastrada(@PathVariable("motorista_id") Long motorista_id, @PathVariable("viagem_id") Long viagem_id) {
        return menuMotoristaService.removerViagemCadastrada(motorista_id, viagem_id);
    }

    @DeleteMapping
    public ResponseEntity<Void> deletarViagensCadastradas(@PathVariable("motorista_id") Long motorista_id) {
        return menuMotoristaService.deletarViagensCadastradas(motorista_id);
    }

}
