package com.treinamento.consultofertapreaprovada.handler;

import com.treinamento.consultofertapreaprovada.exceptions.ClientNotFound;
import com.treinamento.consultofertapreaprovada.exceptions.ExceptionDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.SQLException;
import java.time.LocalDateTime;

@ControllerAdvice
public class ClientNotFoundExceptionHandler {

    @ExceptionHandler(ClientNotFound.class)
    public ResponseEntity<ExceptionDetails> handleClientNotFound(ClientNotFound clientNotFound){
        return new ResponseEntity<>(
                ExceptionDetails.builder()
                        .timestamp(LocalDateTime.now())
                        .status(HttpStatus.NOT_FOUND.value())
                        .title("Cliente não encontrado")
                        .details(clientNotFound.getMessage())
                        .developerMessage(clientNotFound.getClass().getName())
                        .build(), HttpStatus.NOT_FOUND);
    }


}
