package br.dcx.ufpb.meajude.controladores;

import br.dcx.ufpb.meajude.dtos.DoacaoDto;
import br.dcx.ufpb.meajude.modelos.DoacaoModelo;
import br.dcx.ufpb.meajude.servicos.DoacaoServico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doacoes")
public class DoacaoControlador {

    private final DoacaoServico doacaoServico;

    @Autowired
    public DoacaoControlador(DoacaoServico doacaoServico) {
        this.doacaoServico = doacaoServico;
    }

    @PostMapping("/realizar")
    public ResponseEntity<DoacaoModelo> realizarDoacao(
            @RequestParam Long campanhaId,
            @RequestBody DoacaoDto doacaoDto) {
        DoacaoModelo doacaoRealizada = doacaoServico.realizarDoacao(campanhaId, doacaoDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(doacaoRealizada);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<DoacaoModelo>> listarDoacoes(@RequestParam Long campanhaId) {
        List<DoacaoModelo> doacoes = doacaoServico.listarDoacoesRealizadas(campanhaId);
        return ResponseEntity.ok(doacoes);
    }
}
