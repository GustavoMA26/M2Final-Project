package tech.devinhouse.devinpharmacy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.devinhouse.devinpharmacy.model.Estoque;
import tech.devinhouse.devinpharmacy.repositories.EstoqueRepo;

import java.util.List;

@Service
public class EstoqueService {

    @Autowired
    private EstoqueRepo estoqueRepo;
    public List<Estoque> consultar() {
        return estoqueRepo.findAll();
    }
    public Estoque salvar(Estoque estoque) {
        return estoqueRepo.save(estoque);
    }
}
