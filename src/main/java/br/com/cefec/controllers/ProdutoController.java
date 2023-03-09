package br.com.cefec.controllers;

import br.com.cefec.client.EmailClient;
import br.com.cefec.dtos.FaturamentoDto;
import br.com.cefec.dtos.ProdutoDto;
import br.com.cefec.models.FaturamentoModel;
import br.com.cefec.models.MensagemModel;
import br.com.cefec.models.ProdutoModel;
import br.com.cefec.services.FaturamentoServices;
import br.com.cefec.services.ProdutoServices;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("caixa")
public class ProdutoController {

    @Autowired
    ProdutoServices produtoServices;

    @Autowired
    FaturamentoServices faturamentoServices;

    @Autowired
    EmailClient emailClient;

    private static final Logger logger = LoggerFactory.getLogger(EmailClient.class);

    @GetMapping
    public ResponseEntity<List<ProdutoModel>> getDados() {
        List<ProdutoModel> produtos = produtoServices.findAll();
        return ResponseEntity.ok(produtos);
    }

    @PostMapping
    public ResponseEntity<ProdutoModel> saveProduto(@RequestBody @Valid ProdutoDto dto) {
        return ResponseEntity.ok().body(produtoServices.save(dto));
    }

    @GetMapping("{id}")
    public ResponseEntity<ProdutoModel> getProduto(@PathVariable UUID id) {
        return ResponseEntity.ok().body(produtoServices.findById(id));
    }

    @PostMapping("sending-email")
    public ResponseEntity<String> sendingEmail(@RequestBody MensagemModel msg) {
        try {
            return ResponseEntity.ok("E-mail enviado com sucesso");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao enviar o e-mail: " + e.getMessage());
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<ProdutoModel> updateProduto(@PathVariable UUID id,
                                                      @Valid @RequestBody ProdutoDto produtoDto) {
        return ResponseEntity.ok(produtoServices.update(id, produtoDto));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> deleteProduto(@PathVariable UUID id) {
        produtoServices.delete(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
