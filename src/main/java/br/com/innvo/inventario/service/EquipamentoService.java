package br.com.innvo.inventario.service;

import br.com.innvo.inventario.model.Equipamento;
import br.com.innvo.inventario.model.Status;
import br.com.innvo.inventario.model.Tipo;
import br.com.innvo.inventario.repository.EquipamentoRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class EquipamentoService {
    private final EquipamentoRepository repository;

    public EquipamentoService(EquipamentoRepository repository) {
        this.repository = repository;
    }

    public Equipamento salvar(Equipamento equipamento) {
        equipamento.setEquipamento(equipamento.getEquipamento());
        equipamento.setStatus(equipamento.getStatus());
        equipamento.setModelo(equipamento.getModelo());
        equipamento.setDataCompra(equipamento.getDataCompra());
        equipamento.setProjeto(equipamento.getProjeto());
        equipamento.setMarca(equipamento.getMarca());
        equipamento.setNumeroSerie(equipamento.getNumeroSerie());
        equipamento.setFuncinario(equipamento.getFuncinario());

        return repository.save(equipamento);
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