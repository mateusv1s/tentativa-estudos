package tentativa_embarque_dev.mateus9.dto.response;

import tentativa_embarque_dev.mateus9.entity.Pagamento;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record PagamentoResponse (

        Long idPagamento,
        BigDecimal valor,
        LocalDateTime criadoEm,
        LocalDateTime pagoEm


)

{

    public static PagamentoResponse fromEntity (Pagamento pagamento) {
        return new PagamentoResponse (
                pagamento.getIdPagamento(),
                pagamento.getValor(),
                pagamento.getCriadoEm(),
                pagamento.getPagoEm()
        );
    }

}
