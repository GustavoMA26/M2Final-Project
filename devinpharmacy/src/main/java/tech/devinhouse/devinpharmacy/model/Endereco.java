package tech.devinhouse.devinpharmacy.model;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Embeddable
public class Endereco {
    private Long cep;
    private String logradouro;
    private Integer numero;
    private String bairro;
    private String cidade;
    private String estado;
    private String complemento;
    private Double latitude;
    private Double longitude;
}
