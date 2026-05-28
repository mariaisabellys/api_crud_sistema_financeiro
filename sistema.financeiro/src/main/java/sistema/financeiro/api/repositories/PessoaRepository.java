package sistema.financeiro.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sistema.financeiro.api.entities.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {}
