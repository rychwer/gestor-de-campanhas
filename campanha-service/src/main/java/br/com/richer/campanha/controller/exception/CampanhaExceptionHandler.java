package br.com.richer.campanha.controller.exception;

import br.com.richer.campanha.domain.ErrorResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collections;
import java.util.List;

@ControllerAdvice
public class CampanhaExceptionHandler {

    @ExceptionHandler(CampanhaNotFoundException.class)
    @ResponseBody
    public ResponseEntity<ErrorResponse> handleCampanhaNotFoundException(CampanhaNotFoundException ex) {
        List<String> errors = Collections.singletonList(ex.getMessage());
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        return new ResponseEntity<>(new ErrorResponse(errors), headers, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CampanhaDataInformadaInvalidaException.class)
    @ResponseBody
    public ResponseEntity<ErrorResponse> handleDataInformadaInvalidaException(CampanhaDataInformadaInvalidaException ex) {
        List<String> errors = Collections.singletonList(ex.getMessage());
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        return new ResponseEntity<>(new ErrorResponse(errors), headers, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity<ErrorResponse> handleInternalErrorException(Exception ex) {
        List<String> errors = Collections.singletonList(ex.getMessage());
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        return new ResponseEntity<>(new ErrorResponse(errors), headers, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ResponseBody
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ErrorResponse> handleMissingParams(MissingServletRequestParameterException ex) {
        String name = ex.getParameterName();
        List<String> errors = Collections.singletonList("O parâmetro: " + name + " não foi informado.");
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        return new ResponseEntity<>(new ErrorResponse(errors), headers, HttpStatus.BAD_REQUEST);
    }

}
