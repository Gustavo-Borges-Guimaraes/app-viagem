package project.app_viagem.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.app_viagem.model.Motorista;
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
    public ResponseEntity<Motorista> verMotorista(@PathVariable("motorista_id") Long motorista_id) {
        return motoristaService.verMotorista(motorista_id);
    }

    @GetMapping
    public ResponseEntity<List<Motorista>> listarMotoristas() {
        return motoristaService.listarMotoristas();
    }

    @PatchMapping("/{motorista_id}")
    public ResponseEntity<Motorista> atualizarMotorista(@RequestBody Motorista motorista_att, @PathVariable("motorista_id") Long motorista_id) {
        return motoristaService.atualizarMotorista(motorista_att, motorista_id);
    }

    @PutMapping("/{motorista_id}")
    public ResponseEntity<Motorista> substituirMotorista(@RequestBody Motorista motorista_att, @PathVariable("motorista_id") Long motorista_id) {
        return motoristaService.substituirMotorista(motorista_att, motorista_id);
    }

    @DeleteMapping("/{motorista_id}")
    public ResponseEntity<Void> excluirMotorista(@PathVariable("motorista_id") Long motorista_id) {
        return motoristaService.excluirMotorista(motorista_id);
    }

    @DeleteMapping
    public ResponseEntity<Void> deletarMotoristas() {
        return motoristaService.deletarMotoristas();
    }

}
