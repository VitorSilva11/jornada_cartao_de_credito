package com.treinamento.consultofertapreaprovada.service;

import com.treinamento.consultofertapreaprovada.dtos.ClienteDto;
import com.treinamento.consultofertapreaprovada.exceptions.ClientNotFound;
import com.treinamento.consultofertapreaprovada.exceptions.OfertaNotFound;
import com.treinamento.consultofertapreaprovada.model.Cliente;
import com.treinamento.consultofertapreaprovada.model.OfertaPreAprovada;
import com.treinamento.consultofertapreaprovada.repositories.OfertaPreAprovadaRepository;
import com.treinamento.consultofertapreaprovada.utils.DateUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OfertaPreAprovadaService {

    private final OfertaPreAprovadaRepository ofertaPreAprovadaRepository;
    private final ClienteService clienteService;

    public OfertaPreAprovadaService(OfertaPreAprovadaRepository ofertaPreAprovadaRepository, ClienteService clienteService) {
        this.ofertaPreAprovadaRepository = ofertaPreAprovadaRepository;
        this.clienteService = clienteService;
    }

    public List<OfertaPreAprovada> getAll() throws SQLException {

        if (ofertaPreAprovadaRepository.findAll() == null) {
            throw new SQLException();
        }

        return ofertaPreAprovadaRepository.findAll();
    }

    public List<OfertaPreAprovada> getOfertasValidas(ClienteDto clienteDto) {

        Cliente cliente = new Cliente();
        BeanUtils.copyProperties(clienteDto,cliente);

        clienteService.findByClient(cliente);

        List<OfertaPreAprovada> listaOfertaPreAprovada = ofertaPreAprovadaRepository.findByCliente(cliente);

        if(listaVazia(listaOfertaPreAprovada)){
            throw new OfertaNotFound("Oferta Não Encontrada");
        }

        List<OfertaPreAprovada> listaFiltrada = listaOfertaPreAprovada
                .stream()
                .filter(preAprovado -> ofertaValida(preAprovado))
                .collect(Collectors.toList());

        return listaFiltrada;

    }

    public boolean ofertaValida(OfertaPreAprovada ofertaPreAprovada) {
        return DateUtils.dataValida(ofertaPreAprovada.getData_validade()) <= 0 && ofertaPreAprovada.getContratado() == false;
    }

    public boolean listaVazia(List<OfertaPreAprovada> listaOfertaPreAprovada){
        return listaOfertaPreAprovada.isEmpty();
    }


}
