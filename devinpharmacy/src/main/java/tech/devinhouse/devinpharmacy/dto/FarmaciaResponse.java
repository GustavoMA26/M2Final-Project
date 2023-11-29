package tech.devinhouse.devinpharmacy.dto;

import lombok.Data;
import tech.devinhouse.devinpharmacy.model.Endereco;

@Data
public class FarmaciaResponse {

    private Long cnpj;

    private String razaoSocial;

    private String nomeFantasia;

    private String email;

    private String telefone;

    private String celular;

    private Endereco endereco;
}
