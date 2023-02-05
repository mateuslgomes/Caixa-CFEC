package br.com.cefec.controllers;

import br.com.cefec.dtos.ProdutoDto;
import br.com.cefec.models.ProdutoModel;
import br.com.cefec.services.ProdutoServices;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("caixa")
public class CaixaController {

    @Autowired
    ProdutoServices produtoServices;

    @GetMapping()
    public ResponseEntity<List<ProdutoModel>> getDados() {
        return ResponseEntity.status(HttpStatus.OK).body(produtoServices.findAll());
    }

    @PostMapping("cadastrar-produto")
    public ResponseEntity<Object> saveProduto(@RequestBody @Valid ProdutoDto dto) {
        System.out.println(dto);
        var produtoModel = new ProdutoModel();
        BeanUtils.copyProperties(dto, produtoModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(produtoServices.save(produtoModel));
    }

}
