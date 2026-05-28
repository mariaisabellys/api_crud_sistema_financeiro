# Estrutura do Banco de Dados - Projeto Financeiro

Este documento descreve as tabelas e o esquema do banco de dados MySQL utilizado pelo sistema.

## 🗄️ Tabelas

### `categoria`
Armazena as categorias de lançamentos financeiros.
- `codigo`: BIGINT(20) (PK, Auto-increment)
- `nome`: VARCHAR(50) (NOT NULL)

### `pessoa`
Armazena os dados das pessoas envolvidas nas transações.
- `codigo`: BIGINT(20) (PK, Auto-increment)
- `nome`: VARCHAR(50) (NOT NULL)
- `logradouro`: VARCHAR(30)
- `numero`: VARCHAR(30)
- `complemento`: VARCHAR(30)
- `bairro`: VARCHAR(30)
- `cep`: VARCHAR(30)
- `cidade`: VARCHAR(30)
- `estado`: VARCHAR(30)
- `ativo`: BOOLEAN (NOT NULL)

### `lancamento`
Armazena os registros de receitas e despesas.
- `codigo`: BIGINT(20) (PK, Auto-increment)
- `descricao`: VARCHAR(50) (NOT NULL)
- `data_vencimento`: DATE (NOT NULL)
- `data_pagamento`: DATE
- `valor`: DECIMAL(10,2) (NOT NULL)
- `observacao`: VARCHAR(100)
- `tipo`: VARCHAR(20) (NOT NULL) - (RECEITA, DESPESA)
- `codigo_categoria`: BIGINT(20) (FK para `categoria`)
- `codigo_pessoa`: BIGINT(20) (FK para `pessoa`)

## 🚀 Migrations (Flyway)
O histórico de evolução do banco de dados será mantido em arquivos `.sql` no diretório `src/main/resources/db/migration`.
- `V01__criar_e_registrar_categorias.sql`
- `V02__criar_tabela_pessoa.sql`
- `V03__criar_tabela_e_registrar_lancamentos.sql`
