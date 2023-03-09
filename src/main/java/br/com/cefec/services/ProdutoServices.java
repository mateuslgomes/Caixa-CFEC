package br.com.cefec.services;

import br.com.cefec.dtos.ProdutoDto;
import br.com.cefec.exceptions.NotFoundException;
import br.com.cefec.models.ProdutoModel;
import br.com.cefec.repositories.ProdutoRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class ProdutoServices {

    @Autowired
    ProdutoRepository produtoRepository;

    @Transactional
    public ProdutoModel update(UUID id, ProdutoDto dto) {
        var produto = findById(id);
        BeanUtils.copyProperties(dto, produto);
        return produtoRepository.save(produto);
    }

    public List<ProdutoModel> findAll() {
        return produtoRepository.findAll();
    }

    public void delete (UUID id) {
        produtoRepository.delete(findById(id));
    }

    public ProdutoModel findById (UUID id) {
        return produtoRepository.findById(id).orElseThrow(() -> new NotFoundException("ID não encontrado"));
    }

    public ProdutoModel save(ProdutoDto produtoDto) {
        ProdutoModel produto = ProdutoModel.builder()
                .titulo(produtoDto.getTitulo())
                .estoque(produtoDto.getEstoque())
                .preco(produtoDto.getPreco())
                .build();
        return produtoRepository.save(produto);
    }



}
