# Documentação da Arquitetura do Projeto - Sistema Financeiro

Este documento descreve a arquitetura, a estrutura de pastas e a responsabilidade de cada classe no projeto **Sistema Financeiro**.

## 1. Visão Geral
O projeto é uma API REST desenvolvida com **Java** e **Spring Boot**, utilizando uma arquitetura em camadas para garantir a separação de responsabilidades, facilidade de manutenção e testabilidade.

### Tecnologias Utilizadas:
- **Java 17+**
- **Spring Boot 3**
- **Spring Data JPA**: Para persistência de dados.
- **Flyway**: Para migrações do banco de dados.
- **Lombok**: Para reduzir o código boilerplate (Getters, Setters, etc).
- **Jakarta Bean Validation**: Para validação de dados de entrada.

---

## 2. Estrutura de Camadas

A aplicação segue o padrão de camadas clássico do ecossistema Spring:

1.  **Controller (Web)**: Define os endpoints da API, recebe requisições HTTP e retorna respostas.
2.  **Service (Negócio)**: Contém a lógica de negócio e regras de validação.
3.  **Repository (Dados)**: Interface de comunicação com o banco de dados via JPA.
4.  **Entities (Modelo)**: Representação das tabelas do banco de dados como objetos Java.
5.  **DTO (Data Transfer Objects)**: Objetos para transferência de dados entre o cliente e o servidor.

---

## 3. Descrição dos Pacotes e Classes

### 3.1. Pacote: `sistema.financeiro.api`
-   `ApiApplication.java`: Classe principal que inicia o contexto do Spring Boot.

### 3.2. Pacote: `controller`
Responsável por lidar com as requisições HTTP.
-   `CategoriaController.java`: Endpoints para gerenciamento de categorias.
-   `LancamentoController.java`: Endpoints para gerenciamento de lançamentos financeiros.
-   `PessoaController.java`: Endpoints para gerenciamento de pessoas.

### 3.3. Pacote: `service`
Contém a inteligência do sistema.
-   `CategoriaService.java`: Lógica de negócio para categorias.
-   `LancamentoService.java`: Regras para salvar lançamentos, incluindo validação se a pessoa está ativa.
-   `PessoaService.java`: Lógica de negócio para pessoas.

### 3.4. Pacote: `repositories`
Interfaces que estendem `JpaRepository`.
-   `CategoriaRepository.java`: Acesso aos dados da tabela `categoria`.
-   `LancamentoRepository.java`: Acesso aos dados da tabela `lancamento`.
-   `PessoaRepository.java`: Acesso aos dados da tabela `pessoa`.

### 3.5. Pacote: `entities`
Mapeamento Objeto-Relacional (ORM).
-   `Categoria.java`: Representa a categoria do lançamento (ex: Lazer, Alimentação).
-   `Pessoa.java`: Representa o cliente/usuário do sistema.
-   `Lancamento.java`: Representa uma transação financeira (Receita ou Despesa).
-   `Endereco.java`: Classe `@Embeddable` com dados de endereço da pessoa.
-   `TipoLancamento.java`: Enumeração que define se o lançamento é `RECEITA` ou `DESPESA`.

### 3.6. Pacote: `dto`
Records Java utilizados para validação e transporte de dados.
-   `DadosCadastroCategoria.java`: Dados necessários para criar uma categoria.
-   `DadosCadastroLancamento.java`: Dados necessários para criar um lançamento.
-   `DadosCadastroPessoa.java`: Dados necessários para criar uma pessoa.
-   `DadosEndereco.java`: Estrutura de dados de endereço nos DTOs.
-   `DadosListagemLancamento.java`: Projeção simplificada de lançamentos para listagem.

---

## 4. Banco de Dados

O banco de dados é gerenciado via **Flyway**. As migrações estão em `src/main/resources/db/migration`:
-   `V01`: Criação e inserção inicial de categorias.
-   `V02`: Criação da tabela de pessoas e endereços.
-   `V03`: Criação da tabela de lançamentos com chaves estrangeiras para categoria e pessoa.

---

## 5. Fluxo de uma Requisição (Exemplo: Cadastro de Lançamento)

1.  O cliente envia um **POST** para `/lancamentos` com um JSON.
2.  O `LancamentoController` recebe o `DadosCadastroLancamento` (DTO) e valida os campos.
3.  O controller chama o `LancamentoService`.
4.  O `LancamentoService` verifica se a `Pessoa` associada existe e se está ativa.
5.  O service converte o DTO para a entidade `Lancamento`.
6.  O `LancamentoRepository` salva a entidade no banco de dados.
7.  A resposta é retornada ao cliente com o objeto criado e status **201 Created**.
