package br.com.cefec.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
public class FaturamentoDto {

    @NotNull
    private BigDecimal valor;

}
