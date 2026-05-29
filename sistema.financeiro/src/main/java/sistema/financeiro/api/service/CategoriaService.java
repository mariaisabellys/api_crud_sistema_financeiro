package sistema.financeiro.api.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import sistema.financeiro.api.dto.DadosAtualizacaoCategoria;
import sistema.financeiro.api.dto.DadosCadastroCategoria;
import sistema.financeiro.api.entities.Categoria;
import sistema.financeiro.api.repositories.CategoriaRepository;

@Service
public class CategoriaService {
    @Autowired
    private CategoriaRepository repository;

    @Transactional
    public Categoria salvar(DadosCadastroCategoria dados) {
        var categoria = new Categoria(dados);
        return repository.save(categoria);
    }

    public Page<Categoria> listar(Pageable paginacao) {
        return repository.findAllByAtivoTrue(paginacao);
    }

    public Categoria detalhar(Long codigo) {
        return repository.getReferenceById(codigo);
    }

    @Transactional
    public Categoria atualizar(DadosAtualizacaoCategoria dados) {
        var categoria = repository.getReferenceById(dados.codigo());
        categoria.atualizarInformacoes(dados);
        return categoria;
    }

    @Transactional
    public void excluir(Long codigo) {
        var categoria = repository.getReferenceById(codigo);
        categoria.excluir();
    }
}
