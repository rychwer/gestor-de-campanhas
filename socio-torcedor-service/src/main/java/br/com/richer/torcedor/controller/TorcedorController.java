package br.com.richer.torcedor.controller;

import br.com.richer.torcedor.domain.Campanha;
import br.com.richer.torcedor.domain.Torcedor;
import br.com.richer.torcedor.service.TorcedorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/torcedor")
@Api(value="Api Torcedor")
public class TorcedorController {

    @Autowired
    private TorcedorService torcedorService;


    @ApiOperation(value = "Responsável por criar e devolver a lista de campanha de determinado torcedor", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Cadastra o torcedor no sistema e devolve a lista de campanhas associadas"),
            @ApiResponse(code = 400, message = "Caso o torcedor não seja válido"),
            @ApiResponse(code = 500, message = "Erro desconhecido")
    })
    @PostMapping
    public ResponseEntity<List<Campanha>> criarTorcedor(@RequestBody @Valid Torcedor torcedor) {
        final Torcedor torcedorCriado = torcedorService.criarTorcedor(torcedor);
        return new ResponseEntity<>(torcedorCriado.getCampanhas(), HttpStatus.OK);
    }
}
