package com.treinamento.consultofertapreaprovada.dtos;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClienteDto {


    @NotNull
    private Long id;

    @NotEmpty
    private String cpf;

    @NotNull
    private Integer agencia;

    @NotNull
    private Integer numeroDaConta;

}
