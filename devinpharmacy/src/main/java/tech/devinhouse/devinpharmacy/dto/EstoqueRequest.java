package tech.devinhouse.devinpharmacy.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class EstoqueRequest {

    @NotNull(message = "Este campo não pode estar vazio")
    private Long cnpj;

    @NotNull(message = "Este campo não pode estar vazio")
    private Integer nroRegistro;

    @NotNull(message = "Este campo não pode estar vazio")
    @Positive(message = "Deve ser um número positivo e maior que 0")
    private Integer quantidade;

}
