package br.com.cefec.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProdutoDto {

    @NotBlank
    private String titulo;

    @NotNull
    private Double preco;

    @NotNull
    private Integer estoque;

}
