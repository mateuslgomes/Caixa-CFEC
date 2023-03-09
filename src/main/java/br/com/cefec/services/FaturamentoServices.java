package br.com.cefec.services;

import br.com.cefec.dtos.FaturamentoDto;
import br.com.cefec.exceptions.NotFoundException;
import br.com.cefec.models.FaturamentoModel;
import br.com.cefec.repositories.FaturamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class FaturamentoServices {

    @Autowired
    FaturamentoRepository faturamentoRepository;


    public FaturamentoModel saveTotal(FaturamentoDto dto) {
        return faturamentoRepository.save(new FaturamentoModel(dto));
    }

    public FaturamentoModel findById(UUID id) {
        return faturamentoRepository.findById(id).orElseThrow(() -> new NotFoundException("ID não encontrado"));
    }

    public Double getFaturamentoDia() {
        List<FaturamentoModel> faturamentoModels = faturamentoRepository.findByDate(LocalDate.now());
        Double faturamentoDia = 0.0;
        for (FaturamentoModel faturamento : faturamentoModels) {
            faturamentoDia += faturamento.getValor();
        }
        return faturamentoDia;
    }

}
