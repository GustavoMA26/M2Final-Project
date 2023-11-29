package tech.devinhouse.devinpharmacy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.devinhouse.devinpharmacy.dto.FarmaciaResponse;
import tech.devinhouse.devinpharmacy.model.Farmacia;
import tech.devinhouse.devinpharmacy.service.FarmaciaService;
import org.modelmapper.ModelMapper;

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
}
