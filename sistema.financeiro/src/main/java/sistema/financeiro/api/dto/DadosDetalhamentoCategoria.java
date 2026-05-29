package sistema.financeiro.api.dto;

import sistema.financeiro.api.entities.Categoria;

public record DadosDetalhamentoCategoria(Long codigo, String nome) {
    public DadosDetalhamentoCategoria(Categoria categoria) {
        this(categoria.getCodigo(), categoria.getNome());
    }
}
