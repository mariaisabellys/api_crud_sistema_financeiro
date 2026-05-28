# Modelo Relacional - Projeto Financeiro

Este documento descreve os relacionamentos entre as entidades do sistema.

## 📊 Entidades e Relacionamentos

### 1. Lançamento (`Lancamento`) e Categoria (`Categoria`)
- **Tipo**: Many-to-One (N:1)
- **Descrição**: Um lançamento pertence obrigatoriamente a uma categoria, mas uma categoria pode ter vários lançamentos associados.
- **Mapeamento**: `Lancamento` possui um atributo `categoria` com `@ManyToOne`. No banco de dados, `lancamento` terá a chave estrangeira `codigo_categoria`.

### 2. Lançamento (`Lancamento`) e Pessoa (`Pessoa`)
- **Tipo**: Many-to-One (N:1)
- **Descrição**: Cada lançamento está vinculado a uma pessoa (cliente ou fornecedor), enquanto uma pessoa pode estar associada a múltiplos lançamentos.
- **Mapeamento**: `Lancamento` possui um atributo `pessoa` com `@ManyToOne`. No banco de dados, `lancamento` terá a chave estrangeira `codigo_pessoa`.

### 3. Pessoa (`Pessoa`) e Endereço (`Endereco`)
- **Tipo**: Embedded (Composição)
- **Descrição**: O endereço é uma parte integrante da entidade Pessoa, não possuindo tabela própria separada.
- **Mapeamento**: `Endereco` usa a anotação `@Embeddable` e `Pessoa` usa `@Embedded`. Os campos do endereço são colunas na tabela `pessoa`.

## 🔄 Enums e Categorias

### Tipo de Lançamento (`TipoLancamento`)
Define se o valor está entrando ou saindo do caixa:
- `RECEITA`: Representa ganhos ou entradas de capital.
- `DESPESA`: Representa gastos, pagamentos ou saídas de capital.
