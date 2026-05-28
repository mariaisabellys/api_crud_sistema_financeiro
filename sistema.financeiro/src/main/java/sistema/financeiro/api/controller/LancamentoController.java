package sistema.financeiro.api.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sistema.financeiro.api.dto.DadosCadastroLancamento;
import sistema.financeiro.api.service.LancamentoService;

@RestController
@RequestMapping("/lancamentos")
public class LancamentoController {

    @Autowired
    private LancamentoService service;

    @PostMapping
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroLancamento dados) {
        var lancamentoSalvo = service.salvar(dados);
        return ResponseEntity.status(HttpStatus.CREATED).body(lancamentoSalvo);
    }
}
