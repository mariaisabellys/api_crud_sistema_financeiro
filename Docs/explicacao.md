# Documentação de Lógica e Arquitetura - Sistema Financeiro

Este documento explica a estrutura, as anotações e o fluxo de execução da API de Sistema Financeiro.

## 1. Arquitetura do Projeto

O projeto segue o padrão de **Arquitetura em Camadas**, que separa as responsabilidades para facilitar a manutenção e evolução do código:

*   **Controller (API)**: Porta de entrada da aplicação. Recebe as requisições HTTP, valida os dados e retorna as respostas.
*   **Service**: Camada de lógica de negócio. É onde as regras do sistema são aplicadas antes de persistir ou recuperar dados.
*   **Repository**: Camada de persistência. Faz a ponte entre o código Java e o Banco de Dados utilizando o Spring Data JPA.
*   **Entity**: Representa as tabelas do banco de dados como objetos Java.
*   **DTO (Data Transfer Object)**: Objetos simples usados para transportar dados entre o cliente (Insomnia/Frontend) e a API, evitando expor as entidades diretamente.

---

## 2. Fluxo de Chamada (Ordem de Execução)

Sempre que você faz uma requisição no Insomnia (ex: `GET /pessoas/2`), o fluxo segue esta ordem:

1.  **Client (Insomnia)** -> Envia requisição HTTP.
2.  **Controller** -> O Spring identifica o método mapeado (ex: `@GetMapping`). Se houver dados chegando, ele usa um **DTO** para recebê-los.
3.  **Service** -> O Controller chama um método no Service. Aqui, o sistema pode verificar regras (ex: se o lançamento é maior que zero).
4.  **Repository** -> O Service chama o Repository para buscar ou salvar no banco de dados.
5.  **Database** -> O banco processa a query SQL.
6.  **Retorno** -> O caminho se inverte: Repository -> Service -> Controller (que converte a Entidade em um DTO de detalhamento) -> Client.

---

## 3. Principais Anotações e Seus Porquês

### No Controller
*   `@RestController`: Indica que a classe é um componente do Spring que lida com requisições REST e retorna JSON.
*   `@RequestMapping("/...")`: Define a rota base para todos os métodos daquela classe.
*   `@PostMapping`, `@GetMapping`, `@PutMapping`, `@DeleteMapping`: Definem o verbo HTTP que o método aceita.
*   `@RequestBody`: Converte o JSON que vem no corpo da requisição em um objeto Java (DTO).
*   `@PathVariable`: Captura valores variáveis na URL (ex: o `id` em `/pessoas/{id}`).
*   `@Valid`: Aciona as validações do Bean Validation (como `@NotBlank` ou `@NotNull`) nos DTOs.

### No Service
*   `@Service`: Marca a classe como um componente de serviço para que o Spring possa injetá-la onde for necessário.
*   `@Transactional`: Garante que a operação no banco de dados seja atômica. Se algo der errado no meio do processo, o Spring faz um "rollback" (desfaz as alterações) para manter a integridade dos dados.

### No Repository
*   `@Repository`: Indica que é um componente de acesso a dados. Ao estender `JpaRepository`, ganhamos métodos prontos como `save()`, `findAll()`, `findById()`, etc.

### Nas Entidades (Entities)
*   `@Entity`: Diz ao JPA que aquela classe representa uma tabela no banco de dados.
*   `@Table(name = "...")`: Especifica o nome exato da tabela no banco.
*   `@Id` e `@GeneratedValue`: Definem a chave primária e como ela é gerada (geralmente `IDENTITY` para autoincremento).

---

## 4. Relacionamento entre as Classes

As classes se comunicam através de **Injeção de Dependência**, utilizando a anotação `@Autowired`.

*   **PessoaController** possui um `@Autowired` de **PessoaService**.
*   **PessoaService** possui um `@Autowired` de **PessoaRepository**.

Isso permite que uma camada chame a próxima sem precisar instanciar manualmente os objetos (`new PessoaService()`), deixando o Spring gerenciar o ciclo de vida dos componentes.

### Exemplo de Relacionamento de Dados:
A entidade `Lancamento` possui um relacionamento com `Categoria` e `Pessoa`.
*   Um Lançamento pertence a **Uma** Categoria (`@ManyToOne`).
*   Um Lançamento pertence a **Uma** Pessoa (`@ManyToOne`).

---

## 5. Por que usar DTOs?

Você notará que usamos `DadosCadastroPessoa` para receber dados e `DadosDetalhamentoPessoa` para enviar. Fazemos isso porque:
1.  **Segurança**: Não expomos campos sensíveis da Entidade que não devem ser vistos pelo cliente.
2.  **Flexibilidade**: Podemos mudar a estrutura do banco de dados sem quebrar o contrato com quem usa a API.
3.  **Validação**: Podemos colocar regras de validação específicas para a criação que não existem na listagem.
