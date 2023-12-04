package tech.devinhouse.devinpharmacy.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class EstoqueResponse {

    private Long cnpj;

    private Integer nroRegistro;

    private Integer quantidade;

    private LocalDateTime dataAtualizacao;
}
