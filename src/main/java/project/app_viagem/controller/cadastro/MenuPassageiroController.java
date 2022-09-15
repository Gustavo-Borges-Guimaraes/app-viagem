package project.app_viagem.controller.cadastro;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.app_viagem.model.dto.ViagemInfoDTO;
import project.app_viagem.service.cadastro.MenuPassageiroService;

import java.util.List;

@RestController
@RequestMapping("/menu/passageiro/{passageiro_id}")
@AllArgsConstructor
public class MenuPassageiroController {

    private MenuPassageiroService menuPassageiroService;

    @PostMapping("/{viagem_id}")
    @ResponseStatus(HttpStatus.CREATED)
    public ViagemInfoDTO cadastrarViagem(@PathVariable("passageiro_id") Long passageiro_id, @PathVariable("viagem_id") Long viagem_id) {
        return menuPassageiroService.cadastrarViagem(passageiro_id, viagem_id);
    }

    @GetMapping("/{viagem_id}")
    public ResponseEntity<ViagemInfoDTO> verViagemCadastrada(@PathVariable("passageiro_id") Long passageiro_id, @PathVariable("viagem_id") Long viagem_id) {
        ViagemInfoDTO viagemInfoDTO = menuPassageiroService.verViagemCadastrada(passageiro_id, viagem_id);
        return ResponseEntity.ok(viagemInfoDTO);
    }

    @GetMapping
    public ResponseEntity<List<ViagemInfoDTO>> listarViagensCadastradas(@PathVariable("passageiro_id") Long passageiro_id) {
        List<ViagemInfoDTO> viagemInfoDTOList = menuPassageiroService.listarViagensCadastradas(passageiro_id);
        return ResponseEntity.ok(viagemInfoDTOList);
    }

    @DeleteMapping("/{viagem_id}")
    public ResponseEntity<Void> removerViagemCadastrada(@PathVariable("passageiro_id") Long passageiro_id, @PathVariable("viagem_id") Long viagem_id) {
        if (menuPassageiroService.removerViagemCadastrada(passageiro_id, viagem_id))
            return ResponseEntity.noContent().build();

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deletarViagensCadastradas(@PathVariable("passageiro_id") Long passageiro_id) {
        if (menuPassageiroService.deletarViagensCadastradas(passageiro_id))
            return ResponseEntity.noContent().build();

        return ResponseEntity.notFound().build();
    }

}
