package tech.devinhouse.devinpharmacy.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import tech.devinhouse.devinpharmacy.model.TipoMedicamento;

@Data
public class MedicamentoRequest {

    @NotNull(message = "Este campo não pode estar vazio")

    private Integer nroRegistro;

    @NotEmpty(message = "Este campo não pode estar vazio")
    private String nome;

    @NotEmpty(message = "Este campo não pode estar vazio")
    private String laboratorio;

    @NotEmpty(message = "Este campo não pode estar vazio")
    private String dosagem;

    private String descricao;

    @NotNull(message = "Este campo não pode estar vazio")
    private Float preco;

    @NotNull(message = "Este campo não pode estar vazio")
    private TipoMedicamento tipo;
}
