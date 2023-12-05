package tech.devinhouse.devinpharmacy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.devinhouse.devinpharmacy.exceptions.CnpjNaoEncontradoException;
import tech.devinhouse.devinpharmacy.exceptions.MedicamentoExistenteException;
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
        boolean existente = medicamentoRepo.existsById(medicamento.getNroRegistro());
        if (existente) {
            throw new MedicamentoExistenteException();
        }
        medicamentoRepo.save(medicamento);
        return medicamento;
    }
    public boolean temMedicamento(Integer nroRegistro) {
        return medicamentoRepo.existsById(nroRegistro);
    }

}
