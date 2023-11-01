package br.dcx.ufpb.meajude.servicos;

import br.dcx.ufpb.meajude.modelos.CampanhaModelo;
import br.dcx.ufpb.meajude.modelos.EstadoCampanha;
import br.dcx.ufpb.meajude.dtos.CampanhaDto;
import br.dcx.ufpb.meajude.excecoes.CampanhaException.CampanhaEncerradaException;
import br.dcx.ufpb.meajude.excecoes.CampanhaException.CampanhaNotFoundException;
import br.dcx.ufpb.meajude.excecoes.CampanhaException.ValorInvalidoException;
import br.dcx.ufpb.meajude.repositorios.CampanhaRepositorio;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CampanhaServicoImpl implements CampanhaServico {
    private final CampanhaRepositorio campanhaRepositorio;


    public CampanhaServicoImpl(CampanhaRepositorio campanhaRepositorio) {
        this.campanhaRepositorio = campanhaRepositorio;
    }

    @Override
    public CampanhaModelo criarCampanha(CampanhaDto campanhaDto) {
        Date dataAtual = new Date();
        if (campanhaDto.getDataTermino().before(dataAtual)) {
            throw new ValorInvalidoException("A data de término da campanha deve estar no futuro.");
        }
        CampanhaModelo novaCampanha = new CampanhaModelo();
        novaCampanha.setEstado(EstadoCampanha.ATIVA);
        novaCampanha.setTituloCurto(campanhaDto.getTituloCurto());
        novaCampanha.setDescricao(campanhaDto.getDescricao());
        novaCampanha.setMeta(campanhaDto.getMeta());
        novaCampanha.setDataTermino(campanhaDto.getDataTermino());
        novaCampanha.setValorArrecadado(0.0);
        return campanhaRepositorio.save(novaCampanha);
    }

    @Override
    public CampanhaModelo atualizarCampanha(Long campanhaId, CampanhaDto campanhaDto) {
  
        CampanhaModelo campanhaExistente = campanhaRepositorio.findById(campanhaId)
            .orElseThrow(() -> new CampanhaNotFoundException(campanhaId));

        
        if (campanhaExistente.getEstado() == EstadoCampanha.ENCERRADA) {// Verifica se a campanha já foi encerrada ou não
            throw new CampanhaEncerradaException();
        }

    
        Date dataAtual = new Date();    // Validar a data do término
        if (campanhaDto.getDataTermino().before(dataAtual)) {
            throw new ValorInvalidoException("A data de término da campanha deve estar no futuro.");
        }
        campanhaExistente.setTituloCurto(campanhaDto.getTituloCurto());
        campanhaExistente.setDescricao(campanhaDto.getDescricao());
        campanhaExistente.setMeta(campanhaDto.getMeta());
        campanhaExistente.setDataTermino(campanhaDto.getDataTermino());
        return campanhaRepositorio.save(campanhaExistente);
    }

    @Override
    public void encerrarCampanha(Long campanhaId) {
        CampanhaModelo campanhaExistente = campanhaRepositorio.findById(campanhaId)
            .orElseThrow(() -> new CampanhaNotFoundException(campanhaId));

        if (campanhaExistente.getEstado() == EstadoCampanha.ENCERRADA) {
            throw new CampanhaEncerradaException();
        }
        campanhaExistente.setEstado(EstadoCampanha.ENCERRADA);

        campanhaRepositorio.save(campanhaExistente);
    }

    @Override
    public CampanhaModelo obterCampanha(Long campanhaId) {
        return campanhaRepositorio.findById(campanhaId)
            .orElseThrow(() -> new CampanhaNotFoundException(campanhaId));
    }

    @Override
    public List<CampanhaModelo> listarCampanhasAtivas() {
        Date dataAtual = new Date();
        return campanhaRepositorio.findByEstadoAndDataTerminoAfter(EstadoCampanha.ATIVA, dataAtual);
    }
}
