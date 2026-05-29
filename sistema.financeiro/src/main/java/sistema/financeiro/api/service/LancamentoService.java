package sistema.financeiro.api.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import sistema.financeiro.api.dto.DadosAtualizacaoLancamento;
import sistema.financeiro.api.dto.DadosCadastroLancamento;
import sistema.financeiro.api.entities.Categoria;
import sistema.financeiro.api.entities.Lancamento;
import sistema.financeiro.api.entities.Pessoa;
import sistema.financeiro.api.repositories.CategoriaRepository;
import sistema.financeiro.api.repositories.LancamentoRepository;
import sistema.financeiro.api.repositories.PessoaRepository;

@Service
public class LancamentoService {

    @Autowired
    private LancamentoRepository lancamentoRepository;
    @Autowired
    private PessoaRepository pessoaRepository;
    @Autowired
    private CategoriaRepository categoriaRepository;

    @Transactional
    public Lancamento salvar(DadosCadastroLancamento dados) {
        var pessoa = pessoaRepository.findById(dados.pessoa().getCodigo())
                .orElseThrow(() -> new RuntimeException("Pessoa não encontrada"));

        if (!pessoa.getAtivo()) {
            throw new RuntimeException("Pessoa inativa não pode realizar lançamentos");
        }

        var categoria = categoriaRepository.findById(dados.categoria().getCodigo())
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));

        var lancamento = new Lancamento(dados, categoria, pessoa);

        return lancamentoRepository.save(lancamento);
    }

    public Page<Lancamento> listar(Pageable paginacao) {
        return lancamentoRepository.findAllByAtivoTrue(paginacao);
    }

    public Lancamento detalhar(Long codigo) {
        return lancamentoRepository.getReferenceById(codigo);
    }

    @Transactional
    public Lancamento atualizar(DadosAtualizacaoLancamento dados) {
        var lancamento = lancamentoRepository.getReferenceById(dados.codigo());

        Categoria categoria = null;
        if (dados.codigoCategoria() != null) {
            categoria = categoriaRepository.findById(dados.codigoCategoria())
                    .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));
        }

        Pessoa pessoa = null;
        if (dados.codigoPessoa() != null) {
            pessoa = pessoaRepository.findById(dados.codigoPessoa())
                    .orElseThrow(() -> new RuntimeException("Pessoa não encontrada"));
        }

        lancamento.atualizarInformacoes(dados, categoria, pessoa);
        return lancamento;
    }

    @Transactional
    public void excluir(Long codigo) {
        var lancamento = lancamentoRepository.getReferenceById(codigo);
        lancamento.excluir();
    }


}
