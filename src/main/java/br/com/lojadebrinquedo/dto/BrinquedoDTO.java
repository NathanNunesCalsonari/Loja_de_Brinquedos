package br.com.lojadebrinquedo.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import java.math.BigDecimal;

@Data
public class BrinquedoDTO {

    @NotBlank
    private Long id;

    @NotBlank
    private String nome;

    @NotBlank
    private String tipo;

    private String classificacao;

    private String tamanho;

    private BigDecimal preco;

    private String usuarioNome;

    @NotBlank
    private Long usuarioId;

    @NotBlank
    private Long empresaId;

    private String empresaNome;  // Nome da empresa para exibição
}
