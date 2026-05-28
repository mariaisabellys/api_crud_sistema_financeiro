package sistema.financeiro.api.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sistema.financeiro.api.dto.DadosCadastroCategoria;
import sistema.financeiro.api.service.CategoriaService;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService service;

    @PostMapping
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroCategoria dados) {
        var categoriaSalva = service.salvar(dados);
        return ResponseEntity.status(HttpStatus.CREATED).body(categoriaSalva);
    }
}
