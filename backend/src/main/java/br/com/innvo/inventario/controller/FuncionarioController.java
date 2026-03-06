package br.com.innvo.inventario.controller;

import br.com.innvo.inventario.model.Equipamento;
import br.com.innvo.inventario.model.Funcionario;
import br.com.innvo.inventario.repository.FuncionarioRepository;
import br.com.innvo.inventario.service.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/funcionario")
@CrossOrigin(origins = "http://localhost:4200")
public class FuncionarioController {

    @Autowired
    private FuncionarioRepository  funcionarioRepository;

    @Autowired
    private FuncionarioService funcionarioService;



    @GetMapping("/listar")
    public List<Funcionario> listar() {
        return funcionarioRepository.findAll();
    }

    @PostMapping("/registrar")
    public Funcionario criar(@RequestBody Funcionario funcionario) {
        return  funcionarioService.salvar(funcionario);
    }

    @PutMapping("/atualizar/{codigo}")
    public ResponseEntity<Funcionario> atualizar(@PathVariable Long codigo, @RequestBody Funcionario funcionario) {
        try {
            Funcionario funcionarioSalvo = funcionarioService.atualizar(codigo,  funcionario);
            return ResponseEntity.ok(funcionarioSalvo);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }



}
