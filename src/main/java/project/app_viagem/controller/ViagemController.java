package project.app_viagem.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.app_viagem.model.Viagem;
import project.app_viagem.model.dto.ViagemDTO;
import project.app_viagem.service.ViagemService;

import java.util.List;

@RestController
@RequestMapping("/viagem")
@AllArgsConstructor
public class ViagemController {

    private ViagemService viagemService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Viagem criarViagem(@RequestBody Viagem viagem) {
        return viagemService.criarViagem(viagem);
    }

    @GetMapping("/{viagem_id}")
    public ResponseEntity<ViagemDTO> verViagem(@PathVariable("viagem_id") Long viagem_id) {
        return viagemService.verViagem(viagem_id);
    }

    @GetMapping
    public ResponseEntity<List<Viagem>> listarViagems() {
        return viagemService.listarViagems();
    }

    @PatchMapping("/{viagem_id}")
    public ResponseEntity<Viagem> atualizarViagem(@RequestBody Viagem viagem_att, @PathVariable("viagem_id") Long viagem_id) {
        return viagemService.atualizarViagem(viagem_att, viagem_id);
    }

    @PutMapping("/{viagem_id}")
    public ResponseEntity<Viagem> substituirViagem(@RequestBody Viagem viagem_att, @PathVariable("viagem_id") Long viagem_id) {
        return viagemService.substituirViagem(viagem_att, viagem_id);
    }

    @DeleteMapping("/{viagem_id}")
    public ResponseEntity<Void> excluirViagem(@PathVariable("viagem_id") Long viagem_id) {
        return viagemService.excluirViagem(viagem_id);
    }

    @DeleteMapping
    public ResponseEntity<Void> deletarViagems() {
        return viagemService.deletarViagems();
    }

}
