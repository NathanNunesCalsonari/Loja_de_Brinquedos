package br.com.lojadebrinquedo.dto;

import br.com.lojadebrinquedo.entity.Usuario;
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
    private Long usuarioId; // Altere para um campo Long para o ID do usuário

    public String getUsuarioNome() {
        return usuarioNome;
    }

    public void setUsuarioNome(String usuarioNome) {
        this.usuarioNome = usuarioNome;
    }
}
