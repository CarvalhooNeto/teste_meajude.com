package br.dcx.ufpb.meajude.servicos;

import br.dcx.ufpb.meajude.modelos.DoacaoModelo;
import br.dcx.ufpb.meajude.dtos.DoacaoDto;
import java.util.List;

public interface DoacaoServico {
    DoacaoModelo realizarDoacao(Long campanhaId, DoacaoDto doacaoDto);
    List<DoacaoModelo> listarDoacoesRealizadas(Long campanhaId);
}
