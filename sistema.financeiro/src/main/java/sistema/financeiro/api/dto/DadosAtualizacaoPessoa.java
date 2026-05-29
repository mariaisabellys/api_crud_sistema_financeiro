package sistema.financeiro.api.dto;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoPessoa(
        @NotNull Long codigo,
        String nome,
        Boolean ativo,
        DadosEndereco endereco
) {}
