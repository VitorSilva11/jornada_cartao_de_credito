package com.treinamento.consultofertapreaprovada.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 11)
    private String cpf;

    @Column(nullable = false)
    private Integer agencia;

    @Column(nullable = false)
    private Integer numeroDaConta;

    @OneToMany(mappedBy = "cliente")
    private List<OfertaPreAprovada> ofertasPreAprovadas;

}
