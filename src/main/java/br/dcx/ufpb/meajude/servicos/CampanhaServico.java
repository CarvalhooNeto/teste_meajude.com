package br.dcx.ufpb.meajude.servicos;

import br.dcx.ufpb.meajude.modelos.CampanhaModelo;
import br.dcx.ufpb.meajude.dtos.CampanhaDto;

import java.util.List;

public interface CampanhaServico {
    CampanhaModelo criarCampanha(CampanhaDto campanhaDto);
    CampanhaModelo atualizarCampanha(Long campanhaId, CampanhaDto campanhaDto);
    void encerrarCampanha(Long campanhaId);
    CampanhaModelo obterCampanha(Long campanhaId);
    List<CampanhaModelo> listarCampanhasAtivas();
}
