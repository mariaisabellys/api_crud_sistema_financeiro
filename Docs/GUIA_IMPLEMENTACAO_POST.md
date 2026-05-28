# Guia de Implementação: Requisições POST - Sistema Financeiro

Este guia detalha o passo a passo para implementar a funcionalidade de cadastro (POST) no Sistema Financeiro, garantindo que a arquitetura esteja correta para persistência no banco de dados.

---

## 1. Configuração do Projeto e Banco de Dados

### Dependências Necessárias (Maven)
Certifique-se de que o seu `pom.xml` contenha:
- `Spring Web`
- `Spring Data JPA`
- `MySQL Driver`
- `Flyway Migration`
- `Validation` (Bean Validation)

### Banco de Dados (`application.properties`)
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/sistema_financeiro?createDatabaseIfNotExist=true&useTimezone=true&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=sua_senha
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
```

---

## 2. Camada de Entidades (`med.voll.api.entities`)

As entidades representam as tabelas no banco de dados.

### Categoria
```java
package med.voll.api.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "categoria")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @EqualsAndHashCode(of = "codigo")
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;
    private String nome;
}
```

### Pessoa e Endereço (Embedded)
```java
package med.voll.api.entities;

import jakarta.persistence.*;
import lombok.*;

@Embeddable
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Endereco {
    private String logradouro;
    private String numero;
    private String complemento;
    private String bairro;
    private String cep;
    private String cidade;
    private String estado;
}

@Entity
@Table(name = "pessoa")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @EqualsAndHashCode(of = "codigo")
public class Pessoa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;
    private String nome;
    private Boolean ativo;
    @Embedded
    private Endereco endereco;
}
```

### Lançamento e Enum TipoLancamento
```java
package med.voll.api.entities;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;

public enum TipoLancamento {
    RECEITA, DESPESA
}

@Entity
@Table(name = "lancamento")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @EqualsAndHashCode(of = "codigo")
public class Lancamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;
    private String descricao;

    @Column(name = "data_vencimento")
    private LocalDate dataVencimento;

    @Column(name = "data_pagamento")
    private LocalDate dataPagamento;

    private BigDecimal valor;
    private String observacao;

    @Enumerated(EnumType.STRING)
    private TipoLancamento tipo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "codigo_categoria")
    private Categoria categoria;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "codigo_pessoa")
    private Pessoa pessoa;
}
```

---

## 3. Camada de DTOs (`med.voll.api.dto`)

Utilizamos **Java Records** para receber os dados das requisições POST com as validações necessárias.

### DadosCadastroCategoria
```java
package med.voll.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record DadosCadastroCategoria(
    @NotBlank @Size(min = 3, max = 50) String nome
) {}
```

### DadosCadastroPessoa
```java
package med.voll.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroPessoa(
    @NotBlank String nome,
    @NotNull Boolean ativo,
    DadosEndereco endereco
) {}

public record DadosEndereco(
    String logradouro, String numero, String complemento,
    String bairro, String cep, String cidade, String estado
) {}
```

### DadosCadastroLancamento
```java
package med.voll.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import med.voll.api.entities.TipoLancamento;
import java.math.BigDecimal;
import java.time.LocalDate;

public record DadosCadastroLancamento(
    @NotBlank String descricao,
    @NotNull LocalDate dataVencimento,
    LocalDate dataPagamento,
    @NotNull BigDecimal valor,
    String observacao,
    @NotNull TipoLancamento tipo,
    @NotNull Long codigoCategoria,
    @NotNull Long codigoPessoa
) {}
```

---

## 4. Camada de Repositórios (`med.voll.api.repositories`)

```java
package med.voll.api.repositories;

import med.voll.api.entities.Categoria;
import med.voll.api.entities.Pessoa;
import med.voll.api.entities.Lancamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {}
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {}
public interface LancamentoRepository extends JpaRepository<Lancamento, Long> {}
```

---

## 5. Camada de Serviço (`med.voll.api.services`)

Essencial para validar regras de negócio antes de salvar e centralizar a lógica.

### CategoriaService
```java
package med.voll.api.services;

import med.voll.api.dto.DadosCadastroCategoria;
import med.voll.api.entities.Categoria;
import med.voll.api.repositories.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoriaService {
    @Autowired
    private CategoriaRepository repository;

    public Categoria salvar(DadosCadastroCategoria dados) {
        var categoria = new Categoria();
        categoria.setNome(dados.nome());
        return repository.save(categoria);
    }
}
```

### PessoaService
```java
package med.voll.api.services;

import med.voll.api.dto.DadosCadastroPessoa;
import med.voll.api.entities.Endereco;
import med.voll.api.entities.Pessoa;
import med.voll.api.repositories.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PessoaService {
    @Autowired
    private PessoaRepository repository;

    public Pessoa salvar(DadosCadastroPessoa dados) {
        var pessoa = new Pessoa();
        pessoa.setNome(dados.nome());
        pessoa.setAtivo(dados.ativo());
        
        if (dados.endereco() != null) {
            var endereco = new Endereco(
                dados.endereco().logradouro(),
                dados.endereco().numero(),
                dados.endereco().complemento(),
                dados.endereco().bairro(),
                dados.endereco().cep(),
                dados.endereco().cidade(),
                dados.endereco().estado()
            );
            pessoa.setEndereco(endereco);
        }

        return repository.save(pessoa);
    }
}
```

### LancamentoService
```java
package med.voll.api.services;

import med.voll.api.dto.DadosCadastroLancamento;
import med.voll.api.entities.Lancamento;
import med.voll.api.repositories.CategoriaRepository;
import med.voll.api.repositories.LancamentoRepository;
import med.voll.api.repositories.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LancamentoService {

    @Autowired
    private LancamentoRepository lancamentoRepository;
    @Autowired
    private PessoaRepository pessoaRepository;
    @Autowired
    private CategoriaRepository categoriaRepository;

    public Lancamento salvar(DadosCadastroLancamento dados) {
        var pessoa = pessoaRepository.findById(dados.codigoPessoa())
                .orElseThrow(() -> new RuntimeException("Pessoa não encontrada"));

        if (!pessoa.getAtivo()) {
            throw new RuntimeException("Pessoa inativa não pode realizar lançamentos");
        }

        var categoria = categoriaRepository.findById(dados.codigoCategoria())
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));

        var lancamento = new Lancamento();
        lancamento.setDescricao(dados.descricao());
        lancamento.setDataVencimento(dados.dataVencimento());
        lancamento.setDataPagamento(dados.dataPagamento());
        lancamento.setValor(dados.valor());
        lancamento.setObservacao(dados.observacao());
        lancamento.setTipo(dados.tipo());
        lancamento.setCategoria(categoria);
        lancamento.setPessoa(pessoa);

        return lancamentoRepository.save(lancamento);
    }
}
```

---

## 6. Camada de Controllers (`med.voll.api.controller`)

Endpoints para receber as requisições.

### CategoriaController
```java
package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.dto.DadosCadastroCategoria;
import med.voll.api.services.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
```

### PessoaController
```java
package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.dto.DadosCadastroPessoa;
import med.voll.api.services.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
```

### LancamentoController
```java
package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.dto.DadosCadastroLancamento;
import med.voll.api.services.LancamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
```

---

## 7. Exemplos para Teste no Insomnia

### POST /categorias
```json
{
  "nome": "Alimentação"
}
```

### POST /pessoas
```json
{
  "nome": "Zé Maria",
  "ativo": true,
  "endereco": {
    "logradouro": "Rua das Silvas",
    "numero": "25",
    "bairro": "Valentina",
    "cep": "58028-30",
    "cidade": "João Pessoa",
    "estado": "Paraíba"
  }
}
```

### POST /lancamentos
```json
{
  "descricao": "Compra de Supermercado",
  "dataVencimento": "2024-11-20",
  "valor": 350.50,
  "tipo": "DESPESA",
  "codigoCategoria": 1,
  "codigoPessoa": 1
}
```