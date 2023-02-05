package br.com.cefec.services;

import br.com.cefec.models.ProdutoModel;
import br.com.cefec.repositories.ProdutoRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ProdutoServices {


    @Autowired
    ProdutoRepository produtoRepository;

    @Transactional
    public ProdutoModel save(ProdutoModel model) {
        return produtoRepository.save(model);
    }

    public List<ProdutoModel> findAll() {
        return produtoRepository.findAll();
    }

}
