package tech.devinhouse.devinpharmacy.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import tech.devinhouse.devinpharmacy.model.Endereco;

@Data
public class FarmaciaRequest {

    @NotNull(message = "Este campo não pode estar vazio")
    private Long cnpj;

    @NotBlank(message = "Este campo não pode estar vazio")
    private String razaoSocial;

    @NotEmpty(message = "Este campo não pode estar vazio")
    private String nomeFantasia;

    @NotBlank(message = "Este campo não pode estar vazio")
    private String email;

    @NotBlank(message = "Este campo não pode estar vazio")
    private String telefone;

    @NotBlank(message = "Este campo não pode estar vazio")
    private String celular;

    private EnderecoRequest endereco;
}
