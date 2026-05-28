package sistema.financeiro.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sistema.financeiro.api.entities.Lancamento;

public interface LancamentoRepository extends JpaRepository<Lancamento, Long> {}
