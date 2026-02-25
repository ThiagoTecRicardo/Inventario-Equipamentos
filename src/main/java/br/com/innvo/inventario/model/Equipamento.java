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
    private Status status = Status.ESTOQUE;
    private LocalDateTime dataCompra = LocalDateTime.now();
    private String projeto;
    private Tipo equipamento = Tipo.NOTEBOOK;
    private String funcinario;

}

