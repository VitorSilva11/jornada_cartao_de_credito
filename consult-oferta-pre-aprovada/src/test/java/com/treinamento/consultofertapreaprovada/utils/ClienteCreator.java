package com.treinamento.consultofertapreaprovada.utils;

import com.treinamento.consultofertapreaprovada.dtos.ClienteDto;

public class ClienteCreator {

    public static ClienteDto createClienteDTOToBeSave(){
        return ClienteDto.builder()
                .id(1L)
                .cpf("76589108743")
                .agencia(456)
                .numeroDaConta(9878832)
                .build();
    }
}
