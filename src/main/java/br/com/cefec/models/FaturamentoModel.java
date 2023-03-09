package br.com.cefec.models;

import br.com.cefec.dtos.FaturamentoDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@Entity
@AllArgsConstructor
@RequiredArgsConstructor
@Table(name = "faturamento")
public class FaturamentoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "valor", nullable = false, length = 50)
    private Double valor;

    @Column(name = "date")
    private LocalDate date;

    public FaturamentoModel(FaturamentoDto dto) {
        this.valor = dto.getValor();
    }

    @PrePersist
    public void prePersist() {
        this.date = LocalDate.now();
    }

}
