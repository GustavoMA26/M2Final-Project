package tech.devinhouse.devinpharmacy.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.devinhouse.devinpharmacy.dto.FarmaciaRequest;
import tech.devinhouse.devinpharmacy.dto.FarmaciaResponse;
import tech.devinhouse.devinpharmacy.model.Farmacia;
import tech.devinhouse.devinpharmacy.service.FarmaciaService;
import org.modelmapper.ModelMapper;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/farmacias")
public class FarmaciasController {

    @Autowired
    private FarmaciaService farmaciaService;

    @Autowired
    private ModelMapper mapper;

    @GetMapping
    public ResponseEntity<List<FarmaciaResponse>> consultarFarmacias() {
        List<Farmacia> farmacias = farmaciaService.consultar();
        List<FarmaciaResponse> response = new ArrayList<>();
        for(Farmacia farmacia : farmacias) {
            FarmaciaResponse f = mapper.map(farmacia, FarmaciaResponse.class);
            response.add(f);
        }
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{cnpj}")
    public ResponseEntity<FarmaciaResponse> consultarFarmacia(@PathVariable("cnpj") Long cnpj) {
        Farmacia farmacia = farmaciaService.consultar(cnpj);
        FarmaciaResponse response = mapper.map(farmacia, FarmaciaResponse.class);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<FarmaciaResponse> criarFarmacia(@RequestBody @Valid FarmaciaRequest request) {
        var farmacia = mapper.map(request, Farmacia.class);
        farmacia = farmaciaService.salvar(farmacia);
        var response = mapper.map(farmacia, FarmaciaResponse.class);
        return ResponseEntity.created(URI.create(farmacia.getCnpj().toString())).body(response);

    }
}
