package sistema.financeiro.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import sistema.financeiro.api.dto.DadosAtualizacaoCategoria;
import sistema.financeiro.api.dto.DadosCadastroCategoria;
import sistema.financeiro.api.dto.DadosDetalhamentoCategoria;
import sistema.financeiro.api.service.CategoriaService;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService service;

    @PostMapping
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroCategoria dados, UriComponentsBuilder uriBuilder) {
        var categoria = service.salvar(dados);
        var uri = uriBuilder.path("/categorias/{codigo}").buildAndExpand(categoria.getCodigo()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoCategoria(categoria));
    }

    @GetMapping
    public ResponseEntity<Page<DadosDetalhamentoCategoria>> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        var page = service.listar(paginacao).map(DadosDetalhamentoCategoria::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{codigo}")
    public ResponseEntity detalhar(@PathVariable Long codigo) {
        var categoria = service.detalhar(codigo);
        return ResponseEntity.ok(new DadosDetalhamentoCategoria(categoria));
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizacaoCategoria dados) {
        var categoria = service.atualizar(dados);
        return ResponseEntity.ok(new DadosDetalhamentoCategoria(categoria));
    }

    @DeleteMapping("/{codigo}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long codigo) {
        service.excluir(codigo);
        return ResponseEntity.noContent().build();
    }
}
