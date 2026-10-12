package tentativa_embarque_dev.mateus9.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import tentativa_embarque_dev.mateus9.entity.Pagamento;
import tentativa_embarque_dev.mateus9.service.PagamentoService;

@Component
public class PagamentoConsumer {

    private final PagamentoService pagamentoService;

    public PagamentoConsumer(PagamentoService pagamentoService) {
        this.pagamentoService = pagamentoService;
    }

    // explicacao no codigo abaixo
    @RabbitListener(queues = "pagamento-queue")
    public void testes (String message) {
        System.out.println("testes" + message);
    }

    // to em duvida como posso validar o pagamento, então criei isso aqui só pra testar mesmo se o consumer vai funcionar
    @RabbitListener(queues = "pagamento-queue")
    public void testes2 (Pagamento teste) {
        pagamentoService.criarPagamento(teste);
    }


}
