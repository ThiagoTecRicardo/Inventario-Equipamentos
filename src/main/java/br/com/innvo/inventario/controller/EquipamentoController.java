package br.com.innvo.inventario.controller;

import br.com.innvo.inventario.model.Equipamento;
import br.com.innvo.inventario.service.EquipamentoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/equipamento")
public class EquipamentoController {

    private final EquipamentoService service;

    public EquipamentoController(EquipamentoService service) {
        this.service = service;
    }


    @PostMapping("/registrar")
    public Equipamento criar(@RequestBody Equipamento equipamento) {
        return service.salvar(equipamento);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Equipamento> atualizar(@PathVariable Long id, @RequestBody Equipamento equipamentoAtualizado) {
        return service.buscarPorId(id)
                .map(equipamento -> {
                    equipamento.setMarca(equipamentoAtualizado.getMarca());
                    equipamento.setModelo(equipamentoAtualizado.getModelo());
                    equipamento.setNumeroSerie(equipamentoAtualizado.getNumeroSerie());
                    equipamento.setStatus(equipamentoAtualizado.getStatus());
                    equipamento.setEquipamento(equipamentoAtualizado.getEquipamento());
                    equipamento.setProjeto(equipamentoAtualizado.getProjeto());
                    equipamento.setDataCompra(equipamentoAtualizado.getDataCompra());
                    Equipamento atualizado = service.salvar(equipamento);
                    return ResponseEntity.ok(atualizado);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/listar")
    public List<Equipamento> listar() {
        return service.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Equipamento> buscar(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/status/{status}")
    public List<Equipamento> buscarPorStatus(@PathVariable String status) {
        return service.buscarPorStatus(status);
    }

    @GetMapping("/marca/{marca}")
    public List<Equipamento> buscarPorMarca(@PathVariable String marca) {
        return service.buscarPorMarca(marca);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        service.deletar(id);
    }
}
