package tech.devinhouse.devinpharmacy.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.devinhouse.devinpharmacy.dto.EstoqueResponse;
import tech.devinhouse.devinpharmacy.model.Estoque;
import tech.devinhouse.devinpharmacy.repositories.EstoqueRepo;
import tech.devinhouse.devinpharmacy.service.EstoqueService;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/estoque")
public class EstoqueController {

    @Autowired
    private EstoqueRepo estoqueRepo;

    @Autowired
    private EstoqueService estoqueService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/{cnpj}")
    public ResponseEntity <List<EstoqueResponse>> consultar(@PathVariable("cnpj") Long cnpj) {
        Optional<List<Estoque>> estoqueOpt = estoqueService.consultarPorCnpj(cnpj);
        if(estoqueOpt.isPresent()) {
            List<Estoque> estoques = estoqueOpt.get();
            List<EstoqueResponse> response = estoques.stream()
                    .map(estoque -> modelMapper.map(estoque, EstoqueResponse.class))
                    .collect(Collectors.toList());
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.emptyList());
        }
    }
}
