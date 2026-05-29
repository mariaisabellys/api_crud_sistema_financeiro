# Guia de Implementação: CRUD Completo e Seguro - Sistema Financeiro

Este guia descreve os padrões técnicos adotados para garantir um CRUD robusto, seguindo o modelo de maturidade REST e Safe Delete.

---

## 1. Lógica de Negócio nas Entidades (Rich Domain)
Evite "setters" espalhados pelo Service. Centralize a atualização na entidade.

```java
// Na Entidade Pessoa
public void atualizarInformacoes(DadosAtualizacaoPessoa dados) {
    if (dados.nome() != null) this.nome = dados.nome();
    if (dados.endereco() != null) this.endereco.atualizarInformacoesEndereco(dados.endereco());
}
```

## 2. Safe Delete (Exclusão Lógica)
Nunca remova fisicamente registros que possuem vínculos (como Categoria e Pessoa vinculadas a Lançamentos).

1. Adicione o campo `Boolean ativo` na entidade e no banco.
2. No Service, chame `entidade.excluir()` (que define `ativo = false`).
3. No Repository, use `findAllByAtivoTrue` para as listagens.

## 3. Tratamento de Erros Padronizado
Utilize o `TratadorDeErros` para converter exceções do JPA/Bean Validation em respostas HTTP semânticas.

- `EntityNotFoundException` -> `404 Not Found`
- `MethodArgumentNotValidException` -> `400 Bad Request` com lista de campos e mensagens.

## 4. Records como DTOs
Sempre utilize Java Records para DTOs. Eles são imutáveis, possuem `equals`/`hashCode` automáticos e são perfeitos para transporte de dados.

## 5. Transacionalidade
Todo método que realiza escrita no banco (salvar, atualizar, excluir) deve ser anotado com `@Transactional` (do pacote `jakarta.transaction` ou `org.springframework.transaction`).

## 6. Padronização de Retorno (POST)
Sempre retorne `201 Created`, o corpo com o objeto detalhado e o cabeçalho `Location`.

```java
var uri = uriBuilder.path("/recurso/{id}").buildAndExpand(objeto.getId()).toUri();
return ResponseEntity.created(uri).body(new DadosDetalhamento(objeto));
```
