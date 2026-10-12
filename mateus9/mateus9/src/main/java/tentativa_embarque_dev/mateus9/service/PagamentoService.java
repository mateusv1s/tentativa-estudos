package tentativa_embarque_dev.mateus9.service;

import org.springframework.stereotype.Service;
import tentativa_embarque_dev.mateus9.dto.response.PagamentoResponse;
import tentativa_embarque_dev.mateus9.entity.Pagamento;
import tentativa_embarque_dev.mateus9.repository.PagamentoRepository;

import java.time.LocalDateTime;

@Service
public class PagamentoService {

    private final PagamentoRepository pagamentoRepository;

    public PagamentoService(PagamentoRepository pagamentoRepository) {
        this.pagamentoRepository = pagamentoRepository;
    }

   public PagamentoResponse criarPagamento (Pagamento pagamento) {

       Pagamento pag = new Pagamento();

       pag.setValor(pagamento.getValor());
       pag.setCriadoEm(LocalDateTime.now());
       pag.setPagoEm(pagamento.getPagoEm());

       Pagamento salvarPagamento = pagamentoRepository.save(pag);

       return PagamentoResponse.fromEntity(salvarPagamento);

   }


}
