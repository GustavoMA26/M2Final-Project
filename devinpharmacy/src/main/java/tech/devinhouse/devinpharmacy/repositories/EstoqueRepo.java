package tech.devinhouse.devinpharmacy.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.devinhouse.devinpharmacy.model.Estoque;
import tech.devinhouse.devinpharmacy.model.IdEstoque;

import java.util.List;
import java.util.Optional;

@Repository
public interface EstoqueRepo extends JpaRepository<Estoque, IdEstoque> {
    List<Estoque> findByCnpj(Long cnpj);
}
