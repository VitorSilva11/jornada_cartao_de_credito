package com.treinamento.consultofertapreaprovada.service;

import com.treinamento.consultofertapreaprovada.model.OfertaPreAprovada;
import com.treinamento.consultofertapreaprovada.repositories.OfertaPreAprovadaRepository;
import com.treinamento.consultofertapreaprovada.utils.OfertaPreAprovadaCreator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@ExtendWith(SpringExtension.class)
class OfertaPreAprovadaServiceTest {

    @InjectMocks
    private OfertaPreAprovadaService ofertaPreAprovadaService;

    @Mock
    private OfertaPreAprovadaRepository ofertaPreAprovadaRepositoryMock;


    @BeforeEach()
    public void setUp() {
        List<OfertaPreAprovada> listOfertaPreAprovada = Arrays.asList(OfertaPreAprovadaCreator.createOfertaPreAprovadaInvalidaToBeSave(), OfertaPreAprovadaCreator.createOfertaPreAprovadaToBeSave());
        BDDMockito.when(this.ofertaPreAprovadaRepositoryMock.findAll())
                .thenReturn(listOfertaPreAprovada);

    }


    @Test
    public void listar_todasAs_Ofertas_retornandoSucesso() throws SQLException {

        List<OfertaPreAprovada> list = this.ofertaPreAprovadaService.getAll();

        Assertions.assertThat(list).isNotNull();

        Assertions.assertThat(list)
                .hasSize(2)
                .isNotEmpty();

    }


    @Test
    public void listar_listaFiltrada_retornaSucesso(){

        List<OfertaPreAprovada> list = this.ofertaPreAprovadaService.getOfertasVelidas();

        Assertions.assertThat(list).isNotNull();

        Assertions.assertThat(list)
                .hasSize(1)
                .isNotEmpty();
    }

    @Test
    public void listar_ofertasPreAprovadas_retornandoErroNoBancoDeDados() throws SQLException {

         List<OfertaPreAprovada> lista = null;


        BDDMockito.when(this.ofertaPreAprovadaRepositoryMock.findAll())
                .thenReturn(lista);

        Assertions.assertThatExceptionOfType(SQLException.class)
                .isThrownBy(() -> ofertaPreAprovadaService.getAll());
    }

}