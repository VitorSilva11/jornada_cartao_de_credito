package com.treinamento.consultofertapreaprovada.utils;

import java.time.Duration;
import java.time.LocalDate;

public class DateUtils {
    public static Long dataValida (LocalDate dataSalva){
        LocalDate dataNow = LocalDate.now();

        Long tempo = Duration.between(dataSalva.atStartOfDay(), dataNow.atStartOfDay()).toDays();

        return tempo;
    }
}