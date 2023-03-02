package br.com.cefec.repositories;

import br.com.cefec.models.FaturamentoModel;
import br.com.cefec.models.ProdutoModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface FaturamentoRepository extends JpaRepository<FaturamentoModel, UUID> {
}
