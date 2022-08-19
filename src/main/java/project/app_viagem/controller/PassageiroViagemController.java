package project.app_viagem.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import project.app_viagem.model.Viagem;
import project.app_viagem.service.PassageiroService;
import project.app_viagem.service.PassageiroViagemService;

import java.util.List;

@RestController
@RequestMapping("/{passageiro_id}")
@AllArgsConstructor
public class PassageiroViagemController {

    PassageiroViagemService passageiroViagemService;

    @PostMapping("/{viagem_id}")
    public List<Viagem> cadastrarViagem(@PathVariable("passageiro_id") Long passageiro_id, @PathVariable("viagem_id") Long viagem_id) {
        return passageiroViagemService.cadastrarViagem(passageiro_id, viagem_id);
    }

    @GetMapping("/{viagem_id}")
    public Viagem verViagem(@PathVariable("passageiro_id") Long passageiro_id, @PathVariable("viagem_id") Long viagem_id) {
        return passageiroViagemService.verViagem(passageiro_id, viagem_id);
    }

    @DeleteMapping("/{viagem_id}")
    public String removerViagem(@PathVariable("passageiro_id") Long passageiro_id, @PathVariable("viagem_id") Long viagem_id) {
        return passageiroViagemService.removerViagem(passageiro_id, viagem_id);
    }

}
