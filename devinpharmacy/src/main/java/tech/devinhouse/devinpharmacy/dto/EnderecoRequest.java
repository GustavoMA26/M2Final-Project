package tech.devinhouse.devinpharmacy.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class EnderecoRequest {

    @NotNull(message = "Este campo não pode estar vazio")
    private Long cep;

    @NotBlank(message = "Este campo não pode estar vazio")
    private String logradouro;

    @NotNull(message = "Este campo não pode estar vazio")
    private Integer numero;

    @NotBlank(message = "Este campo não pode estar vazio")
    private String bairro;

    @NotBlank(message = "Este campo não pode estar vazio")
    private String cidade;

    @NotBlank(message = "Este campo não pode estar vazio")
    private String estado;

    @NotBlank(message = "Este campo não pode estar vazio")
    private String complemento;

    @NotNull(message = "Este campo não pode estar vazio")
    private Double latitude;

    @NotNull(message = "Este campo não pode estar vazio")
    private Double longitude;
}
