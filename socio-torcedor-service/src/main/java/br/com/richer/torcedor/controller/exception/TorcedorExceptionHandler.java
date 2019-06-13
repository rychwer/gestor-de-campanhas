package br.com.richer.torcedor.controller.exception;

import br.com.richer.torcedor.domain.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collections;
import java.util.List;

@ControllerAdvice
public class TorcedorExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    protected ResponseEntity<ErrorResponse> handleGeneralException(Exception ex) {
        return new ResponseEntity<>(new ErrorResponse(ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(TorcedorCadastradoException.class)
    @ResponseBody
    protected ResponseEntity<ErrorResponse> handleTorcedorCadastradoException(TorcedorCadastradoException ex) {
        return new ResponseEntity<>(new ErrorResponse(ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseBody
    public ResponseEntity<ErrorResponse> handleMissingParams(MissingServletRequestParameterException ex) {
        String name = ex.getParameterName();
        return new ResponseEntity<>(new ErrorResponse("O parâmetro: " + name + " não foi informado."), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ServicoIndisponivelException.class)
    @ResponseBody
    public ResponseEntity<ErrorResponse> handleServicoIndisponivelException(ServicoIndisponivelException ex) {
        List<String> errors = Collections.singletonList(ex.getMessage());
        return new ResponseEntity<>(new ErrorResponse(ex.getMessage(), ex.getObjetoRetorno()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
