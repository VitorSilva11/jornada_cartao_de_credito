package com.treinamento.consultofertapreaprovada.service;

import com.treinamento.consultofertapreaprovada.exceptions.ClientNotFound;
import com.treinamento.consultofertapreaprovada.model.OfertaPreAprovada;
import com.treinamento.consultofertapreaprovada.repositories.OfertaPreAprovadaRepository;
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
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

@ExtendWith(SpringExtension.class)
class OfertaPreAprovadaServiceTest {

    @InjectMocks
    private OfertaPreAprovadaService ofertaPreAprovadaService;

    @Mock
    private OfertaPreAprovadaRepository ofertaPreAprovadaRepositoryMock;
    @Mock
    private ClienteService clienteServiceMock;


    @BeforeEach()
    public void setUp() {
        List<OfertaPreAprovada> listOfertaPreAprovada = Arrays.asList(OfertaPreAprovadaCreator.createOfertaPreAprovadaInvalidaToBeSave(), OfertaPreAprovadaCreator.createOfertaPreAprovadaToBeSave());
        BDDMockito.when(this.ofertaPreAprovadaRepositoryMock.findAll())
                .thenReturn(listOfertaPreAprovada);

        BDDMockito.when(this.clienteServiceMock.findByClient(ArgumentMatchers.any()))
                .thenReturn(true);


        BDDMockito.when(this.ofertaPreAprovadaRepositoryMock.findByCliente(ArgumentMatchers.any()))
                .thenReturn(Arrays.asList(OfertaPreAprovadaCreator.createOfertaPreAprovadaToBeSave(), OfertaPreAprovadaCreator.createOfertaPreAprovadaInvalidaToBeSave()));

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
    public void listar_listaFiltrada_retornaSucesso() {

        List<OfertaPreAprovada> list = this.ofertaPreAprovadaService.getOfertasValidas(ClienteCreator.createClienteDTOToBeSave());

        Assertions.assertThat(list).isNotNull();

        Assertions.assertThat(list)
                .hasSize(1)
                .isNotEmpty();
    }


    @Test
    public void listar_ofertasPreAprovadas_retornandoErroDeClienteNaoEncontrado() {

        BDDMockito.when(this.clienteServiceMock.findByClient(ArgumentMatchers.any()))
                .thenThrow(new ClientNotFound("Cliente não Encontrado"));

        Assertions.assertThatExceptionOfType(ClientNotFound.class)
                .isThrownBy(() -> this.ofertaPreAprovadaService.getOfertasValidas(ClienteCreator.createClienteDTOToBeSave()));
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