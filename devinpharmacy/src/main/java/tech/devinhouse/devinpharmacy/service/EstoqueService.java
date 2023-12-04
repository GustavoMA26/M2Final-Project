package tech.devinhouse.devinpharmacy.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.devinhouse.devinpharmacy.dto.EstoqueResponse;
import tech.devinhouse.devinpharmacy.exceptions.CnpjExistenteException;
import tech.devinhouse.devinpharmacy.model.Estoque;
import tech.devinhouse.devinpharmacy.repositories.EstoqueRepo;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class EstoqueService {

    @Autowired
    private EstoqueRepo estoqueRepo;

    public List<Estoque> consultar() {
        return estoqueRepo.findAll();
    }

    public Optional<List<Estoque>> consultarPorCnpj(Long cnpj) {
        return Optional.ofNullable(estoqueRepo.findByCnpj(cnpj));
    }
    public Estoque salvar(Estoque estoque) {
        return estoqueRepo.save(estoque);
    }
}
