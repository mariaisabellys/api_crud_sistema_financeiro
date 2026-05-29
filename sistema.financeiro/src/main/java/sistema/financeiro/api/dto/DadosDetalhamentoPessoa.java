package sistema.financeiro.api.dto;

import sistema.financeiro.api.entities.Pessoa;

public record DadosDetalhamentoPessoa(Long codigo, String nome, Boolean ativo, DadosEndereco endereco) {

    public DadosDetalhamentoPessoa(Pessoa pessoa) {
        this(pessoa.getCodigo(), pessoa.getNome(), pessoa.getAtivo(),
                new DadosEndereco(pessoa.getEndereco().getLogradouro(),
                        pessoa.getEndereco().getNumero(),
                        pessoa.getEndereco().getComplemento(),
                        pessoa.getEndereco().getBairro(),
                        pessoa.getEndereco().getCep(),
                        pessoa.getEndereco().getCidade(),
                        pessoa.getEndereco().getEstado()));
    }
}
