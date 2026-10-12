package tentativa_embarque_dev.mateus9.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tentativa_embarque_dev.mateus9.dto.response.PagamentoResponse;
import tentativa_embarque_dev.mateus9.entity.Pagamento;
import tentativa_embarque_dev.mateus9.service.PagamentoService;

@RestController
@RequestMapping ("/testes")
public class PagamentoController {

    private final PagamentoService pagamentoService;

    public PagamentoController(PagamentoService pagamentoService) {
        this.pagamentoService = pagamentoService;
    }

    @PostMapping
    public ResponseEntity<PagamentoResponse> criarPagamento (@RequestBody Pagamento pagamento) {
        return ResponseEntity.status(HttpStatus.CREATED).body(pagamentoService.criarPagamento(pagamento));
    }

    @GetMapping
    public ResponseEntity<String> pagamentoTeste (String message) {
        return ResponseEntity.ok(message);
    }

}
