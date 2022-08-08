package project.app_viagem.controller;

import lombok.AllArgsConstructor;
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
    public Motorista criarMotorista(@RequestBody Motorista motorista) {
        return motoristaService.criarMotorista(motorista);
    }

    @GetMapping("{/motorista_id}")
    public Motorista verMotorista(@PathVariable("motorista_id") Long motorista_id) {
        return motoristaService.verMotorista(motorista_id);
    }

    @GetMapping
    public List<Motorista> listarMotoristas() {
        return motoristaService.listarMotoristas();
    }

    @PatchMapping
    public Motorista atualizarMotorista(@RequestBody Motorista motorista) {
        return motoristaService.atualizarMotorista(motorista);
    }

    @PutMapping
    public Motorista substituirMotorista(@RequestBody Motorista motorista) {
        return motoristaService.substituirMotorista(motorista);
    }

    @DeleteMapping("{/motorista_id}")
    public String excluirMotorista(@PathVariable("motorista_id") Long motorista_id) {
        return motoristaService.excluirMotorista(motorista_id);
    }

    @DeleteMapping
    public String deletarMotoristas() {
        return motoristaService.deletarMotoristas();
    }

}
