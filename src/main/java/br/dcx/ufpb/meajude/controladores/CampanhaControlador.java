package br.dcx.ufpb.meajude.controladores;

import br.dcx.ufpb.meajude.dtos.CampanhaDto;
import br.dcx.ufpb.meajude.modelos.CampanhaModelo;
import br.dcx.ufpb.meajude.servicos.CampanhaServico;
import io.swagger.annotations.ApiOperation;
import jakarta.validation.Valid;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

public class CampanhaControlador {
    private final CampanhaServico campanhaServico;

    @Autowired
    public CampanhaControlador(CampanhaServico campanhaServico) {
        this.campanhaServico = campanhaServico;
    }

    @ApiOperation(value = "Cadastra uma nova campanha na lista. Campanhas não podem ser repetidas. Apenas usuários autenticados podem cadastrar novas campanhas.")
    @RequestMapping(value = "/v1/api/campanhas", method = RequestMethod.POST, consumes="application/json", produces="application/json")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Retorna a campanha adicionada, incluindo seu código"),
            @ApiResponse(responseCode = "401", description = "Usuário não tem permissão para acessar este recurso"),
            @ApiResponse(responseCode = "400", description = "Campanha já existe na lista, atualize os dados da campanha que já existe")
    })
    public ResponseEntity<CampanhaModelo> criarCampanha(@RequestBody @Valid CampanhaDto campanhaDto) {
        CampanhaModelo campanhaCriada = campanhaServico.criarCampanha(campanhaDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(campanhaCriada);
    }

    @PutMapping("/{campanhaId}")
    public ResponseEntity<CampanhaModelo> atualizarCampanha(
            @PathVariable Long campanhaId,
            @RequestBody @Valid CampanhaDto campanhaDto) {
        CampanhaModelo campanhaAtualizada = campanhaServico.atualizarCampanha(campanhaId, campanhaDto);
        return ResponseEntity.ok(campanhaAtualizada);
    }

    @PatchMapping("/{campanhaId}/encerrar")
    public ResponseEntity<Void> encerrarCampanha(@PathVariable Long campanhaId) {
        campanhaServico.encerrarCampanha(campanhaId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{campanhaId}")
    public ResponseEntity<CampanhaModelo> obterCampanha(@PathVariable Long campanhaId) {
        CampanhaModelo campanha = campanhaServico.obterCampanha(campanhaId);
        return ResponseEntity.ok(campanha);
    }

    @GetMapping("/ativas")
    public ResponseEntity<List<CampanhaModelo>> listarCampanhasAtivas() {
        List<CampanhaModelo> campanhasAtivas = campanhaServico.listarCampanhasAtivas();
        return ResponseEntity.ok(campanhasAtivas);
    }
}
