package project.app_viagem.controller.cadastro;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.app_viagem.model.Viagem;
import project.app_viagem.model.dto.ViagemInfoDTO;
import project.app_viagem.service.cadastro.MenuPassageiroService;

import java.util.List;

@RestController
@RequestMapping("/cadastro/{passageiro_id}")
@AllArgsConstructor
public class MenuPassageiroController {

    private MenuPassageiroService menuPassageiroService;

    @PostMapping("/{viagem_id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Viagem cadastrarViagem(@PathVariable("passageiro_id") Long passageiro_id, @PathVariable("viagem_id") Long viagem_id) {
        return menuPassageiroService.cadastrarViagem(passageiro_id, viagem_id);
    }

    @GetMapping("/{viagem_id}")
    public ResponseEntity<ViagemInfoDTO> verViagemCadastrada(@PathVariable("passageiro_id") Long passageiro_id, @PathVariable("viagem_id") Long viagem_id) {
        return menuPassageiroService.verViagemCadastrada(passageiro_id, viagem_id);
    }

    @GetMapping
    public ResponseEntity<List<ViagemInfoDTO>> listarViagensCadastradas(@PathVariable("passageiro_id") Long passageiro_id) {
        return menuPassageiroService.listarViagensCadastradas(passageiro_id);
    }

    @DeleteMapping("/{viagem_id}")
    public ResponseEntity<Void> removerViagemCadastrada(@PathVariable("passageiro_id") Long passageiro_id, @PathVariable("viagem_id") Long viagem_id) {
        return menuPassageiroService.removerViagemCadastrada(passageiro_id, viagem_id);
    }

    @DeleteMapping
    public ResponseEntity<Void> deletarViagensCadastradas(@PathVariable("passageiro_id") Long passageiro_id) {
        return menuPassageiroService.deletarViagensCadastradas(passageiro_id);
    }

}
