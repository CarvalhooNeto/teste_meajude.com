package br.dcx.ufpb.meajude.repositorios;

import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.dcx.ufpb.meajude.modelos.CampanhaModelo;
import br.dcx.ufpb.meajude.modelos.EstadoCampanha;

@Repository
public interface CampanhaRepositorio extends JpaRepository<CampanhaModelo, Long> {
    List<CampanhaModelo> findByEstadoAndDataTerminoAfter(EstadoCampanha estado, Date data);
}
