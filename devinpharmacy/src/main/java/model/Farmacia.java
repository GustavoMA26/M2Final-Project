package model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "FARMACIAS")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Farmacia {

        @Id
        private Long cnpj;

        private String razaoSocial;

        private String nomeFantasia;

        private String email;

        private String telefone;

        private String celular;

        @Embedded
        private Endereco endereco;

    }
