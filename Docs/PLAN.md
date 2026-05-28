# Plano de Projeto Financeiro (Plan)

Este plano descreve as etapas para o desenvolvimento do Sistema de Controle Financeiro, divididas em tarefas incrementais.

## 🚀 Fase 1: Setup do Projeto
- **Task 1.1: Inicialização do Projeto**
  - Criar o projeto no Spring Initializr (Maven, Java 21, Spring Boot 3.5.13, Spring Web, Spring Data JPA, MySQL Driver, Flyway, Validation, Lombok).
  - Configurar `application.properties` with the MySQL database details.
- **Task 1.2: Configuração do Flyway**
  - Criar as migrations SQL iniciais (`V01__criar_e_registrar_categorias.sql`, `V02__criar_tabela_pessoa.sql`, etc.) para espelhar o esquema do banco de dados.

## 🏗 Fase 2: Mapeamento de Entidades e DTOs
- **Task 2.1: Implementação das Entidades JPA**
  - Criar classes `Categoria`, `Pessoa`, `Endereco` (Embedded) e `Lancamento`.
  - Configurar os relacionamentos `@ManyToOne` e `@Embedded`.
- **Task 2.2: Criação dos DTOs (Records)**
  - Criar Records para entrada de dados (Cadastro) e saída de dados (Listagem), utilizando anotações de validação (`@NotBlank`, `@NotNull`, etc.).

## 🛠 Fase 3: Desenvolvimento da Camada de Persistência e Negócio
- **Task 3.1: Interfaces Repository**
  - Criar interfaces que estendem `JpaRepository` para cada entidade principal.
- **Task 3.2: Implementação da Camada Service**
  - Criar classes de serviço para concentrar a lógica de negócio (ex: validação de pessoa ativa antes do lançamento).

## 📡 Fase 4: Desenvolvimento dos Endpoints (Controllers)
- **Task 4.1: CRUD de Categorias e Pessoas**
  - Implementar endpoints para listar, buscar por código, cadastrar, atualizar e excluir.
- **Task 4.2: CRUD de Lançamentos**
  - Implementar o cadastro de lançamentos com validações.
  - Implementar listagem com paginação utilizando `Pageable`.

## ✅ Fase 5: Validação e Testes
- **Task 5.1: Teste das APIs via Insomnia/Postman**
  - Validar todos os cenários de sucesso (Status 200/201).
  - Validar cenários de erro e mensagens de validação (Status 400/404).
- **Task 5.2: Revisão Final**
  - Verificar se a estrutura de pastas e os nomes de pacotes estão seguindo o padrão estabelecido.
