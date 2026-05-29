package sistema.financeiro.api.dto;

import jakarta.validation.constraints.NotNull;
import sistema.financeiro.api.entities.TipoLancamento;
import java.math.BigDecimal;
import java.time.LocalDate;

public record DadosAtualizacaoLancamento(
        @NotNull
        Long codigo,
        String descricao,
        LocalDate dataVencimento,
        LocalDate dataPagamento,
        BigDecimal valor,
        String observacao,
        TipoLancamento tipo,
        Long codigoCategoria,
        Long codigoPessoa
) {
}
