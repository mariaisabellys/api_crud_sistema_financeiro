package sistema.financeiro.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sistema.financeiro.api.dto.DadosCadastroPessoa;
import sistema.financeiro.api.entities.Endereco;
import sistema.financeiro.api.entities.Pessoa;
import sistema.financeiro.api.repositories.PessoaRepository;

@Service
public class PessoaService {
    @Autowired
    private PessoaRepository repository;

    public Pessoa salvar(DadosCadastroPessoa dados) {
        var pessoa = new Pessoa();
        pessoa.setNome(dados.nome());
        pessoa.setAtivo(dados.ativo());

        if (dados.endereco() != null) {
            var endereco = new Endereco(
                    dados.endereco().logradouro(),
                    dados.endereco().numero(),
                    dados.endereco().complemento(),
                    dados.endereco().bairro(),
                    dados.endereco().cep(),
                    dados.endereco().cidade(),
                    dados.endereco().estado()
            );
            pessoa.setEndereco(endereco);
        }

        return repository.save(pessoa);
    }
}
