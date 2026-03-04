package br.com.innvo.inventario.model;



import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter
@Entity
public class Equipamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String marca;
    private String modelo;
    private String numeroSerie;
    private StatusEquipamento status = StatusEquipamento.ESTOQUE;
    private LocalDateTime dataCompra = LocalDateTime.now();
    private String projeto;
    private TipoEquipamento equipamento = TipoEquipamento.NOTEBOOK;

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    @ManyToOne
    @JoinColumn(name = "codigo_funcionario")
    private Funcionario funcionario;

}

