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
import sistema.financeiro.api.dto.DadosAtualizacaoLancamento;
import sistema.financeiro.api.dto.DadosCadastroLancamento;
import sistema.financeiro.api.dto.DadosDetalhamentoLancamento;
import sistema.financeiro.api.dto.DadosListagemLancamento;
import sistema.financeiro.api.service.LancamentoService;

@RestController
@RequestMapping("/lancamentos")
public class LancamentoController {

    @Autowired
    private LancamentoService service;

    @PostMapping
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroLancamento dados, UriComponentsBuilder uriBuilder) {
        var lancamento = service.salvar(dados);
        var uri = uriBuilder.path("/lancamentos/{codigo}").buildAndExpand(lancamento.getCodigo()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoLancamento(lancamento));
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemLancamento>> listar(@PageableDefault(size = 10, sort = {"dataVencimento"}) Pageable paginacao) {
        var page = service.listar(paginacao).map(DadosListagemLancamento::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{codigo}")
    public ResponseEntity detalhar(@PathVariable Long codigo) {
        var lancamento = service.detalhar(codigo);
        return ResponseEntity.ok(new DadosDetalhamentoLancamento(lancamento));
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizacaoLancamento dados) {
        var lancamento = service.atualizar(dados);
        return ResponseEntity.ok(new DadosDetalhamentoLancamento(lancamento));
    }

    @DeleteMapping("/{codigo}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long codigo) {
        service.excluir(codigo);
        return ResponseEntity.noContent().build();
    }
}
