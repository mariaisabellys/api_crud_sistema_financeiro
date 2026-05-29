package sistema.financeiro.api.dto;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoCategoria(
        @NotNull
        Long codigo,
        String nome
) {
}
