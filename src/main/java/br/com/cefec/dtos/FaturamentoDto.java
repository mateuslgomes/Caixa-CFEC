package br.com.cefec.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FaturamentoDto {

    @NotNull
    private Double valor;

}
