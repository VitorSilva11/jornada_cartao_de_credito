package com.treinamento.consultofertapreaprovada.controller;

import com.treinamento.consultofertapreaprovada.dtos.ClienteDto;
import com.treinamento.consultofertapreaprovada.exceptions.ClientNotFound;
import com.treinamento.consultofertapreaprovada.exceptions.OfertaNotFound;
import com.treinamento.consultofertapreaprovada.model.OfertaPreAprovada;
import com.treinamento.consultofertapreaprovada.service.OfertaPreAprovadaService;
import com.treinamento.consultofertapreaprovada.utils.ClienteCreator;
import com.treinamento.consultofertapreaprovada.utils.OfertaPreAprovadaCreator;
import org.assertj.core.api.Assertions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.core.MethodParameter;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.Collections;
import java.util.List;

@ExtendWith(SpringExtension.class)
public class OfertaPreAprovadaControllerTest {

    @InjectMocks
    private OfertaPreAprovadaController ofertaPreAprovadaController;

    @Mock
    private OfertaPreAprovadaService ofertaPreAprovadaServiceMock;


    @BeforeEach()
    public void setUp() {

        BDDMockito.when(ofertaPreAprovadaServiceMock.getOfertasValidas(ArgumentMatchers.any()))
                .thenReturn(List.of(OfertaPreAprovadaCreator.createOfertaPreAprovadaToBeSave()));

    }


    @Test
    public void Retorna_ListaDeOfertasPreAprovadasValidas_ComSucesso(){

        List<OfertaPreAprovada> lista = ofertaPreAprovadaController
                .listOfertasPreAprovadas(ClienteCreator.createClienteDTOToBeSave()).getBody();

        Assertions.assertThat(lista).isNotNull();

        Assertions.assertThat(lista)
                .isNotEmpty()
                .hasSize(1);

    }


    @Test
    public void retorna_cliente_naoEncontrado(){

        BDDMockito.when(ofertaPreAprovadaServiceMock.getOfertasValidas(ArgumentMatchers.any()))
                .thenThrow(new ClientNotFound("Cliente não encontrado"));

        Assertions.assertThatExceptionOfType(ClientNotFound.class)
                .isThrownBy(() -> this.ofertaPreAprovadaServiceMock.getOfertasValidas(ClienteCreator.createClienteDTOToBeSave()));

    }

    @Test
    public void retorna_oferta_naoEncontrada(){
        BDDMockito.when(ofertaPreAprovadaServiceMock.getOfertasValidas(ArgumentMatchers.any()))
                .thenThrow(new OfertaNotFound("Oferta Não Encontrada"));

        Assertions.assertThatExceptionOfType(OfertaNotFound.class)
                .isThrownBy(() -> this.ofertaPreAprovadaController.listOfertasPreAprovadas(ClienteCreator.createClienteDTOToBeSave()));
    }

    @Test
    public void retorna_erroDeValidacaoCpf(){


        ClienteDto clienteDto = ClienteCreator.createClienteDTOToBeSave();

        clienteDto.setCpf("");

        Assertions.assertThatExceptionOfType(MethodArgumentNotValidException.class)
                .isThrownBy(() -> this.ofertaPreAprovadaController.listOfertasPreAprovadas(clienteDto));
    }

}