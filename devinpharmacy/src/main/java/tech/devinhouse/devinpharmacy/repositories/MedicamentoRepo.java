package tech.devinhouse.devinpharmacy.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.devinhouse.devinpharmacy.model.Medicamento;

@Repository
public interface MedicamentoRepo extends JpaRepository<Medicamento, Integer> {
}
