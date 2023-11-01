package br.dcx.ufpb.meajude.servicos;

import br.dcx.ufpb.meajude.dtos.LoginDeUsuarioDTO;
import br.dcx.ufpb.meajude.dtos.UsuarioDto;
import br.dcx.ufpb.meajude.excecoes.AutenticacaoFalhouException;
import br.dcx.ufpb.meajude.excecoes.OperacaoNaoAutorizadaException;
import br.dcx.ufpb.meajude.excecoes.UsuarioNaoEncontradoException;
import br.dcx.ufpb.meajude.repositorios.UsuarioRepositorio;
import br.dcx.ufpb.meajude.modelos.UsuarioModelo;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class UsuarioServico {
    @Autowired
    private  UsuarioRepositorio usuarioRepositorio;
    @Autowired
    private  ServicoJWT servicoJWT;

    public UsuarioModelo cadastrarUsuario(UsuarioDto usuarioDto) {
        UsuarioModelo usuarioModelo = new UsuarioModelo();
        BeanUtils.copyProperties(usuarioDto, usuarioModelo);
        return usuarioRepositorio.save(usuarioModelo);
    }

    public void deletarUsuario(Long usuarioId) {
        usuarioRepositorio.deleteById(usuarioId);
    }

    public void atualizarNomeEEmail(Long usuarioId, String novoNome, String novoEmail, String authHeader) {

        UsuarioModelo usuarioModelo = usuarioRepositorio.findById(usuarioId)
                .orElseThrow(() -> new UsuarioNaoEncontradoException("Usuário não encontrado", "O usuário com ID " + usuarioId + " não foi encontrado."));

        if (usuarioTemPermissao(authHeader, novoEmail)) {
            usuarioModelo.setNome(novoNome);
            usuarioModelo.setEmail(novoEmail);

            usuarioRepositorio.save(usuarioModelo);
        } else {
            throw new OperacaoNaoAutorizadaException("Usuário não tem permissão",
                    "A operação requerida não pode ser realizada por este usuário: " + servicoJWT.getSujeitoDoToken(authHeader) + ".");
        }
    }

    private boolean usuarioTemPermissao(String authorizationHeader, String email) {
        String subject = servicoJWT.getSujeitoDoToken(authorizationHeader);
        Optional<UsuarioModelo> optUsuario = usuarioRepositorio.findByEmail(subject);
        return optUsuario.isPresent() && optUsuario.get().getEmail().equals(email);
    }

    public boolean validaUsuarioSenha(LoginDeUsuarioDTO usuario) {
        Optional<UsuarioModelo> optUsuario = usuarioRepositorio.findByEmail(usuario.getEmail());
        if (optUsuario.isPresent() && optUsuario.get().getSenha().equals(usuario.getSenha()))
            return true;
        return false;
    }
}

