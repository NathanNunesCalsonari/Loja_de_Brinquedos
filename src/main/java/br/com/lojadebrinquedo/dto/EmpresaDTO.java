package br.com.lojadebrinquedo.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class EmpresaDTO {

    private Long id;

    @NotBlank
    private String nome;

    @NotBlank
    private String cnpj;

    private String endereco;

    private String telefone;
}
