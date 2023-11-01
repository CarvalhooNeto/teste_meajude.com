package br.dcx.ufpb.meajude.repositorios;

import br.dcx.ufpb.meajude.modelos.UsuarioModelo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepositorio extends JpaRepository<UsuarioModelo, Long> {
    boolean existsByEmail(String email);
    Optional<UsuarioModelo> findByEmail(String email);
}