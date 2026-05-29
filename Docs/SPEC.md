# Especificação do Projeto Financeiro (Spec)

Este documento detalha os requisitos técnicos e funcionais para o Sistema de Controle Financeiro, alinhado aos padrões RESTful.

## 📌 Requisitos Funcionais (RF)

- **RF01: Gerenciamento de Categorias**
  - Cadastro, consulta e exclusão de categorias.
- **RF02: Gerenciamento de Pessoas**
  - Cadastro completo com endereço.
  - Exclusão lógica (inativação) para preservar o histórico de lançamentos.
- **RF03: Gerenciamento de Lançamentos**
  - Registro de receitas e despesas vinculadas a categorias e pessoas.
  - Validação rigorosa: pessoa deve estar ativa.
- **RF04: Consultas Paginadas**
  - Listagem de lançamentos e pessoas deve suportar paginação e ordenação via URL.

## 🛠 Requisitos Técnicos e Padrões

- **Padrão de Respostas HTTP:**
  - `201 Created`: Para cadastros bem-sucedidos, retornando a URI no cabeçalho `Location`.
  - `200 OK`: Para consultas e atualizações.
  - `204 No Content`: Para exclusões.
  - `404 Not Found`: Quando um ID informado não existe.
  - `400 Bad Request`: Para erros de validação de dados ou regras de negócio.
- **Segurança e Abstração:**
  - Uso obrigatório de **Records (DTOs)** para entrada e saída de dados.
  - Entidades JPA nunca devem ser expostas diretamente nos Controllers.
- **Tratamento de Erros:**
  - Centralizado em um `TratadorDeErros` global.
  - Erros de validação (Bean Validation) devem retornar uma lista detalhada com campo e mensagem.

## ⚠️ Regras de Negócio e Casos de Borda

- **Validação de Pessoa:** Um lançamento só pode ser salvo se o `codigoPessoa` referenciar uma pessoa existente e com `ativo = true`.
- **Integridade de Dados:** O sistema deve utilizar `@Transactional` em operações de escrita nos Services.
- **Campos Obrigatórios:** Validados via `@NotBlank`, `@NotNull`, `@Positive`, etc.

## ✅ Critérios de Aceite

- API segue os níveis de maturidade REST (uso correto de verbos e status).
- Código limpo e organizado em camadas (Controller -> Service -> Repository).
- Banco de dados gerenciado via Flyway.
- Feedback de erro claro e padronizado para o cliente da API.
