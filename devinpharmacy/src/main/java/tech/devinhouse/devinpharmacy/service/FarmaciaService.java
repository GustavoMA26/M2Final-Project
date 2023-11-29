package tech.devinhouse.devinpharmacy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.devinhouse.devinpharmacy.model.Farmacia;
import tech.devinhouse.devinpharmacy.repositories.FarmaciaRepo;

import java.util.List;

@Service
public class FarmaciaService {

    @Autowired
    private FarmaciaRepo farmaciaRepo;

    public List<Farmacia> consultar() {
        return farmaciaRepo.findAll();
    }

    public Farmacia salvar(Farmacia farmacia) {
        return farmaciaRepo.save(farmacia);
    }
}
