package br.com.cefec.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Table(name = "produtos")
public class ProdutoModel {
    private static final long serialVesionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "titulo", nullable = false, length = 50)
    private String titulo;

    @Column(name = "preco", nullable = false)
    private Double preco;

    @Column(name = "estoque", nullable = false)
    private Integer estoque;

}
