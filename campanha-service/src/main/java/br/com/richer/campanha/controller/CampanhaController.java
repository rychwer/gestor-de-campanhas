package br.com.richer.campanha.controller;

import br.com.richer.campanha.domain.Campanha;
import br.com.richer.campanha.service.CampanhaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/campanha")
@Api(value="Api Torcedor")
public class CampanhaController {

    private CampanhaService campanhaService;

    @Autowired
    public CampanhaController(CampanhaService campanhaService) {
        this.campanhaService = campanhaService;
    }

    @ApiOperation(value = "Cria uma campanha", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Campanha cadastrada com sucesso"),
            @ApiResponse(code = 400, message = "Caso da data da vigência da campanha seja inválida"),
            @ApiResponse(code = 500, message = "Erro desconhecido")
    })
    @PutMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<Campanha> criarCampanha(@RequestBody Campanha campanha) {
        campanhaService.criarCampanha(campanha);
        return new ResponseEntity<Campanha>(HttpStatus.CREATED);
    }

    @ApiOperation(value = "Altera uma campanha", response = Campanha.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Campanha alterada com sucesso"),
            @ApiResponse(code = 400, message = "Caso a campanha não seja válida"),
            @ApiResponse(code = 500, message = "Erro desconhecido")
    })
    @PostMapping(path = "/{idCampanha}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<Campanha> alterarCampanha(@PathVariable Long idCampanha, @RequestBody @Valid Campanha campanha) {
        final Campanha campanhaAlterada = campanhaService.alterarCampanha(idCampanha, campanha);
        return new ResponseEntity<Campanha>(campanhaAlterada, HttpStatus.OK);
    }

    @ApiOperation(value = "Deleta uma campanha", response = ResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Campanha deletada com sucesso"),
            @ApiResponse(code = 500, message = "Erro desconhecido")
    })
    @DeleteMapping(path = "/{idCampanha}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity deletarCampanha(@PathVariable Long idCampanha) {
        campanhaService.deletarCampanha(idCampanha);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation(value = "Lista as campanhas cadastradas", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna as campanhas cadastradas"),
            @ApiResponse(code = 500, message = "Erro desconhecido")
    })
    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<List<Campanha>> listarCampanhas() {
        final List<Campanha> campanhas = campanhaService.listarCampanhasAtivas();
        return new ResponseEntity<List<Campanha>>(campanhas, HttpStatus.OK);
    }

    @ApiOperation(value = "Lista as campanhas pelo time do coração", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna as campanhas"),
            @ApiResponse(code = 500, message = "Erro desconhecido")
    })
    @GetMapping(value = "/time/{idTimeCoracao}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<List<Campanha>> listarCampanhasPorTimeCoracao(@PathVariable Long idTimeCoracao) {
        final List<Campanha> campanhas = campanhaService.listarCampanhasAtivasPorTimeCoracao(idTimeCoracao);
        return new ResponseEntity<List<Campanha>>(campanhas, HttpStatus.OK);
    }

    @ApiOperation(value = "Recupera uma campanha cadastrada", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna as campanhas"),
            @ApiResponse(code = 404, message = "Caso não encontre a campanha informada"),
            @ApiResponse(code = 500, message = "Erro desconhecido")
    })
    @GetMapping(path = "/{idCampanha}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<Campanha> recuperarCampanha(@PathVariable Long idCampanha) {
        final Campanha campanha = campanhaService.consultarCampanha(idCampanha);
        return new ResponseEntity<Campanha>(campanha, HttpStatus.OK);
    }

}
