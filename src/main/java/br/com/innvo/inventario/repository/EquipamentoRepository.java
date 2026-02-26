package br.com.innvo.inventario.repository;

import br.com.innvo.inventario.model.Equipamento;
import br.com.innvo.inventario.model.Tipo;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface EquipamentoRepository extends JpaRepository<Equipamento, Long> {
    List<Equipamento> findByStatus(String status);
    List<Equipamento> findByMarca(String marca);

}