package sistema.financeiro.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record DadosCadastroCategoria(@NotBlank
                                     @Size(min = 3, max = 50)
                                     String nome) {

}
