package tech.devinhouse.devinpharmacy.controller;

import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.devinhouse.devinpharmacy.dto.MedicamentoRequest;
import tech.devinhouse.devinpharmacy.dto.MedicamentoResponse;
import tech.devinhouse.devinpharmacy.model.Medicamento;
import tech.devinhouse.devinpharmacy.repositories.MedicamentoRepo;
import tech.devinhouse.devinpharmacy.service.MedicamentoService;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/medicamentos")
public class MedicamentosController {

    @Autowired
    private MedicamentoRepo medicamentoRepo;

    @Autowired
    private MedicamentoService medicamentoService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<MedicamentoResponse>> consultarMedicamentos() {
        List<Medicamento> medicamentos = medicamentoService.consultar();
        List<MedicamentoResponse> response = new ArrayList<>();
        for(Medicamento medicamento : medicamentos) {
            MedicamentoResponse m = modelMapper.map(medicamento, MedicamentoResponse.class);
            response.add(m);
        }
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<MedicamentoResponse> criarMedicamento(@RequestBody @Valid MedicamentoRequest request) {
        var medicamento = modelMapper.map(request, Medicamento.class);
        medicamento = medicamentoService.salvar(medicamento);
        var response = modelMapper.map(medicamento, MedicamentoResponse.class);
        return ResponseEntity.created(URI.create(medicamento.getNroRegistro().toString())).body(response);
    }
}
