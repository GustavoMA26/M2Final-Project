package tech.devinhouse.devinpharmacy.dto;

import lombok.*;
import tech.devinhouse.devinpharmacy.model.Estoque;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TrocaResponse {

    private Integer nroRegistro;

    private Long cnpjOrigem;

    private Integer quantidadeOrigem;

    private Long cnpjDestino;

    private Integer quantidadeDestino;

    private LocalDateTime dataAtualizacao;

}
