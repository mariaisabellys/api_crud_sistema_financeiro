package sistema.financeiro.api.entities;

import jakarta.persistence.*;
import lombok.*;
import sistema.financeiro.api.dto.DadosAtualizacaoLancamento;
import sistema.financeiro.api.dto.DadosCadastroLancamento;

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

        private Boolean ativo;

        public Lancamento(DadosCadastroLancamento dados, Categoria categoria, Pessoa pessoa) {
            this.descricao = dados.descricao();
            this.dataVencimento = dados.dataVencimento();
            this.dataPagamento = dados.dataPagamento();
            this.valor = dados.valor();
            this.observacao = dados.observacao();
            this.tipo = dados.tipo();
            this.categoria = categoria;
            this.pessoa = pessoa;
            this.ativo = true;
        }

        public void atualizarInformacoes(DadosAtualizacaoLancamento dados, Categoria categoria, Pessoa pessoa) {
            if (dados.descricao() != null) {
                this.descricao = dados.descricao();
            }
            if (dados.dataVencimento() != null) {
                this.dataVencimento = dados.dataVencimento();
            }
            if (dados.dataPagamento() != null) {
                this.dataPagamento = dados.dataPagamento();
            }
            if (dados.valor() != null) {
                this.valor = dados.valor();
            }
            if (dados.observacao() != null) {
                this.observacao = dados.observacao();
            }
            if (dados.tipo() != null) {
                this.tipo = dados.tipo();
            }
            if (categoria != null) {
                this.categoria = categoria;
            }
            if (pessoa != null) {
                this.pessoa = pessoa;
            }
        }

        public void excluir() {
            this.ativo = false;
        }
}
