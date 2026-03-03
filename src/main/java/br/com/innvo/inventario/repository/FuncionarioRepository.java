package br.com.innvo.inventario.repository;

import br.com.innvo.inventario.model.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FuncionarioRepository extends JpaRepository <Funcionario, Long>{

}
