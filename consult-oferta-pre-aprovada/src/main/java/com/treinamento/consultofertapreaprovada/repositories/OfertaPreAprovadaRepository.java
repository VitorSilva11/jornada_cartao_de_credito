package com.treinamento.consultofertapreaprovada.repositories;

import com.treinamento.consultofertapreaprovada.model.OfertaPreAprovada;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OfertaPreAprovadaRepository extends JpaRepository<OfertaPreAprovada, Long> {
}
