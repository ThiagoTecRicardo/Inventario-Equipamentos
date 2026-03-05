package br.com.innvo.inventario.controller;

import br.com.innvo.inventario.model.Equipamento;
import br.com.innvo.inventario.model.Funcionario;
import br.com.innvo.inventario.model.StatusEquipamento;
import br.com.innvo.inventario.repository.FuncionarioRepository;
import br.com.innvo.inventario.service.EquipamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/equipamento")
public class EquipamentoController {

    @Autowired
    private EquipamentoService service;

    @Autowired
    private FuncionarioRepository  funcionarioRepository;


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
        try {
            Equipamento equipamentoSalvo = service.atualizar(id, equipamentoAtualizado);
            return ResponseEntity.ok(equipamentoSalvo);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }


    @PutMapping("/{id}/desvincular")
    public ResponseEntity<Equipamento> desvincular(@PathVariable Long id) {
        Equipamento equipamentoNoBanco = service.buscarPorId(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Equipamento não encontrado"));

        // Limpa os campos
        equipamentoNoBanco.setFuncionario(null);
        equipamentoNoBanco.setProjeto(null);
        equipamentoNoBanco.setStatus(StatusEquipamento.ESTOQUE);

        // Salva o objeto alterado
        Equipamento atualizado = service.salvar(equipamentoNoBanco);

        return ResponseEntity.ok(atualizado);
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
