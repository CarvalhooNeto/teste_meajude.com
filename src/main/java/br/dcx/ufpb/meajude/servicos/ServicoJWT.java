package br.dcx.ufpb.meajude.servicos;
import java.security.Key;
import java.util.Date;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.dcx.ufpb.meajude.dtos.LoginDeUsuarioDTO;
import br.dcx.ufpb.meajude.dtos.RespostaDeLogin;
import br.dcx.ufpb.meajude.excecoes.LoginInvalidoException;
import br.dcx.ufpb.meajude.filtros.FiltroDeTokensJWT;

@Service
public class ServicoJWT {
    @Autowired
    private UsuarioServico usuarioServico;
    public static final Key TOKEN_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS512);

    public RespostaDeLogin autentica(LoginDeUsuarioDTO usuario) {
        if (!usuarioServico.validaUsuarioSenha(usuario)) {
            throw new LoginInvalidoException("Login falhou",
                    "O usuário não foi autenticado. A requisição de login foi processada com sucesso, mas as informações passadas não foram corretas para autenticar o usuário com sucesso.");
        }

        String token = geraToken(usuario.getEmail());
        return new RespostaDeLogin(token);
    }



    private String geraToken(String email) {
        return Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setSubject(email)
                .signWith(TOKEN_KEY, SignatureAlgorithm.HS512)
                .setExpiration(new Date(System.currentTimeMillis() + 3 * 60 * 1000))
                .compact();// 3 min
    }

    public String getSujeitoDoToken(String authorizationHeader) {
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            throw new SecurityException("Token inexistente ou mal formatado!");
        }

        // Extraindo apenas o token do cabecalho.
        String token = authorizationHeader.substring(FiltroDeTokensJWT.TOKEN_INDEX);

        String subject = null;
        try {
            JwtParser parser = Jwts.parserBuilder().setSigningKey(TOKEN_KEY).build();
            subject = parser.parseClaimsJws(token).getBody().getSubject();
        } catch (SignatureException e) {
            throw new SecurityException("Token invalido ou expirado!");
        }
        return subject;
    }
}
