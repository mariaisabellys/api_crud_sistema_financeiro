# Notações do Spring Boot e JPA - Referência Rápida

Documentação das anotações essenciais utilizadas no projeto para mapeamento, validação e controle.

## Spring Framework & REST
- `@RestController`: Define a classe como um recurso REST.
- `@RequestMapping("/caminho")`: Define a rota base do controller.
- `@Autowired`: Injeção de dependência (Service no Controller, Repository no Service).
- `@Service`: Identifica a camada de lógica de negócio.
- `@Repository`: Identifica a camada de acesso a dados.
- `@RestControllerAdvice`: Componente global para tratamento de exceções.
- `@ExceptionHandler`: Define o tratamento para uma exceção específica.

## JPA & Hibernate (Banco de Dados)
- `@Entity`: Mapeia a classe para uma tabela.
- `@Table(name = "...")`: Especifica o nome da tabela.
- `@Id` & `@GeneratedValue`: Define a chave primária e sua estratégia de geração (Auto-incremento).
- `@Embedded` & `@Embeddable`: Para classes que são "partes" de uma tabela (ex: Endereco dentro de Pessoa).
- `@ManyToOne` & `@JoinColumn`: Mapeia relacionamentos N:1 (Chave Estrangeira).
- `@Enumerated(EnumType.STRING)`: Salva o texto do Enum no banco em vez do número.
- `@Transactional`: Garante que a operação seja atômica (tudo ou nada).

## Validação (Bean Validation)
- `@Valid`: Ativa a validação no parâmetro do Controller.
- `@NotBlank`: String não pode ser vazia ou só espaços.
- `@NotNull`: Campo não pode ser nulo (obrigatório).
- `@Size(min, max)`: Define limites de caracteres.

## Lombok (Produtividade)
- `@Getter` / `@Setter`: Gera métodos de acesso.
- `@NoArgsConstructor`: Gera construtor vazio (exigido pelo JPA).
- `@AllArgsConstructor`: Gera construtor com todos os campos.
- `@EqualsAndHashCode(of = "...")`: Gera métodos de comparação baseados em campos específicos.
