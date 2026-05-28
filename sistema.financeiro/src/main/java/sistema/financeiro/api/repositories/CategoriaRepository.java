package sistema.financeiro.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sistema.financeiro.api.entities.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {}
