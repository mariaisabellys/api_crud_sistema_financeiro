# Notações do Spring Boot e JPA

Este documento explica as principais anotações utilizadas no Projeto Financeiro para facilitar o desenvolvimento e a integração com o banco de dados.

## Anotações Core do Spring

### `@RestController`
Indica que a classe é um controlador REST, onde os métodos retornam dados diretamente no corpo da resposta (geralmente JSON). Combina `@Controller` e `@ResponseBody`.

### `@RequestMapping("/url")`
Define a URL base para todos os endpoints dentro de uma classe Controller.

### `@Autowired`
Habilita a injeção de dependência automática. O Spring encontra o componente correspondente e o injeta na classe.

### `@Service`
Marca uma classe como pertencente à camada de serviço (lógica de negócio).

### `@Repository`
Indica que a classe é um componente de acesso a dados (camada de persistência).

## Anotações JPA (Persistência)

### `@Entity`
Define que a classe é uma entidade mapeada para uma tabela no banco de dados.

### `@Table(name = "nome_tabela")`
Especifica o nome da tabela no banco de dados que a entidade representa.

### `@Id`
Marca o atributo como a chave primária (Primary Key) da entidade.

### `@GeneratedValue(strategy = GenerationType.IDENTITY)`
Indica que a chave primária será gerada automaticamente pelo banco de dados (ex: Auto-increment).

### `@ManyToOne`
Define um relacionamento de muitos para um (N:1) entre a entidade atual e outra.

### `@JoinColumn(name = "coluna_fk")`
Especifica o nome da coluna de chave estrangeira (Foreign Key) na tabela.

### `@Enumerated(EnumType.STRING)`
Persiste o nome do valor do Enum como uma String no banco de dados, em vez de seu índice numérico.

### `@Embeddable`
Marca uma classe cujas instâncias são armazenadas como parte de uma entidade proprietária.

### `@Embedded`
Marca um atributo em uma entidade como sendo um objeto embutido (de uma classe `@Embeddable`).

## Anotações de Validação (Bean Validation)

### `@Valid`
Ativa a validação automática do objeto enviado no corpo da requisição com base nas anotações de validação nos campos do DTO.

### `@NotBlank`
Valida se o campo String não é nulo e não possui apenas espaços em branco.

### `@NotNull`
Valida se o campo não é nulo.

### `@FutureOrPresent`
Garante que a data informada seja a data atual ou uma data futura.

## Anotações de Requisição HTTP

### `@PostMapping`, `@GetMapping`, `@PutMapping`, `@DeleteMapping`
Mapeiam requisições HTTP para métodos específicos do controlador.

### `@RequestBody`
Indica que o objeto de entrada deve ser desserializado a partir do corpo (body) da requisição JSON.

### `@PathVariable`
Indica que um parâmetro do método será preenchido com um valor vindo de uma variável da URL.
