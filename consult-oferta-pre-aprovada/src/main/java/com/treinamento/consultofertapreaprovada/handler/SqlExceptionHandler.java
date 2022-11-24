package com.treinamento.consultofertapreaprovada.handler;

import com.treinamento.consultofertapreaprovada.exceptions.ExceptionDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.SQLException;
import java.time.LocalDateTime;

@ControllerAdvice
public class SqlExceptionHandler {

    @ExceptionHandler(SQLException.class)
    public ResponseEntity<ExceptionDetails> handleBadRequestException(SQLException sqlException){
        return new ResponseEntity<>(
                ExceptionDetails.builder()
                        .timestamp(LocalDateTime.now())
                        .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                        .title("Erro no banco de dados")
                        .build(), HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
