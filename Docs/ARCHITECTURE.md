# Arquitetura do Projeto Financeiro

A aplicação segue o padrão de arquitetura em camadas do Spring Boot, com foco em **Clean Code**, **Surgical Updates** (lógica de negócio nas entidades) e **Safe Delete** (exclusão lógica).

## Camadas e Padrões

### 1. Controller (`sistema.financeiro.api.controller`)
Responsável por expor os endpoints REST.
- Utiliza `ResponseEntity` para controle total das respostas HTTP.
- Recebe e valida DTOs via `@Valid`.
- Delega a orquestração para a camada de Service.

### 2. Service (`sistema.financeiro.api.service`)
Orquestra as operações e garante a transacionalidade (`@Transactional`).
- Realiza validações de regra de negócio (ex: verificar se pessoa está ativa antes de um lançamento).
- Não contém lógica de atualização de campos (delegada para as Entities).

### 3. Repository (`sistema.financeiro.api.repositories`)
Interface `JpaRepository` para persistência.
- Implementa métodos customizados como `findAllByAtivoTrue` para suportar o Safe Delete nas listagens.

### 4. Entities (`sistema.financeiro.api.entities`)
Onde reside a lógica de alteração de estado (**Rich Domain Model**).
- Métodos `atualizarInformacoes(...)`: Recebem DTOs e atualizam apenas campos permitidos.
- Método `excluir()`: Altera o atributo `ativo` para `false`.
- Construtores específicos para DTOs de cadastro.

### 5. DTO - Data Transfer Objects (`sistema.financeiro.api.dto`)
Utiliza **Java Records** para imutabilidade e concisão.
- `DadosCadastro...`: Para criação de novos recursos.
- `DadosAtualizacao...`: Para atualizações (PUT), contendo o ID obrigatório.
- `DadosDetalhamento...`: Para retornos detalhados.
- `DadosListagem...`: Para projeções otimizadas em listas paginadas.

### 6. Infrastructure (`sistema.financeiro.api.infra`)
Transversal à aplicação.
- `TratadorDeErros`: `@RestControllerAdvice` para capturar exceções globalmente (404 para `EntityNotFoundException`, 400 para erros de validação).

### 7. Database Migrations (`src/main/resources/db/migration`)
Controle de versão do esquema do banco de dados via **Flyway**.
- Garante que a estrutura (incluindo campos de exclusão lógica) seja idêntica em todos os ambientes.
