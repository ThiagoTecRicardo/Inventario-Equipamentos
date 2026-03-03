package br.com.innvo.inventario.service;

import br.com.innvo.inventario.model.Funcionario;
import br.com.innvo.inventario.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class FuncionarioService {

    @Autowired
    private FuncionarioRepository funcionarioRepository;


    public Funcionario salvar(Funcionario funcionario) {
         return funcionarioRepository.save(funcionario);
    }


    public Funcionario atualizar(Long codigo){
        Funcionario funcionarioAtualizado = buscarFuncionarioPeloCodigo(codigo);
        return funcionarioRepository.saveAndFlush(funcionarioAtualizado);
    }

    public Funcionario buscarFuncionarioPeloCodigo(Long codigo) {

        return funcionarioRepository.findById(codigo).orElseThrow(IllegalArgumentException::new);
    }





}
