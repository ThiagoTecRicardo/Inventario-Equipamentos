package br.com.innvo.inventario.controller;

import br.com.innvo.inventario.model.Notebook;
import br.com.innvo.inventario.repository.NotebookRepository;
import br.com.innvo.inventario.service.NotebookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/notebooks")
public class NotebookController {

    private final NotebookService service;

    public NotebookController(NotebookService service) {
        this.service = service;
    }


    @PostMapping
    public Notebook criar(@RequestBody Notebook notebook) {
        return service.salvar(notebook);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Notebook> atualizar(@PathVariable Long id, @RequestBody Notebook notebookAtualizado) {
        return service.buscarPorId(id)
                .map(notebook -> {
                    notebook.setMarca(notebookAtualizado.getMarca());
                    notebook.setModelo(notebookAtualizado.getModelo());
                    notebook.setNumeroSerie(notebookAtualizado.getNumeroSerie());
                    notebook.setStatus(notebookAtualizado.getStatus());
                    Notebook atualizado = service.salvar(notebook);
                    return ResponseEntity.ok(atualizado);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/listar")
    public List<Notebook> listar() {
        return service.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Notebook> buscar(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/status/{status}")
    public List<Notebook> buscarPorStatus(@PathVariable String status) {
        return service.buscarPorStatus(status);
    }

    @GetMapping("/marca/{marca}")
    public List<Notebook> buscarPorMarca(@PathVariable String marca) {
        return service.buscarPorMarca(marca);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        service.deletar(id);
    }
}
