package br.com.innvo.inventario.controller;

import br.com.innvo.inventario.model.Equipamento;
import br.com.innvo.inventario.model.StatusEquipamento;
import br.com.innvo.inventario.service.EquipamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/equipamento")
public class EquipamentoController {

    @Autowired
    private EquipamentoService service;


    @GetMapping("/listar")
    public List<Equipamento> listar() {
        return service.listarTodos();
    }

    @PostMapping("/registrar")
    public ResponseEntity<Equipamento> criar(@RequestBody Equipamento equipamento) {
        Equipamento equipamentoSalvo = service.salvar(equipamento);
        return ResponseEntity.status(HttpStatus.CREATED).body(equipamentoSalvo);
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Equipamento> atualizar(@PathVariable Long id, @RequestBody Equipamento equipamentoAtualizado) {
        Equipamento equipamentoSalvo = service.atualizar(id, equipamentoAtualizado);
        return ResponseEntity.ok(equipamentoSalvo);
       }

    @PutMapping("/{id}/desvincular")
    public ResponseEntity<Equipamento> desvincular(@PathVariable Long id) {

        // Busca o notebook ou retorna erro 404 se não existir

        Equipamento equipamento = service.buscarPorId(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Notebook não encontrado"));

        // Remove o vínculo com o funcionário e limpas os campos de cliente e status

        //equipamento.setFuncinario(null);
        equipamento.setProjeto(null);
        equipamento.setStatus(StatusEquipamento.ESTOQUE);

        // Salva a alteração no banco
        return ResponseEntity.ok(service.salvar(equipamento));
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
