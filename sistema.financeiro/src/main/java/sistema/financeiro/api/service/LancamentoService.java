package sistema.financeiro.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sistema.financeiro.api.dto.DadosCadastroLancamento;
import sistema.financeiro.api.entities.Lancamento;
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

    public Lancamento salvar(DadosCadastroLancamento dados) {
        var pessoa = pessoaRepository.findById(dados.pessoa().getCodigo())
                .orElseThrow(() -> new RuntimeException("Pessoa não encontrada"));

        if (!pessoa.getAtivo()) {
            throw new RuntimeException("Pessoa inativa não pode realizar lançamentos");
        }

        var categoria = categoriaRepository.findById(dados.categoria().getCodigo())
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));

        var lancamento = new Lancamento();
        lancamento.setDescricao(dados.descricao());
        lancamento.setDataVencimento(dados.dataVencimento());
        lancamento.setDataPagamento(dados.dataPagamento());
        lancamento.setValor(dados.valor());
        lancamento.setObservacao(dados.observacao());
        lancamento.setTipo(dados.tipo());
        lancamento.setCategoria(categoria);
        lancamento.setPessoa(pessoa);

        return lancamentoRepository.save(lancamento);
    }
}
