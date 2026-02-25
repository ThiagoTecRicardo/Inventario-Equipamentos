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
    private Status status;
    private LocalDateTime dataCompra;
    private String projeto;
    private Tipo equipamento;

    @OneToOne
    @JoinColumn(name = "funcionario_id")
    private Funcionario funcionario;

}

