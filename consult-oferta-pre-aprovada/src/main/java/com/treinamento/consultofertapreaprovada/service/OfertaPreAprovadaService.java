package com.treinamento.consultofertapreaprovada.service;

import com.treinamento.consultofertapreaprovada.model.OfertaPreAprovada;
import com.treinamento.consultofertapreaprovada.repositories.OfertaPreAprovadaRepository;
import com.treinamento.consultofertapreaprovada.utils.DateUtils;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OfertaPreAprovadaService {

    private final OfertaPreAprovadaRepository repository;

    public OfertaPreAprovadaService(OfertaPreAprovadaRepository repository) {
        this.repository = repository;
    }

    public List<OfertaPreAprovada> getAll() throws SQLException {

        if(repository.findAll() == null){
            throw new SQLException();
        }

        return repository.findAll();
    }

    public List<OfertaPreAprovada> getOfertasVelidas() {

        try {
            List<OfertaPreAprovada> listaOfertaPreAprovada = getAll();

            List<OfertaPreAprovada> listaFiltrada = listaOfertaPreAprovada
                    .stream()
                    .filter(preAprovado -> ofertaValida(preAprovado))
                    .collect(Collectors.toList());

            return listaFiltrada;
        } catch (SQLException exception) {
            System.out.println(exception.getStackTrace());
            return null;
        }
    }

    public boolean ofertaValida(OfertaPreAprovada ofertaPreAprovada){
        return DateUtils.dataValida(ofertaPreAprovada.getData_validade()) <= 0 && ofertaPreAprovada.getIscontrado() == false;
    }


}
