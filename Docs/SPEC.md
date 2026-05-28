# Especificação do Projeto Financeiro (Spec)

Este documento detalha os requisitos, casos de uso e critérios de sucesso para o Sistema de Controle Financeiro.

## 📌 Requisitos Funcionais (RF)

- **RF01: Gerenciamento de Categorias**
  - O sistema deve permitir o cadastro de categorias de lançamentos.
- **RF02: Gerenciamento de Pessoas**
  - O sistema deve permitir o cadastro de pessoas com seus respectivos endereços.
  - O sistema deve permitir ativar/desativar pessoas.
- **RF03: Gerenciamento de Lançamentos**
  - O sistema deve permitir o registro de receitas e despesas.
  - Cada lançamento deve estar obrigatoriamente vinculado a uma categoria e a uma pessoa ativa.
- **RF04: Listagem e Paginação**
  - O sistema deve retornar listas paginadas para lançamentos e pessoas.
- **RF05: Atualização e Remoção**
  - O sistema deve permitir a atualização de dados cadastrais.
  - O sistema deve permitir a exclusão de registros.

## 🛠 Casos de Uso (UC)

- **UC01: Registrar uma Despesa**
  - O usuário informa a descrição, data de vencimento, valor, tipo (DESPESA), categoria e a pessoa responsável.
  - O sistema valida se a pessoa informada existe e está ativa.
  - O sistema persiste a despesa no banco de dados.
- **UC02: Consultar Lançamentos com Paginação**
  - O usuário solicita a lista de lançamentos com parâmetros de página e tamanho.
  - O sistema retorna uma página de resultados contendo os dados essenciais dos lançamentos.
- **UC03: Desativar uma Pessoa**
  - O usuário solicita a inativação de uma pessoa pelo código.
  - O sistema altera o campo `ativo` para `false`.

## ⚠️ Casos de Borda (Edge Cases)

- **Pessoa Inativa:** Tentar realizar um lançamento para uma pessoa que existe mas está com o status `ativo = false`. O sistema deve retornar um erro de regra de negócio.
- **Data Retroativa:** Tentar cadastrar um lançamento com data de vencimento no passado (dependendo da regra de negócio, pode ser permitido ou bloqueado por `@FutureOrPresent`).
- **Valor Negativo:** Tentar cadastrar um lançamento com valor menor ou igual a zero. O sistema deve validar via `@Positive`.
- **Categoria Inexistente:** Tentar vincular um lançamento a um código de categoria que não consta na base de dados.

## ✅ Critérios de Sucesso

- O sistema persiste corretamente os dados no banco de dados MySQL via JPA.
- Todas as APIs retornam códigos de status HTTP apropriados (201 para criação, 200 para sucesso, 404 para não encontrado, 400 para erro de validação).
- As validações de Bean Validation bloqueiam entradas de dados inconsistentes antes de chegarem à camada de persistência.
- O mapeamento objeto-relacional reflete fielmente o diagrama de classes proposto.
- O histórico de alterações do banco de dados é gerenciado corretamente via Flyway Migrations.
