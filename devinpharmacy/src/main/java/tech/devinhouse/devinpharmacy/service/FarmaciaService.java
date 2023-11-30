package tech.devinhouse.devinpharmacy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.devinhouse.devinpharmacy.model.Farmacia;
import tech.devinhouse.devinpharmacy.repositories.FarmaciaRepo;
import tech.devinhouse.devinpharmacy.exceptions.CnpjNaoEncontradoException;

import java.util.List;

@Service
public class FarmaciaService {

    @Autowired
    private FarmaciaRepo farmaciaRepo;

    public List<Farmacia> consultar() {
        return farmaciaRepo.findAll();
    }

    public Farmacia consultar(Long cnpj) {
        return farmaciaRepo.findById(cnpj).orElseThrow(() -> new CnpjNaoEncontradoException("CNPJ não encontrado"));
    }

    public Farmacia salvar(Farmacia farmacia) {
        return farmaciaRepo.save(farmacia);
    }
}
