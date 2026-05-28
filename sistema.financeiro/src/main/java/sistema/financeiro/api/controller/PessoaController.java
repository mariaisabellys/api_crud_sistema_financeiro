package sistema.financeiro.api.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sistema.financeiro.api.dto.DadosCadastroPessoa;
import sistema.financeiro.api.service.PessoaService;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {

    @Autowired
    private PessoaService service;

    @PostMapping
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroPessoa dados) {
        var pessoaSalva = service.salvar(dados);
        return ResponseEntity.status(HttpStatus.CREATED).body(pessoaSalva);
    }
}
