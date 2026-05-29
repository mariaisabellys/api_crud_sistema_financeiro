package sistema.financeiro.api.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import sistema.financeiro.api.entities.Lancamento;

public interface LancamentoRepository extends JpaRepository<Lancamento, Long> {
    Page<Lancamento> findAllByAtivoTrue(Pageable paginacao);
}
