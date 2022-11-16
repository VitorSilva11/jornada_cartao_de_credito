package com.treinamento.consultofertapreaprovada.controller;


import com.treinamento.consultofertapreaprovada.model.OfertaPreAprovada;
import com.treinamento.consultofertapreaprovada.service.OfertaPreAprovadaService;
import com.treinamento.consultofertapreaprovada.utils.DateUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("ofertas")
public class OfertaPreAprovadaController {

    private final OfertaPreAprovadaService ofertaPreAprovadaService;

    public OfertaPreAprovadaController(OfertaPreAprovadaService ofertaPreAprovadaService) {
        this.ofertaPreAprovadaService = ofertaPreAprovadaService;
    }

    @GetMapping
    public ResponseEntity<List<OfertaPreAprovada>> listAll(){

        List<OfertaPreAprovada> listaOfertasPreAprovadas = null;
        try{
            listaOfertasPreAprovadas = this.ofertaPreAprovadaService.getAll();
        } catch (SQLException e) {
            System.out.println(e.getStackTrace());;
        }

        return ResponseEntity.ok(listaOfertasPreAprovadas);
    }

    @GetMapping("/preAprovadas")
    public ResponseEntity<List<OfertaPreAprovada>> listOfertasPreAprovadas(){
        return ResponseEntity.ok(this.ofertaPreAprovadaService.getOfertasVelidas());
    }
}
