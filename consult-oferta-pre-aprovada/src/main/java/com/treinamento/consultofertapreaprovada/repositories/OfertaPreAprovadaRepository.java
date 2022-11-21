package com.treinamento.consultofertapreaprovada.repositories;

import com.treinamento.consultofertapreaprovada.dtos.ClienteDto;
import com.treinamento.consultofertapreaprovada.model.Cliente;
import com.treinamento.consultofertapreaprovada.model.OfertaPreAprovada;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OfertaPreAprovadaRepository extends JpaRepository<OfertaPreAprovada, Long> {
    List<OfertaPreAprovada> findByCliente(Cliente cliente);
}
