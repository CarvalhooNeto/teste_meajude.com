package br.dcx.ufpb.meajude.servicos;

import br.dcx.ufpb.meajude.modelos.CampanhaModelo;
import br.dcx.ufpb.meajude.modelos.DoacaoModelo;
import br.dcx.ufpb.meajude.modelos.EstadoCampanha;
import br.dcx.ufpb.meajude.dtos.DoacaoDto;
import br.dcx.ufpb.meajude.excecoes.CampanhaException.CampanhaEncerradaException;
import br.dcx.ufpb.meajude.excecoes.CampanhaException.CampanhaNotFoundException;
import br.dcx.ufpb.meajude.repositorios.CampanhaRepositorio;
import br.dcx.ufpb.meajude.repositorios.DoacaoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class DoacaoServicoImpl implements DoacaoServico {
    private final CampanhaRepositorio campanhaRepositorio;
    private final DoacaoRepositorio doacaoRepositorio;

    @Autowired
    public DoacaoServicoImpl(CampanhaRepositorio campanhaRepositorio, DoacaoRepositorio doacaoRepositorio) {
        this.campanhaRepositorio = campanhaRepositorio;
        this.doacaoRepositorio = doacaoRepositorio;
    }

    @Override
    public DoacaoModelo realizarDoacao(Long campanhaId, DoacaoDto doacaoDto) {

        CampanhaModelo campanha = campanhaRepositorio.findById(campanhaId)
                .orElseThrow(() -> new CampanhaNotFoundException(campanhaId));
        if (campanha.getEstado() != EstadoCampanha.ATIVA) {
            throw new CampanhaEncerradaException();
        }


        DoacaoModelo doacao = new DoacaoModelo();
        doacao.setValor(doacaoDto.getValor());
        doacao.setData(new Date());
        doacao.setCampanha(campanha);


        return doacaoRepositorio.save(doacao);
    }

    @Override
    public List<DoacaoModelo> listarDoacoesRealizadas(Long campanhaId) {

        campanhaRepositorio.findById(campanhaId)
                .orElseThrow(() -> new CampanhaNotFoundException(campanhaId));
        return doacaoRepositorio.findByCampanhaId(campanhaId);
    }
}
