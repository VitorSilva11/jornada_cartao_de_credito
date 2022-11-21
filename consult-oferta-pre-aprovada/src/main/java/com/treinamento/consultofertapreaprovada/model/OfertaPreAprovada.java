package com.treinamento.consultofertapreaprovada.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OfertaPreAprovada {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Double limitCredito;

    @Column(nullable = false)
    private Double valorJuros;

    @Column(nullable = false)
    private Double mensalidade;

    @Column(nullable = false)
    private LocalDate data_validade;

    @Column(nullable = false)
    private Boolean contratado;


    @ManyToOne
    @JoinColumn(name = "id_cliente")
    @JsonIgnore
    private Cliente cliente;
}
