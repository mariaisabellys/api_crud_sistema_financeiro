package sistema.financeiro.api.entities;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;

    @Entity(name = "Lancamento")
    @Table(name = "lancamento")
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @EqualsAndHashCode(of = "codigo")
    public class Lancamento {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long codigo;
        private String descricao;

        @Column(name = "data_vencimento")
        private LocalDate dataVencimento;

        @Column(name = "data_pagamento")
        private LocalDate dataPagamento;

        private BigDecimal valor;
        private String observacao;

        @Enumerated(EnumType.STRING)
        private TipoLancamento tipo;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "codigo_categoria")
        private Categoria categoria;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "codigo_pessoa")
        private Pessoa pessoa;
}
