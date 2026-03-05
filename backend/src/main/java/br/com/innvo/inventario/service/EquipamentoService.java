package br.com.innvo.inventario.service;

import br.com.innvo.inventario.model.Equipamento;
import br.com.innvo.inventario.model.Funcionario;
import br.com.innvo.inventario.repository.EquipamentoRepository;
import br.com.innvo.inventario.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class EquipamentoService {

    @Autowired
    private EquipamentoRepository repository;

    @Autowired
    private FuncionarioRepository funcionarioRepository;



    public Equipamento salvar(Equipamento equipamento) {
        // Verifica se foi enviado um funcionário com código no JSON
        if (equipamento.getFuncionario() != null && equipamento.getFuncionario().getCodigo() != null) {

            Long idFuncionario = equipamento.getFuncionario().getCodigo();

            // Busca o funcionário real no banco de dados
            Funcionario funcionarioExistente = funcionarioRepository.findById(idFuncionario)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Funcionário não encontrado com código: " + idFuncionario));

            // Substitui o objeto "solto" (detached) pelo objeto monitorado pelo banco (managed)
            equipamento.setFuncionario(funcionarioExistente);
        }

        return repository.save(equipamento);
    }

    public Equipamento atualizar(Long id, Equipamento equipamentoAtualizado) {
        return repository.findById(id)
                .map(equipamento -> {
                    equipamento.setMarca(equipamentoAtualizado.getMarca());
                    equipamento.setModelo(equipamentoAtualizado.getModelo());
                    equipamento.setNumeroSerie(equipamentoAtualizado.getNumeroSerie());
                    equipamento.setStatus(equipamentoAtualizado.getStatus());
                    equipamento.setEquipamento(equipamentoAtualizado.getEquipamento());
                    equipamento.setProjeto(equipamentoAtualizado.getProjeto());

                    // IMPORTANTE: Só atualiza a data se for enviada, senão mantém a original
                    if (equipamentoAtualizado.getDataCompra() != null) {
                        equipamento.setDataCompra(equipamentoAtualizado.getDataCompra());
                    }

                    // Trata o funcionário: se vier nulo no JSON, desvincula
                    equipamento.setFuncionario(equipamentoAtualizado.getFuncionario());

                    return repository.save(equipamento);
                })
                .orElseThrow(() -> new RuntimeException("Equipamento não encontrado com id: " + id));
    }

    public List<Equipamento> listarTodos() {
        return repository.findAll();
    }

    public Optional<Equipamento> buscarPorId(Long id) {
        return repository.findById(id);
    }

    public List<Equipamento> buscarPorStatus(String status) {
        return repository.findByStatus(status);
    }

    public List<Equipamento> buscarPorMarca(String marca) {
        return repository.findByMarca(marca);
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }


    }
