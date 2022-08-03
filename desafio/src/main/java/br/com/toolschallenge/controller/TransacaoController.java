package br.com.toolschallenge.controller;

import br.com.toolschallenge.dto.TransacaoDto;
import br.com.toolschallenge.service.TransacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping(value = "/transacao", consumes ="application/json", produces = "application/json")
public class TransacaoController {

    private TransacaoService transacaoService;

    @Autowired
    public TransacaoController(TransacaoService transacaoService) {
        this.transacaoService = transacaoService;
    }

    @GetMapping("/")
    public ResponseEntity<String> helloWord() {
        return ResponseEntity.ok("Bem vindo a API de Pagamentos");
    }

    @PostMapping(value = "/pagamento")
    public ResponseEntity<TransacaoDto> processarPagamento(@Valid @RequestBody TransacaoDto transacao) {
        TransacaoDto transacaoDto = transacaoService.processarTransacao(transacao);
        return transacaoDto == null ? ResponseEntity.unprocessableEntity().build() : ResponseEntity.ok(transacaoDto);
    }

    @GetMapping(value = "/pagamento/{id}")
    public ResponseEntity<TransacaoDto> getPagamentoById(@PathVariable String id) {
        TransacaoDto transacaoDto = transacaoService.buscarTransacaoPorId(id);
        return transacaoDto == null ? ResponseEntity.unprocessableEntity().build() : ResponseEntity.ok(transacaoDto);
    }

    @GetMapping(value = "/todosPagamento")
    public ResponseEntity<List<TransacaoDto>> getAllPagamento() {
        List<TransacaoDto> transacaoList = transacaoService.buscarTodosOsRegistros();
        return transacaoList.isEmpty() ? ResponseEntity.unprocessableEntity().build() : ResponseEntity.ok(transacaoList);
    }

    @GetMapping(value = "/estorno/{id}")
    public ResponseEntity<TransacaoDto> getEstornoById(@PathVariable String id) {
        TransacaoDto transacaoDto = transacaoService.buscarEstornoPorId(id);
        return transacaoDto == null ? ResponseEntity.unprocessableEntity().build() : ResponseEntity.ok(transacaoDto);
    }

}
