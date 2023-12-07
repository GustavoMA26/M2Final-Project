package tech.devinhouse.devinpharmacy.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.devinhouse.devinpharmacy.dto.EstoqueRequest;
import tech.devinhouse.devinpharmacy.dto.EstoqueResponse;
import tech.devinhouse.devinpharmacy.dto.TrocaRequest;
import tech.devinhouse.devinpharmacy.dto.TrocaResponse;
import tech.devinhouse.devinpharmacy.exceptions.CnpjRegistroNaoCadastradoException;
import tech.devinhouse.devinpharmacy.model.Estoque;
import tech.devinhouse.devinpharmacy.repositories.EstoqueRepo;
import tech.devinhouse.devinpharmacy.service.EstoqueService;

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
    @PostMapping
    public ResponseEntity<EstoqueResponse> adicionarEstoque(@RequestBody @Valid EstoqueRequest request) {
        Estoque estoque = modelMapper.map(request, Estoque.class);

        Estoque estoqueAtual = estoqueService.incluirEstoque(estoque);

        if(estoqueAtual!=null) {
            EstoqueResponse response = modelMapper.map(estoqueAtual, EstoqueResponse.class);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @DeleteMapping
    public ResponseEntity<EstoqueResponse> venderEstoque(@RequestBody @Valid EstoqueRequest request) {
        Estoque estoque = modelMapper.map(request, Estoque.class);
        Estoque estoqueAtualizado = estoqueService.venderEstoque(estoque);

        if (estoqueAtualizado!= null) {
            EstoqueResponse response = modelMapper.map(estoqueAtualizado, EstoqueResponse.class);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @Transactional
    @PutMapping
    public ResponseEntity<TrocaResponse> trocarEstoque(@RequestBody @Valid TrocaRequest request) {
        TrocaResponse trocaResponse = estoqueService.trocaEstoque(request);

        if(trocaResponse!= null) {
        return ResponseEntity.status(HttpStatus.OK).body(trocaResponse);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
