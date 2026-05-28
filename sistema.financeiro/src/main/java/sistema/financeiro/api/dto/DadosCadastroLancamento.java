package sistema.financeiro.api.dto;

import sistema.financeiro.api.entities.Categoria;
import sistema.financeiro.api.entities.Pessoa;
import sistema.financeiro.api.entities.TipoLancamento;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

public record DadosCadastroLancamento(
        @NotBlank String descricao,
        @NotNull LocalDate dataVencimento,
        LocalDate dataPagamento,
        @NotNull BigDecimal valor,
        String observacao,
        @NotNull TipoLancamento tipo,
        @NotNull Categoria categoria,
        @NotNull Pessoa pessoa) {

}
