# Referência de Código: Implementação Completa do CRUD

Este documento contém todos os blocos de código necessários para atualizar o projeto financeiro, organizados por pacotes e classes.

---

## 1. Pacote `infra` (Tratamento de Erros)

Crie este pacote em `src/main/java/sistema/financeiro/api/infra/` e adicione a classe abaixo.

### `TratadorDeErros.java`
```java
package sistema.financeiro.api.infra;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TratadorDeErros {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity tratarErro404() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity tratarErro400(MethodArgumentNotValidException ex) {
        var erros = ex.getFieldErrors();
        return ResponseEntity.badRequest().body(erros.stream().map(DadosErroValidacao::new).toList());
    }

    private record DadosErroValidacao(String campo, String mensagem) {
        public DadosErroValidacao(FieldError erro) {
            this(erro.getField(), erro.getDefaultMessage());
        }
    }
}
```

---

## 2. Pacote `dto` (Novos Records)

Adicione os seguintes arquivos em `src/main/java/sistema/financeiro/api/dto/`.

### `DadosDetalhamentoPessoa.java`
```java
package sistema.financeiro.api.dto;

import sistema.financeiro.api.entities.Pessoa;

public record DadosDetalhamentoPessoa(Long codigo, String nome, Boolean ativo, DadosEndereco endereco) {
    public DadosDetalhamentoPessoa(Pessoa pessoa) {
        this(pessoa.getCodigo(), pessoa.getNome(), pessoa.getAtivo(), 
             new DadosEndereco(pessoa.getEndereco().getLogradouro(), 
                               pessoa.getEndereco().getNumero(), 
                               pessoa.getEndereco().getComplemento(), 
                               pessoa.getEndereco().getBairro(), 
                               pessoa.getEndereco().getCep(), 
                               pessoa.getEndereco().getCidade(), 
                               pessoa.getEndereco().getEstado()));
    }
}
```

### `DadosDetalhamentoLancamento.java`
```java
package sistema.financeiro.api.dto;

import sistema.financeiro.api.entities.Lancamento;
import sistema.financeiro.api.entities.TipoLancamento;
import java.math.BigDecimal;
import java.time.LocalDate;

public record DadosDetalhamentoLancamento(Long codigo, String descricao, LocalDate dataVencimento, LocalDate dataPagamento, BigDecimal valor, String observacao, TipoLancamento tipo, String categoria, String pessoa) {
    public DadosDetalhamentoLancamento(Lancamento lancamento) {
        this(lancamento.getCodigo(), lancamento.getDescricao(), lancamento.getDataVencimento(), lancamento.getDataPagamento(), lancamento.getValor(), lancamento.getObservacao(), lancamento.getTipo(), lancamento.getCategoria().getNome(), lancamento.getPessoa().getNome());
    }
}
```

### `DadosAtualizacaoPessoa.java`
```java
package sistema.financeiro.api.dto;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoPessoa(
    @NotNull Long codigo,
    String nome,
    Boolean ativo,
    DadosEndereco endereco
) {}
```

---

## 3. Pacote `service` (Atualizações)

Atualize os serviços para incluir `@Transactional` e novos métodos.

### `PessoaService.java`
**Alteração:** Adicionar `@Transactional` e métodos `detalhar`, `atualizar` e `excluir`.

```java
@Service
public class PessoaService {
    @Autowired
    private PessoaRepository repository;

    @Transactional
    public Pessoa salvar(DadosCadastroPessoa dados) { /* ... código existente ... */ }

    public Page<Pessoa> listar(Pageable paginacao) {
        return repository.findAll(paginacao);
    }

    public Pessoa detalhar(Long codigo) {
        return repository.getReferenceById(codigo);
    }

    @Transactional
    public Pessoa atualizar(DadosAtualizacaoPessoa dados) {
        var pessoa = repository.getReferenceById(dados.codigo());
        if (dados.nome() != null) pessoa.setNome(dados.nome());
        if (dados.ativo() != null) pessoa.setAtivo(dados.ativo());
        // Adicionar lógica de atualização de endereço se necessário
        return pessoa;
    }

    @Transactional
    public void excluir(Long codigo) {
        var pessoa = repository.getReferenceById(codigo);
        pessoa.setAtivo(false); // Exclusão lógica
    }
}
```

### `LancamentoService.java`
**Alteração:** Adicionar `@Transactional` e lógica de listagem/detalhamento.

```java
@Service
public class LancamentoService {
    // ... repositories ...

    @Transactional
    public Lancamento salvar(DadosCadastroLancamento dados) { /* ... código existente ... */ }

    public Page<Lancamento> listar(Pageable paginacao) {
        return lancamentoRepository.findAll(paginacao);
    }

    public Lancamento detalhar(Long codigo) {
        return lancamentoRepository.getReferenceById(codigo);
    }

    @Transactional
    public void excluir(Long codigo) {
        lancamentoRepository.deleteById(codigo);
    }
}
```

---

## 4. Pacote `controller` (Refatoração REST)

Atualize os controladores para usar `ResponseEntity` e `UriComponentsBuilder`.

### `PessoaController.java`
```java
@RestController
@RequestMapping("/pessoas")
public class PessoaController {

    @Autowired
    private PessoaService service;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroPessoa dados, UriComponentsBuilder uriBuilder) {
        var pessoa = service.salvar(dados);
        var uri = uriBuilder.path("/pessoas/{codigo}").buildAndExpand(pessoa.getCodigo()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoPessoa(pessoa));
    }

    @GetMapping
    public ResponseEntity<Page<DadosDetalhamentoPessoa>> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        var page = service.listar(paginacao).map(DadosDetalhamentoPessoa::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{codigo}")
    public ResponseEntity detalhar(@PathVariable Long codigo) {
        var pessoa = service.detalhar(codigo);
        return ResponseEntity.ok(new DadosDetalhamentoPessoa(pessoa));
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizacaoPessoa dados) {
        var pessoa = service.atualizar(dados);
        return ResponseEntity.ok(new DadosDetalhamentoPessoa(pessoa));
    }

    @DeleteMapping("/{codigo}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long codigo) {
        service.excluir(codigo);
        return ResponseEntity.noContent().build();
    }
}
```

### `LancamentoController.java`
```java
@RestController
@RequestMapping("/lancamentos")
public class LancamentoController {

    @Autowired
    private LancamentoService service;

    @PostMapping
    @Transactional
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

    @DeleteMapping("/{codigo}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long codigo) {
        service.excluir(codigo);
        return ResponseEntity.noContent().build();
    }
}
```

---

## 5. Entidades (Ajustes)

### `Pessoa.java`
Certifique-se de que o campo `ativo` existe e que a classe está anotada corretamente.

### `Lancamento.java`
Certifique-se de que os relacionamentos estão como `LAZY`:
```java
@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "codigo_categoria")
private Categoria categoria;

@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "codigo_pessoa")
private Pessoa pessoa;
```
