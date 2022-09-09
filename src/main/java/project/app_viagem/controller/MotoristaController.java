package project.app_viagem.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.app_viagem.model.Motorista;
import project.app_viagem.model.dto.MotoristaDTO;
import project.app_viagem.service.MotoristaService;

import java.util.List;

@RestController
@RequestMapping("/motorista")
@AllArgsConstructor
public class MotoristaController {

    private MotoristaService motoristaService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Motorista criarMotorista(@RequestBody Motorista motorista) {
        return motoristaService.criarMotorista(motorista);
    }

    @GetMapping("/{motorista_id}")
    public ResponseEntity<MotoristaDTO> verMotorista(@PathVariable("motorista_id") Long motorista_id) {
        return ResponseEntity.ok(motoristaService.verMotorista(motorista_id));
    }

    @GetMapping
    public ResponseEntity<List<MotoristaDTO>> listarMotoristas() {
        return ResponseEntity.ok(motoristaService.listarMotoristas());
    }

    @PatchMapping("/{motorista_id}")
    public ResponseEntity<Motorista> atualizarMotorista(@RequestBody Motorista motorista_att, @PathVariable("motorista_id") Long motorista_id) {
        Motorista motorista = motoristaService.atualizarMotorista(motorista_att, motorista_id);

        if (motorista == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(motorista);
    }

    @PutMapping("/{motorista_id}")
    public ResponseEntity<Motorista> substituirMotorista(@RequestBody Motorista motorista_att, @PathVariable("motorista_id") Long motorista_id) {
        Motorista motorista = motoristaService.substituirMotorista(motorista_att, motorista_id);

        if (motorista == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(motorista);
    }

    @DeleteMapping("/{motorista_id}")
    public ResponseEntity<Void> excluirMotorista(@PathVariable("motorista_id") Long motorista_id) {
        if (motoristaService.excluirMotorista(motorista_id))
            return ResponseEntity.noContent().build();

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deletarMotoristas() {
        if (motoristaService.deletarMotoristas())
            return ResponseEntity.noContent().build();

        return ResponseEntity.notFound().build();
    }

}
