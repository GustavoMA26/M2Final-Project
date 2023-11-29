package tech.devinhouse.devinpharmacy.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.devinhouse.devinpharmacy.model.*;
import tech.devinhouse.devinpharmacy.service.EstoqueService;
import tech.devinhouse.devinpharmacy.service.FarmaciaService;
import tech.devinhouse.devinpharmacy.service.MedicamentoService;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/inicializacao")
public class InicializacaoController {

    @Autowired
    private FarmaciaService farmaciaService;

    @Autowired
    private MedicamentoService medicamentoService;

    @Autowired
    private EstoqueService estoqueService;


    @PostMapping
    public ResponseEntity<?> carregarDados() {
       var farmacias = farmaciaService.consultar();
        if(farmacias.isEmpty()) {
            Endereco endereco1 = new Endereco(88888999L, "Rua Porto Real", 67, "Westeros", "Berlim", "SC", "Complemento", 15.23456, 2.8678687);
            farmacias.add(new Farmacia(90561736000121L, "DevMed Ltda", "Farmácia DevMed", " devmed@farmacia.com", "(44)4444-4444", "(44)9444-4441", endereco1));
            Endereco endereco2 = new Endereco(8877799L, "Rua Madrid", 76, "Winterfell", "Estocolmo", "SC", "Complemento", 19.254356, 3.8987687);
            farmacias.add(new Farmacia(43178995000198L, "MedHouse Ltda", "Farmácia MedHouse", " medhouse@farmacia.com", "(55)5555-5555", "(45)95555-5555", endereco2));
            farmacias.forEach(f-> farmaciaService.salvar(f));
        }

        var medicamentos = medicamentoService.consultar();
        if(medicamentos.isEmpty()) {
            medicamentos.add(new Medicamento(1010, "Programapan", "Matrix", "2x ao dia", ": Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc eleifend", 111.00F, TipoMedicamento.COMUM ));
            medicamentos.add(new Medicamento(7473, "Cafex", "Colombia Farm", "4x ao dia", ": Pellentesque non ultricies mauris, ut lobortis elit. Cras nec cursus nibh", 51.50F, TipoMedicamento.COMUM ));
            medicamentos.add(new Medicamento(2233, "Estomazol", "Acme", "1x ao dia", ": Sed risus mauris,consectetur eget egestas vitae", 111.00F, TipoMedicamento.COMUM ));
            medicamentos.add(new Medicamento(8880, "Gelox", "Ice", "2x ao dia", ": Quisque quamorci, vulputate sit amet", 22.50F, TipoMedicamento.COMUM ));
            medicamentos.add(new Medicamento(5656, "Aspirazol", "Acme", "3x ao dia", ": Sed faucibus,libero iaculis pulvinar consequat, augue elit eleifend", 10.50F, TipoMedicamento.CONTROLADO ));
            medicamentos.add(new Medicamento(4040, "Propolizol", "Bee", "5x ao dia", ": : Nunc euismod ipsum purus, sit amet finibus libero ultricies in", 10.50F, TipoMedicamento.CONTROLADO ));
            medicamentos.forEach(m -> medicamentoService.salvar(m));
        }

        var estoques = estoqueService.consultar();
        if(estoques.isEmpty()) {
            estoques.add(new Estoque(farmacias.get(0).getCnpj(), medicamentos.get(0).getNroRegistro(), 12, LocalDateTime.now()));
            estoques.add(new Estoque(farmacias.get(0).getCnpj(), medicamentos.get(1).getNroRegistro(), 10, LocalDateTime.now()));
            estoques.add(new Estoque(farmacias.get(1).getCnpj(), medicamentos.get(1).getNroRegistro(), 2, LocalDateTime.now()));
            estoques.add(new Estoque(farmacias.get(1).getCnpj(), medicamentos.get(2).getNroRegistro(), 15, LocalDateTime.now()));
            estoques.add(new Estoque(farmacias.get(1).getCnpj(), medicamentos.get(3).getNroRegistro(), 16, LocalDateTime.now()));
            estoques.add(new Estoque(farmacias.get(1).getCnpj(), medicamentos.get(5).getNroRegistro(), 22, LocalDateTime.now()));
            estoques.forEach(e -> estoqueService.salvar(e));

        }
        return ResponseEntity.ok().build();
    }
}
