package tech.devinhouse.devinpharmacy.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class TrocaRequest {

    @NotNull(message = "Este campo não pode estar vazio")
    private Long cnpjOrigem;

    @NotNull(message = "Este campo não pode estar vazio")
    private Long cnpjDestino;

    @NotNull(message = "Este campo não pode estar vazio")
    private Integer nroRegistro;

    @NotNull(message = "Este campo não pode estar vazio")
    @Positive(message = "Deve ser um número positivo e maior que 0")
    private Integer quantidade;

}
