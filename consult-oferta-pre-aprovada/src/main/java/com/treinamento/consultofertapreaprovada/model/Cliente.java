package com.treinamento.consultofertapreaprovada.model;


import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
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
