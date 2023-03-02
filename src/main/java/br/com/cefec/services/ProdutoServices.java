package br.com.cefec.services;

import br.com.cefec.models.FaturamentoModel;
import br.com.cefec.models.ProdutoModel;
import br.com.cefec.repositories.FaturamentoRepository;
import br.com.cefec.repositories.ProdutoRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class ProdutoServices {

    @Autowired
    ProdutoRepository produtoRepository;

    @Autowired
    FaturamentoRepository faturamentoRepository;

    @Transactional
    public ProdutoModel save(ProdutoModel model) {
        return produtoRepository.save(model);
    }

    public FaturamentoModel saveTotal(FaturamentoModel model) {
        return faturamentoRepository.save(model);
    }

    public List<ProdutoModel> findAll() {
        return produtoRepository.findAll();
    }

    public void deleteById(UUID id) {
        produtoRepository.deleteById(id);
    }

    public Optional<ProdutoModel> findById(UUID id) {
        return produtoRepository.findById(id);
    }

    public Optional<FaturamentoModel> faturamentoFindById(UUID id) {
        return faturamentoRepository.findById(id);
    }

    public List<FaturamentoModel> faturamentoFindAll() {
        return faturamentoRepository.findAll();
    }
}
