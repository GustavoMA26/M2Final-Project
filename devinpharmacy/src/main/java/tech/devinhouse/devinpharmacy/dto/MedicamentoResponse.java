package tech.devinhouse.devinpharmacy.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;
import tech.devinhouse.devinpharmacy.model.TipoMedicamento;

@Data
public class MedicamentoResponse {

    private Integer nroRegistro;

    private String nome;

    private String laboratorio;

    private String dosagem;

    private String descricao;

    private Float preco;

    private TipoMedicamento tipo;
}
