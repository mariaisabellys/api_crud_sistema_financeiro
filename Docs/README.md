# Documentação Técnica - Sistema de Controle Financeiro

Bem-vindo à central de documentação do **Projeto Financeiro**. Este repositório contém uma API REST robusta desenvolvida com Spring Boot para gestão de lançamentos, pessoas e categorias.

## 📌 Visão Geral

O projeto foi construído seguindo os princípios de **Clean Code** e **Arquitetura em Camadas**, garantindo manutenibilidade e escalabilidade. Abaixo, você encontrará o índice detalhado de toda a documentação técnica produzida durante o desenvolvimento.

## 📂 Índice de Documentação

### 🏗️ Arquitetura e Design
*   **[Arquitetura do Projeto](ARCHITECTURE.md)**: Detalhamento da organização em pastas (Controller, Service, Repository, DTO, Entity).
*   **[Especificação Funcional (SPEC)](SPEC.md)**: Regras de negócio, casos de uso e requisitos do sistema.
*   **[Anotações Spring Boot](SPRING_ANNOTATIONS.md)**: Guia de referência das annotations utilizadas no projeto.

### 🗄️ Dados e Modelagem
*   **[Modelo Relacional](RELATIONAL_MODEL.md)**: Diagrama e explicação das relações entre tabelas.
*   **[Estrutura do Banco de Dados](DATABASE.md)**: Tipos de dados, constraints e definições SQL.
*   **[Entidades do Domínio](ENTITIES.md)**: Mapeamento objeto-relacional (JPA) das classes principais.

### 🚀 Implementação e API
*   **[Endpoints da API](API_ENDPOINTS.md)**: Catálogo completo de rotas, métodos HTTP e exemplos de payload.
*   **[Guia de Implementação CRUD](GUIA_IMPLEMENTACAO_CRUD.md)**: Passo a passo técnico seguido para a construção das funcionalidades.
*   **[Referência de Código](codigo.md)**: Snippets e lógica central utilizada nos Services e Controllers.

### 🧪 Testes e Validação
*   **[Guia de Testes Insomnia](GUIA_TESTES_INSOMNIA.md)**: Instruções para importar e testar a API manualmente.
*   **[Exemplos de Payload](Informando%20valores%20no%20Insomnia.java)**: Referência rápida para envio de JSON no corpo das requisições.

## 🛠️ Destaques da Implementação

Nesta versão final, foram consolidados os seguintes padrões de mercado:

1.  **Maturidade REST**: Uso correto dos verbos HTTP, Status Codes (200, 201, 204, 400, 404) e negociação de conteúdo.
2.  **Tratamento Global de Erros**: Centralizado na classe `TratadorDeErros` usando `@RestControllerAdvice`.
3.  **Soft Delete (Exclusão Lógica)**: Implementado através do campo `ativo`, preservando a integridade referencial histórica.
4.  **Paginação e Ordenação**: Suporte nativo do Spring Data JPA em todos os endpoints de listagem.
5.  **Validação de Dados**: Uso intensivo de Bean Validation (`@NotNull`, `@NotBlank`, `@Valid`) para garantir a consistência das entradas.
6.  **DTOs com Java Records**: Utilização de Records para transferência de dados imutáveis e código mais limpo.
7.  **Migrations com Flyway**: Controle de versão do esquema do banco de dados MySQL.

## 🔐 Segurança e Credenciais

Para evitar a exposição de dados sensíveis (como senhas de banco de dados) no controle de versão, o projeto utiliza o padrão de **Variáveis de Ambiente** do Spring Boot.

*   **Configuração**: No arquivo `application.properties`, as chaves de conexão utilizam a sintaxe `${VARIAVEL:padrao}`.
*   **Variáveis Suportadas**: `DB_URL`, `DB_USER` e `DB_PASSWORD`.
*   **Vantagem**: Permite que cada desenvolvedor ou ambiente de produção tenha suas próprias credenciais sem precisar alterar o código-fonte.

---
*Este documento serve como guia principal para desenvolvedores e revisores do projeto.*
 