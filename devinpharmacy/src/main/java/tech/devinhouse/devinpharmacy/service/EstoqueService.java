package tech.devinhouse.devinpharmacy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.devinhouse.devinpharmacy.dto.TrocaRequest;
import tech.devinhouse.devinpharmacy.dto.TrocaResponse;
import tech.devinhouse.devinpharmacy.exceptions.CnpjRegistroNaoCadastradoException;
import tech.devinhouse.devinpharmacy.exceptions.QtdEstoqueIndisponivelException;
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

    public Estoque venderEstoque(Estoque estoque) {
        Farmacia farmaciaAtual = farmaciaService.consultar(estoque.getCnpj());
        boolean medicamentoAtual = medicamentoService.temMedicamento(estoque.getNroRegistro());

        if (farmaciaAtual != null && medicamentoAtual) {
            Optional<Estoque> estoqueAtualOpt = estoqueRepo.findById(new IdEstoque(estoque.getCnpj(), estoque.getNroRegistro()));

            if (estoqueAtualOpt.isPresent() && estoqueAtualOpt.get().getQuantidade() >= estoque.getQuantidade()) {
                Estoque estoqueExistente = estoqueAtualOpt.get();
                int novoEstoque = estoqueExistente.getQuantidade() - estoque.getQuantidade();
                estoqueExistente.setQuantidade(novoEstoque);
                estoqueExistente.setDataAtualizacao(LocalDateTime.now());

                Estoque estoqueAtualizado = estoqueRepo.save(estoqueExistente);

                if (novoEstoque == 0) {
                    estoqueRepo.delete(estoqueAtualizado);
                }
                return estoqueAtualizado;

            } else {
                throw new QtdEstoqueIndisponivelException();
            }

        } else {
            throw new CnpjRegistroNaoCadastradoException();
        }
    }

    public TrocaResponse trocaEstoque(TrocaRequest request) {
        Farmacia farmaciaOrigem = farmaciaService.consultar(request.getCnpjOrigem());
        Farmacia farmaciaDestino = farmaciaService.consultar(request.getCnpjDestino());
        boolean medicamentoOrigem = medicamentoService.temMedicamento(request.getNroRegistro());

        if (farmaciaOrigem != null && farmaciaDestino != null && medicamentoOrigem) {
            Optional<Estoque> estoqueOrigemOpt = estoqueRepo.
                    findById(new IdEstoque(request.getCnpjOrigem(), request.getNroRegistro()));
            Optional<Estoque> estoqueDestinoOpt = estoqueRepo.
                    findById(new IdEstoque(request.getCnpjDestino(), request.getNroRegistro()));

            if (estoqueOrigemOpt.isPresent() && estoqueOrigemOpt.get().getQuantidade() >= request.getQuantidade()) {
                Estoque estoqueOrigemExistente = estoqueOrigemOpt.get();
                int novoEstoqueOrigem = estoqueOrigemExistente.getQuantidade() - request.getQuantidade();
                estoqueOrigemExistente.setQuantidade(novoEstoqueOrigem);

                Estoque estoqueAtualizadoOrigem = estoqueRepo.save(estoqueOrigemExistente);


                if (novoEstoqueOrigem == 0) {
                    estoqueRepo.delete(estoqueAtualizadoOrigem);
                }

                int novoEstoqueDestino;
                if (estoqueDestinoOpt.isPresent()) {
                    Estoque estoqueDestinoExistente = estoqueDestinoOpt.get();
                    novoEstoqueDestino = estoqueDestinoExistente.getQuantidade() + request.getQuantidade();
                    estoqueDestinoExistente.setQuantidade(novoEstoqueDestino);
                    estoqueRepo.save(estoqueDestinoExistente);

                } else {
                    Estoque estoqueNovo = new Estoque(
                            request.getCnpjDestino(),
                            request.getNroRegistro(),
                            request.getQuantidade(),
                            LocalDateTime.now()
                    );
                    Estoque estoqueNovoSalvo = estoqueRepo.save(estoqueNovo);
                    novoEstoqueDestino = estoqueNovoSalvo.getQuantidade();

                }
                return new TrocaResponse(
                        request.getNroRegistro(),
                        request.getCnpjOrigem(),
                        novoEstoqueOrigem,
                        request.getCnpjDestino(),
                        novoEstoqueDestino,
                        LocalDateTime.now()
                );

            } else {
                throw new QtdEstoqueIndisponivelException();
            }

        } else {
            throw new CnpjRegistroNaoCadastradoException();
        }
    }
}
