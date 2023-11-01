package br.dcx.ufpb.meajude.repositorios;

import br.dcx.ufpb.meajude.modelos.DoacaoModelo;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoacaoRepositorio extends JpaRepository<DoacaoModelo, Long> {
    List<DoacaoModelo> findByCampanhaId(Long campanhaId);
}
