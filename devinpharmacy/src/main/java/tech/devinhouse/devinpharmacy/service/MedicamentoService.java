package tech.devinhouse.devinpharmacy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.devinhouse.devinpharmacy.model.Medicamento;
import tech.devinhouse.devinpharmacy.repositories.MedicamentoRepo;

import java.util.List;

@Service
public class MedicamentoService {

    @Autowired
    private MedicamentoRepo medicamentoRepo;

    public List<Medicamento> consultar() {
        return medicamentoRepo.findAll();
    }

    public Medicamento salvar(Medicamento medicamento) {
        return medicamentoRepo.save(medicamento);
    }
}
