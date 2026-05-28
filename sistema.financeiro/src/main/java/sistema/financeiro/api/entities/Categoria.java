package sistema.financeiro.api.entities;

import jakarta.persistence.*;
import lombok.*;

    @Entity(name = "Categoria")
    @Table(name = "categoria")
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @EqualsAndHashCode(of = "codigo")
    public class Categoria {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long codigo;
        private String nome;
    }
