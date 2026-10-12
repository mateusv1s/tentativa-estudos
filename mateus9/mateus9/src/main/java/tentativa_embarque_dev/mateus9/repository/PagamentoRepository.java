package tentativa_embarque_dev.mateus9.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tentativa_embarque_dev.mateus9.entity.Pagamento;

@Repository
public interface PagamentoRepository extends JpaRepository <Pagamento, Long> {
}
