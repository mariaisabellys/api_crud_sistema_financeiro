# Entidades do Domínio - Projeto Financeiro

Este documento descreve as entidades principais do sistema de controle financeiro.

## Lancamento
Representa um registro de entrada ou saída financeira.

| Atributo | Tipo | Descrição |
| :--- | :--- | :--- |
| codigo | Long | Identificador único (Primary Key) |
| descricao | String | Descrição curta do lançamento |
| dataVencimento | LocalDate | Data em que o título vence |
| dataPagamento | LocalDate | Data em que o pagamento foi realizado (opcional) |
| valor | BigDecimal | Valor monetário do lançamento |
| observacao | String | Observações adicionais |
| tipo | TipoLancamento | Enum (RECEITA, DESPESA) |
| categoria | Categoria | Categoria associada ao lançamento |
| pessoa | Pessoa | Pessoa associada ao lançamento |

## Pessoa
Representa um cliente, fornecedor ou qualquer entidade que realize transações.

| Atributo | Tipo | Descrição |
| :--- | :--- | :--- |
| codigo | Long | Identificador único (Primary Key) |
| nome | String | Nome completo da pessoa |
| ativo | Boolean | Indica se a pessoa está ativa no sistema |
| endereco | Endereco | Objeto embutido com dados de localização |

## Endereco (Embedded)
Objeto de valor embutido na entidade `Pessoa`.

| Atributo | Tipo | Descrição |
| :--- | :--- | :--- |
| logradouro | String | Nome da rua/avenida |
| numero | String | Número do imóvel |
| complemento | String | Complemento do endereço |
| bairro | String | Bairro |
| cep | String | Código de Endereçamento Postal |
| cidade | String | Cidade |
| estado | String | Estado (UF) |

## Categoria
Classificação dos lançamentos (ex: Alimentação, Lazer, Salário).

| Atributo | Tipo | Descrição |
| :--- | :--- | :--- |
| codigo | Long | Identificador único (Primary Key) |
| nome | String | Nome da categoria |

## TipoLancamento (Enum)
Define a natureza do lançamento:
- `RECEITA`: Entrada de dinheiro.
- `DESPESA`: Saída de dinheiro.
