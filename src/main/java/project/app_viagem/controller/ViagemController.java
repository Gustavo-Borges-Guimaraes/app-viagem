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
        return ResponseEntity.ok(viagemService.verViagem(viagem_id));
    }

    @GetMapping
    public ResponseEntity<List<ViagemDTO>> listarViagems() {
        return ResponseEntity.ok(viagemService.listarViagems());
    }

    @PatchMapping("/{viagem_id}")
    public ResponseEntity<Viagem> atualizarViagem(@RequestBody Viagem viagem_att, @PathVariable("viagem_id") Long viagem_id) {
        Viagem viagem = viagemService.atualizarViagem(viagem_att, viagem_id);

        if (viagem == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(viagem);
    }

    @PutMapping("/{viagem_id}")
    public ResponseEntity<Viagem> substituirViagem(@RequestBody Viagem viagem_att, @PathVariable("viagem_id") Long viagem_id) {
        Viagem viagem = viagemService.substituirViagem(viagem_att, viagem_id);

        if (viagem == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(viagem);
    }

    @DeleteMapping("/{viagem_id}")
    public ResponseEntity<Void> excluirViagem(@PathVariable("viagem_id") Long viagem_id) {
        if (viagemService.excluirViagem(viagem_id))
            return ResponseEntity.noContent().build();

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deletarViagems() {
        if (viagemService.deletarViagems())
            return ResponseEntity.noContent().build();

        return ResponseEntity.notFound().build();
    }

}
