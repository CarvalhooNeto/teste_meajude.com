package br.dcx.ufpb.meajude.controladores;
import br.dcx.ufpb.meajude.excecoes.OperacaoNaoAutorizadaException;
import br.dcx.ufpb.meajude.excecoes.UsuarioNaoEncontradoException;
import br.dcx.ufpb.meajude.servicos.UsuarioServico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.dcx.ufpb.meajude.dtos.UsuarioDto;

@RestController
public class UsuarioControlador {
    @Autowired
    private  UsuarioServico usuarioServico;

    @PostMapping("v1/api/usuarios")
    public ResponseEntity<UsuarioDto> cadastrarUsuario(@RequestBody UsuarioDto usuarioDto) {
        UsuarioDto usuario = UsuarioDto.from(usuarioServico.cadastrarUsuario(usuarioDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(usuario);
    }

    @DeleteMapping("/{usuarioId}")
    public ResponseEntity<Void> deletarUsuario(@PathVariable Long usuarioId) {
        usuarioServico.deletarUsuario(usuarioId);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/auth/usuarios/{usuarioId}")
    public ResponseEntity<Void> atualizarNomeEEmail(
            @PathVariable Long usuarioId,
            @RequestParam String novoNome,
            @RequestParam String novoEmail,
            @RequestHeader("Authorization") String authHeader) {
        try {
            usuarioServico.atualizarNomeEEmail(usuarioId, novoNome, novoEmail, authHeader);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (UsuarioNaoEncontradoException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (OperacaoNaoAutorizadaException e) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }
}







