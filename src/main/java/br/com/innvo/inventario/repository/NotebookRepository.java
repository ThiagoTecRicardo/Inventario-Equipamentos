package br.com.innvo.inventario.repository;

import br.com.innvo.inventario.model.Notebook;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface NotebookRepository extends JpaRepository<Notebook, Long> {
    List<Notebook> findByStatus(String status);
    List<Notebook> findByMarca(String marca);
}