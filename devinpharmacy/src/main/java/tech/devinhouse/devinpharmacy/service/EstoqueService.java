package tech.devinhouse.devinpharmacy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.devinhouse.devinpharmacy.exceptions.CnpjRegistroNaoCadastradoException;
import tech.devinhouse.devinpharmacy.model.Estoque;
import tech.devinhouse.devinpharmacy.model.Farmacia;
import tech.devinhouse.devinpharmacy.model.IdEstoque;
import tech.devinhouse.devinpharmacy.repositories.EstoqueRepo;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class EstoqueService {

    @Autowired
    private EstoqueRepo estoqueRepo;

    @Autowired
    private FarmaciaService farmaciaService;

    @Autowired
    private MedicamentoService medicamentoService;

    public List<Estoque> consultar() {
        return estoqueRepo.findAll();
    }

    public Optional<List<Estoque>> consultarPorCnpj(Long cnpj) {
        return Optional.ofNullable(estoqueRepo.findByCnpj(cnpj));
    }
    public Estoque salvar(Estoque estoque) {
        return estoqueRepo.save(estoque);
    }

    public Estoque incluirEstoque(Estoque estoque) {
        Farmacia farmaciaAtualOpt = farmaciaService.consultar(estoque.getCnpj());
        boolean medicamentoAtualOpt = medicamentoService.temMedicamento(estoque.getNroRegistro());

        if (farmaciaAtualOpt != null && medicamentoAtualOpt) {
            Optional<Estoque> estoqueAtualOpt = estoqueRepo.findById(new IdEstoque(estoque.getCnpj(), estoque.getNroRegistro()));

            if (estoqueAtualOpt.isPresent()) {
                Estoque estoqueExistente = estoqueAtualOpt.get();
                int novoEstoque = estoqueExistente.getQuantidade() + estoque.getQuantidade();
                estoqueExistente.setQuantidade(novoEstoque);
                estoqueExistente.setDataAtualizacao(LocalDateTime.now());

                return estoqueRepo.save(estoqueExistente);
            } else {
                estoque.setDataAtualizacao(LocalDateTime.now());
                return estoqueRepo.save(estoque);
            }
        } else {
            throw new CnpjRegistroNaoCadastradoException();
        }
    }
}
