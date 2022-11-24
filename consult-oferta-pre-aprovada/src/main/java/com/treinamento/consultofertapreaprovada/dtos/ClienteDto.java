package com.treinamento.consultofertapreaprovada.dtos;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClienteDto {


    @NotNull
    private Long id;

    @NotEmpty(message = "Cpf não pode ser vázil")
    private String cpf;

    @NotNull(message = "Agencia não pode ser null")
    @Min(value = 0, message = "agencia no minimo 0")
    @Max(value = 999, message = "agencia no máximo 999")
    private Integer agencia;

    @NotNull(message = "Numero da Conta não Pode ser Nulo")
    @Min(value = 0, message = "numero da conta no minimo 0")
    @Max(value = 999999, message = "numero da conta no máximo 999999")
    private Integer numeroDaConta;

}
