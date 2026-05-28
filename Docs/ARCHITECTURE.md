# Arquitetura do Projeto Financeiro

A aplicação segue o padrão de arquitetura em camadas do Spring Boot, garantindo a separação de responsabilidades.

## Camadas

### 1. Controller (`med.voll.api.controller`)
Responsável por expor os endpoints REST, receber as requisições HTTP, validar os dados de entrada e retornar a resposta adequada ao cliente.
- `LancamentoController`: Gerencia as requisições relacionadas aos lançamentos financeiros (CRUD).
- `PessoaController`: Gerencia o cadastro e consulta de pessoas.
- `CategoriaController`: Fornece operações básicas para categorias.

### 2. Service (`med.voll.api.services`)
Contém a lógica de negócio complexa. Intermedia a comunicação entre os controllers e os repositórios.
- `LancamentoService`: Valida regras de negócio para lançamentos (ex: garantir que a pessoa está ativa).
- `PessoaService`: Lida com regras de negócio para pessoas.

### 3. Repository (`med.voll.api.repositories`)
Interface que estende `JpaRepository`, permitindo a interação com o banco de dados via Spring Data JPA.
- `LancamentoRepository`: Abstração para operações no banco de dados para a entidade `Lancamento`.
- `PessoaRepository`: Abstração para operações no banco de dados para a entidade `Pessoa`.
- `CategoriaRepository`: Abstração para operações no banco de dados para a entidade `Categoria`.

### 4. Entities (`med.voll.api.entities`)
Classes que representam as tabelas do banco de dados (JPA Entities).
- `Lancamento`: Representa a tabela `tb_lancamento`.
- `Pessoa`: Representa a tabela `tb_pessoa`.
- `Categoria`: Representa a tabela `tb_categoria`.
- `Endereco`: Classe `@Embeddable` que contém os dados de endereço.

### 5. DTO - Data Transfer Objects (`med.voll.api.dto`)
Utilizados para trafegar dados entre as camadas da aplicação e a API, evitando a exposição direta das entidades da JPA e permitindo validações específicas via Bean Validation.
- `DadosCadastroLancamento`: Dados recebidos na criação de um lançamento.
- `DadosCadastroPessoa`: Dados para cadastrar uma nova pessoa.
- `DadosListagemLancamento`: Dados retornados na consulta simplificada de lançamentos.
- `DadosCadastroCategoria`: Dados para cadastrar uma nova categoria.
