package br.com.cefec.controllers;

import br.com.cefec.dtos.ProdutoDto;
import br.com.cefec.models.ProdutoModel;
import br.com.cefec.repositories.ProdutoRepository;
import br.com.cefec.services.ProdutoServices;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("caixa")
public class CaixaController {

    @Autowired
    ProdutoServices produtoServices;
    @Autowired
    private ProdutoRepository produtoRepository;


    @GetMapping
    public ResponseEntity<List<ProdutoModel>> getDados() {
        return ResponseEntity.status(HttpStatus.OK).body(produtoServices.findAll());
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
