package br.com.cefec.repositories;

import br.com.cefec.models.FaturamentoModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface FaturamentoRepository extends JpaRepository<FaturamentoModel, UUID> {

    List<FaturamentoModel> findByDate(LocalDate date);

}
