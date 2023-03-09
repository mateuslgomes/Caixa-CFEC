package br.com.cefec.controllers;

import br.com.cefec.dtos.FaturamentoDto;
import br.com.cefec.models.FaturamentoModel;
import br.com.cefec.services.FaturamentoServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("caixa")
public class FaturamentoController {

    @Autowired
    FaturamentoServices faturamentoServices;

    @GetMapping("total/{id}")
    public ResponseEntity<FaturamentoModel> getPagamento(@PathVariable UUID id) {
        return ResponseEntity.status(HttpStatus.OK).body(faturamentoServices.findById(id));
    }

    @GetMapping("total")
    public ResponseEntity<Double> getTotal() {
        return ResponseEntity.status(HttpStatus.OK).body(faturamentoServices.getFaturamentoDia());
    }


    @PostMapping("total")
    public ResponseEntity<HttpStatus> saveTotal(@RequestBody @Valid FaturamentoDto dto) {
        faturamentoServices.saveTotal(dto);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
