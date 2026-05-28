package sistema.financeiro.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sistema.financeiro.api.dto.DadosCadastroCategoria;
import sistema.financeiro.api.entities.Categoria;
import sistema.financeiro.api.repositories.CategoriaRepository;

@Service
public class CategoriaService {
    @Autowired
    private CategoriaRepository repository;

    public Categoria salvar(DadosCadastroCategoria dados) {
        var categoria = new Categoria();
        categoria.setNome(dados.nome());
        return repository.save(categoria);
    }
}
