package sistema.financeiro.api.entities;

import jakarta.persistence.*;
import lombok.*;

    @Entity(name = "Pessoa")
    @Table(name = "pessoa")
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @EqualsAndHashCode(of = "codigo")
    public class Pessoa {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long codigo;
        private String nome;
        private Boolean ativo;
        @Embedded
        private Endereco endereco;
    }
