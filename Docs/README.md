# Projeto Financeiro - Sistema de Controle de Lançamentos

Este é um projeto de back-end avançado utilizando Spring Boot para gerenciar um sistema financeiro de lançamentos de receitas e despesas.

## 📂 Documentação do Projeto

Acesse os documentos detalhados abaixo para entender a arquitetura, o modelo de dados e os requisitos do sistema:

1.  **[Arquitetura do Projeto](ARCHITECTURE.md)**: Visão geral das camadas e organização do código.
2.  **[Entidades do Domínio](ENTITIES.md)**: Detalhamento das classes de negócio.
3.  **[Modelo Relacional](RELATIONAL_MODEL.md)**: Explicação dos relacionamentos entre as tabelas.
4.  **[Endpoints da API](API_ENDPOINTS.md)**: Lista de recursos REST disponíveis.
5.  **[Estrutura do Banco de Dados](DATABASE.md)**: Definição das tabelas e tipos de dados.
6.  **[Anotações do Spring](SPRING_ANNOTATIONS.md)**: Guia de referência das tecnologias utilizadas.
7.  **[Especificação (Spec)](SPEC.md)**: Requisitos funcionais, casos de uso e critérios de sucesso.
8.  **[Plano de Projeto (Plan)](PLAN.md)**: Roadmap de desenvolvimento e tarefas.
9.  **[Guia de Implementação CRUD](GUIA_IMPLEMENTACAO_CRUD.md)**: Passo a passo técnico para o desenvolvimento.
10. **[Referência de Código](codigo.md)**: Blocos de código prontos para implementação.
11. **[Guia de Testes Insomnia](GUIA_TESTES_INSOMNIA.md)**: Como validar a aplicação após o desenvolvimento.

## 🚀 Evolução do Projeto

Este projeto passou por uma análise comparativa profunda com sistemas de referência para adotar padrões de maturidade REST, incluindo:
- **Tratamento Global de Erros** (`RestControllerAdvice`).
- **Paginação e Ordenação** nativas.
- **DTOs Especializados** (Java Records).
- **Exclusão Lógica** (Soft Delete) para preservação de dados.
- **Maturidade REST** (Uso correto de status codes e cabeçalhos).

## 🛠️ Tecnologias Utilizadas

- **Java 21**
- **Spring Boot 3.5.13**
- **Spring Data JPA**
- **MySQL**
- **Flyway** (Migrations)
- **Bean Validation** (Hibernate Validator)
- **Lombok**
- **Maven**

gemini --resume ace0508a-d4eb-48cf-899a-1b5ea694e094  

sessao dop gemini de criacao do gui de implementacao:
│  To resume this session: gemini --resume ea479caa-f16b-42ad-b7eb-c304876ee1fc
 To resume this session: gemini --resume 77d24f64-54a5-43cc-88c3-cad5d9ae8ac1 