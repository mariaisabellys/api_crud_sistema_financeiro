package sistema.financeiro.api.dto;

import sistema.financeiro.api.entities.Lancamento;
import sistema.financeiro.api.entities.TipoLancamento;
import java.math.BigDecimal;
import java.time.LocalDate;

public record DadosDetalhamentoLancamento(Long codigo, String descricao, LocalDate dataVencimento, LocalDate dataPagamento, BigDecimal valor, String observacao, TipoLancamento tipo, String categoria, String pessoa) {
    public DadosDetalhamentoLancamento(Lancamento lancamento) {
        this(lancamento.getCodigo(), lancamento.getDescricao(), lancamento.getDataVencimento(), lancamento.getDataPagamento(), lancamento.getValor(), lancamento.getObservacao(), lancamento.getTipo(), lancamento.getCategoria().getNome(), lancamento.getPessoa().getNome());
    }
}
