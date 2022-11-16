package com.treinamento.consultofertapreaprovada.utils;

import com.treinamento.consultofertapreaprovada.model.OfertaPreAprovada;

import java.time.Duration;
import java.time.LocalDate;

public class OfertaPreAprovadaCreator {

    public static OfertaPreAprovada createOfertaPreAprovadaToBeSave(){
        return OfertaPreAprovada.builder()
                .id(1L)
                .limitCredito(2000.0)
                .data_validade(LocalDate.now())
                .iscontrado(false)
                .mensalidade(17.0)
                .valorJuros(9.15)
                .build();
    }

    public static OfertaPreAprovada createOfertaPreAprovadaInvalidaToBeSave(){
        return OfertaPreAprovada.builder()
                .id(2L)
                .limitCredito(2000.0)
                .data_validade(LocalDate.of(2022, 9, 10))
                .iscontrado(true)
                .mensalidade(20.0)
                .valorJuros(6.15)
                .build();
    }

    public static boolean ofertaValida(OfertaPreAprovada ofertaPreAprovada){
        return DateUtils.dataValida(ofertaPreAprovada.getData_validade()) <= 0 && ofertaPreAprovada.getIscontrado() == false;
    }
}