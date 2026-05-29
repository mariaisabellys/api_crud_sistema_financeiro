# Plano de Projeto Financeiro (Plan)

Este plano descreve as etapas para o desenvolvimento do Sistema de Controle Financeiro, divididas em tarefas incrementais.

## 🚀 Fase 1: Setup do Projeto
- **Task 1.1: Inicialização do Projeto** (Concluído)
- **Task 1.2: Configuração do Flyway** (Concluído)

## 🏗 Fase 2: Mapeamento de Entidades e DTOs
- **Task 2.1: Implementação das Entidades JPA**
  - Revisar classes `Categoria`, `Pessoa`, `Endereco` e `Lancamento`.
- **Task 2.2: Especialização dos DTOs (Records)**
  - Criar DTOs específicos para cada operação:
    - `DadosCadastro...`: Para criação (POST).
    - `DadosListagem...`: Para consultas resumidas (GET).
    - `DadosDetalhamento...`: Para exibição completa (GET por ID).
    - `DadosAtualizacao...`: Para edição (PUT).

## 🛠 Fase 3: Camada de Infraestrutura e Tratamento de Erros
- **Task 3.1: Configuração Global de Erros**
  - Criar o pacote `infra`.
  - Implementar `TratadorDeErros` com `@RestControllerAdvice`.
  - Mapear `404 Not Found` e `400 Bad Request` (com detalhamento de erros de validação).

## 📡 Fase 4: Desenvolvimento dos Endpoints (CRUD Completo)
- **Task 4.1: CRUD de Categorias**
  - Listar (GET), Buscar por ID (GET), Cadastrar (POST), Excluir (DELETE).
- **Task 4.2: CRUD de Pessoas**
  - Implementar listagem paginada.
  - Implementar atualização (PUT).
  - Implementar exclusão lógica (campo `ativo`).
- **Task 4.3: CRUD de Lançamentos**
  - Implementar listagem com filtros e paginação.
  - Implementar detalhamento e exclusão.

## ✅ Fase 5: Validação e Testes
- **Task 5.1: Teste das APIs via Insomnia**
- **Task 5.2: Documentação da API**
  - Atualizar `API_ENDPOINTS.md` com todos os novos métodos.
