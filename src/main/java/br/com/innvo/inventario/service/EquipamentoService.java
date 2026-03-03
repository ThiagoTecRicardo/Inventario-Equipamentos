package br.com.innvo.inventario.service;

import br.com.innvo.inventario.model.Equipamento;
import br.com.innvo.inventario.repository.EquipamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class EquipamentoService {

    @Autowired
    private EquipamentoRepository repository;



    public Equipamento salvar(Equipamento equipamento) {
        equipamento.setEquipamento(equipamento.getEquipamento());
        equipamento.setStatus(equipamento.getStatus());
        equipamento.setModelo(equipamento.getModelo());
        equipamento.setDataCompra(equipamento.getDataCompra());
        equipamento.setProjeto(equipamento.getProjeto());
        equipamento.setMarca(equipamento.getMarca());
        equipamento.setNumeroSerie(equipamento.getNumeroSerie());
        //equipamento.setFuncinario(equipamento.getFuncinario());

        return repository.save(equipamento);
    }

    public Equipamento atualizar(Long id, Equipamento equipamentoAtualizado) {
        return repository.findById(id)
                .map(equipamento -> {
                    equipamento.setMarca(equipamentoAtualizado.getMarca());
                    equipamento.setModelo(equipamentoAtualizado.getModelo());
                    equipamento.setNumeroSerie(equipamentoAtualizado.getNumeroSerie());
                    equipamento.setStatus(equipamentoAtualizado.getStatus());
                    equipamento.setEquipamento(equipamentoAtualizado.getEquipamento());
                    equipamento.setProjeto(equipamentoAtualizado.getProjeto());
                    equipamento.setDataCompra(equipamentoAtualizado.getDataCompra());
                    //equipamento.(equipamentoAtualizado.getFuncinario());
                    Equipamento atualizado = repository.save(equipamento);
                    return ResponseEntity.ok(atualizado);
                })
                .orElse(ResponseEntity.notFound().build()).getBody();
    }

    public List<Equipamento> listarTodos() {
        return repository.findAll();
    }

    public Optional<Equipamento> buscarPorId(Long id) {
        return repository.findById(id);
    }

    public List<Equipamento> buscarPorStatus(String status) {
        return repository.findByStatus(status);
    }

    public List<Equipamento> buscarPorMarca(String marca) {
        return repository.findByMarca(marca);
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }


    }
