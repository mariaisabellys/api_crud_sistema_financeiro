package sistema.financeiro.api.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import sistema.financeiro.api.dto.DadosAtualizacaoPessoa;
import sistema.financeiro.api.dto.DadosCadastroPessoa;
import sistema.financeiro.api.entities.Endereco;
import sistema.financeiro.api.entities.Pessoa;
import sistema.financeiro.api.repositories.PessoaRepository;

@Service
public class PessoaService {
    @Autowired
    private PessoaRepository repository;

    @Transactional
    public Pessoa salvar(DadosCadastroPessoa dados) {
        var pessoa = new Pessoa(dados);
        return repository.save(pessoa);
    }

    public Page<Pessoa> listar(Pageable paginacao) {
        return repository.findAllByAtivoTrue(paginacao);
    }

    public Pessoa detalhar(Long codigo) {
        return repository.getReferenceById(codigo);
    }

    @Transactional
    public Pessoa atualizar(DadosAtualizacaoPessoa dados) {
        var pessoa = repository.getReferenceById(dados.codigo());
        pessoa.atualizarInformacoes(dados);
        return pessoa;
    }

    @Transactional
    public void excluir(Long codigo) {
        var pessoa = repository.getReferenceById(codigo);
        pessoa.excluir();
    }

}
