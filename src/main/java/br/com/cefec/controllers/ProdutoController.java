package br.com.cefec.controllers;

import br.com.cefec.client.EmailClient;
import br.com.cefec.dtos.EmailDto;
import br.com.cefec.dtos.FaturamentoDto;
import br.com.cefec.dtos.ProdutoDto;
import br.com.cefec.models.FaturamentoModel;
import br.com.cefec.models.MensagemModel;
import br.com.cefec.models.ProdutoModel;
import br.com.cefec.repositories.FaturamentoRepository;
import br.com.cefec.repositories.ProdutoRepository;
import br.com.cefec.services.ProdutoServices;
import com.fasterxml.jackson.databind.ObjectMapper;
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
    ProdutoRepository produtoRepository;

    @Autowired
    FaturamentoRepository faturamentoRepository;

    @Autowired
    EmailClient emailClient;

    private static final Logger logger = LoggerFactory.getLogger(EmailClient.class);

    @GetMapping
    public ResponseEntity<List<ProdutoModel>> getDados() {
        return ResponseEntity.status(HttpStatus.OK).body(produtoServices.findAll());
    }

    @GetMapping("total/{id}")
    public ResponseEntity<Object> getPagamento(@PathVariable UUID id) {
        Optional<FaturamentoModel> faturamentoModelOptional = produtoServices.faturamentoFindById(id);
        if (faturamentoModelOptional.isEmpty()) {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body("Total não encontrado");
        }
        return ResponseEntity.status(HttpStatus.OK).body(faturamentoModelOptional.get());
    }

    @GetMapping("total")
    public ResponseEntity<Double> getTotal() {
        List<FaturamentoModel> faturamentoList = produtoServices.faturamentoFindAll();
        Double faturamentoTotal = 0.0;
        for (int i = 0; i != faturamentoList.size(); i++) {
            faturamentoTotal += faturamentoList.get(i).getValor();
            System.out.println(faturamentoList.get(i).getValor());
        }
        return ResponseEntity.status(HttpStatus.OK).body(faturamentoTotal);
    }

    @PostMapping("total")
    public ResponseEntity<FaturamentoModel> saveTotal(@RequestBody @Valid FaturamentoDto dto) {
        var faturamentoModel = new FaturamentoModel();
        BeanUtils.copyProperties(dto, faturamentoModel);
        faturamentoModel.setDate(LocalDate.now());
        return ResponseEntity.status(HttpStatus.CREATED).body(produtoServices.saveTotal(faturamentoModel));
    }

    @PostMapping
    public ResponseEntity<ProdutoModel> saveProduto(@RequestBody @Valid ProdutoDto dto) {
        var produtoModel = new ProdutoModel();
        BeanUtils.copyProperties(dto, produtoModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(produtoServices.save(produtoModel));
    }

    @GetMapping("{id}")
    public ResponseEntity<Object> getProduto(@PathVariable UUID id) {
        Optional<ProdutoModel> produtoOptional = produtoServices.findById(id);
        return produtoOptional.<ResponseEntity<Object>>map(produtoModel -> ResponseEntity.status(HttpStatus.OK)
                .body(produtoModel)).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Produto não encontrado"));
    }

    @PostMapping("sending-email")
    public ResponseEntity<String> sendingEmail(@RequestBody MensagemModel msg) {
        try {
            System.out.println(msg.getMensagem());
            ObjectMapper objectMapper = new ObjectMapper();

            emailClient.sendingEmail(EmailDto.of("teste de envio de email!!!"));

            return ResponseEntity.status(HttpStatus.OK).body("sucess");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.OK).body("failed " + e.getMessage());
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<Object> updateProduto(@PathVariable UUID id,
                                                @RequestBody @Valid ProdutoDto produtoDto) {
        Optional<ProdutoModel> produtoOptional = produtoServices.findById(id);
        if (produtoOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado");
        }
        var produtoModel = new ProdutoModel();
        BeanUtils.copyProperties(produtoDto, produtoModel);
        produtoModel.setId(produtoOptional.get().getId());
        return ResponseEntity.status(HttpStatus.OK).body(produtoServices.save(produtoModel));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteProduto(@PathVariable UUID id) {
        Optional<ProdutoModel> produtoOptional = produtoServices.findById(id);
        if (produtoOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado");
        }
        produtoRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body("O produto " + produtoOptional.get().getTitulo() +
                " foi deletado com sucesso!");
    }

}
