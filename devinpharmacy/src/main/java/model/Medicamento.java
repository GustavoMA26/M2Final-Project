package model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "MEDICAMENTOS")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Medicamento {

    @Id
    private Integer nroRegistro;

    private String nome;

    private String laboratorio;

    private String dosagem;

    private String descricao;

    private Float preco;

    @Enumerated(EnumType.STRING)
    private TipoMedicamento tipo;

    @ManyToMany(mappedBy = "medicamentos" )
    private List<Farmacia> farmacias;
}