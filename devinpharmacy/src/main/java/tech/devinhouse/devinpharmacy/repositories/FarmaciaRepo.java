package tech.devinhouse.devinpharmacy.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.devinhouse.devinpharmacy.model.Farmacia;

@Repository
public interface FarmaciaRepo extends JpaRepository<Farmacia, Long> {
}
