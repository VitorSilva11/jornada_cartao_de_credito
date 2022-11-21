package com.treinamento.consultofertapreaprovada.service;

import com.treinamento.consultofertapreaprovada.exceptions.ClientNotFound;
import com.treinamento.consultofertapreaprovada.model.Cliente;
import com.treinamento.consultofertapreaprovada.repositories.ClienteRepository;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {


    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public boolean findByClient(Cliente cliente){
        boolean exists = clienteRepository.exists(Example.of(cliente));

        if(!exists) throw  new ClientNotFound("Cliente não encontrado");

        return exists;

    }

}
