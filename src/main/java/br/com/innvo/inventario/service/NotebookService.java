package br.com.innvo.inventario.service;

import br.com.innvo.inventario.model.Notebook;
import br.com.innvo.inventario.repository.NotebookRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class NotebookService {
    private final NotebookRepository repository;

    public NotebookService(NotebookRepository repository) {
        this.repository = repository;
    }

    public Notebook salvar(Notebook notebook) {
        return repository.save(notebook);
    }

    public List<Notebook> listarTodos() {
        return repository.findAll();
    }

    public Optional<Notebook> buscarPorId(Long id) {
        return repository.findById(id);
    }

    public List<Notebook> buscarPorStatus(String status) {
        return repository.findByStatus(status);
    }

    public List<Notebook> buscarPorMarca(String marca) {
        return repository.findByMarca(marca);
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }
}