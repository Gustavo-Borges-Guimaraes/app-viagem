package project.app_viagem.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.app_viagem.model.Passageiro;
import project.app_viagem.model.dto.PassageiroDTO;
import project.app_viagem.service.PassageiroService;

import java.util.List;

@RestController
@RequestMapping("/passageiro")
@AllArgsConstructor
public class PassageiroController {

    private PassageiroService passageiroService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Passageiro criarPassageiro(@RequestBody Passageiro passageiro) {
        return passageiroService.criarPassageiro(passageiro);
    }

    @GetMapping("/{passageiro_id}")
    public ResponseEntity<PassageiroDTO> verPassageiro(@PathVariable("passageiro_id") Long passageiro_id) {
        return ResponseEntity.ok(passageiroService.verPassageiro(passageiro_id));
    }

    @GetMapping
    public ResponseEntity<List<PassageiroDTO>> listarPassageiros() {
        return ResponseEntity.ok(passageiroService.listarPassageiros());
    }

    @PatchMapping("/{passageiro_id}")
    public ResponseEntity<Passageiro> atualizarPassageiro(@RequestBody Passageiro passageiro_att, @PathVariable("passageiro_id") Long passageiro_id) {
        Passageiro passageiro = passageiroService.atualizarPassageiro(passageiro_att, passageiro_id);

        if (passageiro == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(passageiro);
    }

    @PutMapping("/{passageiro_id}")
    public ResponseEntity<Passageiro> substituirPassageiro(@RequestBody Passageiro passageiro_att, @PathVariable("passageiro_id") Long passageiro_id) {
        Passageiro passageiro = passageiroService.substituirPassageiro(passageiro_att, passageiro_id);

        if (passageiro == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(passageiro);
    }

    @DeleteMapping("/{passageiro_id}")
    public ResponseEntity<Void> excluirPassageiro(@PathVariable("passageiro_id") Long passageiro_id) {
        if (passageiroService.excluirPassageiro(passageiro_id))
            return ResponseEntity.noContent().build();

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deletarPassageiros() {
        if (passageiroService.deletarPassageiros())
            return ResponseEntity.noContent().build();

        return ResponseEntity.notFound().build();
    }

}
