package com.treinamento.consultofertapreaprovada.utils;

import com.treinamento.consultofertapreaprovada.dtos.ClienteDto;
import com.treinamento.consultofertapreaprovada.model.Cliente;
import com.treinamento.consultofertapreaprovada.model.OfertaPreAprovada;
import org.springframework.beans.BeanUtils;

import java.time.Duration;
import java.time.LocalDate;

public class OfertaPreAprovadaCreator {




    public static OfertaPreAprovada createOfertaPreAprovadaToBeSave(){

        Cliente cliente = new Cliente();

        BeanUtils.copyProperties(ClienteCreator.createClienteDTOToBeSave(), cliente);

        return OfertaPreAprovada.builder()
                .id(1L)
                .limitCredito(2000.0)
                .data_validade(LocalDate.now())
                .contratado(false)
                .mensalidade(17.0)
                .valorJuros(9.15)
                .cliente(cliente)
                .build();
    }

    public static OfertaPreAprovada createOfertaPreAprovadaInvalidaToBeSave(){

        Cliente cliente = new Cliente();

        BeanUtils.copyProperties(ClienteCreator.createClienteDTOToBeSave(), cliente);

        return OfertaPreAprovada.builder()
                .id(2L)
                .limitCredito(2000.0)
                .data_validade(LocalDate.of(2022, 9, 10))
                .contratado(true)
                .mensalidade(20.0)
                .valorJuros(6.15)
                .cliente(cliente)
                .build();
    }

    public static boolean ofertaValida(OfertaPreAprovada ofertaPreAprovada){
        return DateUtils.dataValida(ofertaPreAprovada.getData_validade()) <= 0 && ofertaPreAprovada.getContratado() == false;
    }
}