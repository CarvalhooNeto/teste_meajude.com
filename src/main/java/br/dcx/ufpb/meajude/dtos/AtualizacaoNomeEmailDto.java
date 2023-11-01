package br.dcx.ufpb.meajude.dtos;

public class AtualizacaoNomeEmailDto {
    private String novoNome;
    private String novoEmail;

    public String getNovoNome() {
        return novoNome;
    }

    public void setNovoNome(String novoNome) {
        this.novoNome = novoNome;
    }

    public String getNovoEmail() {
        return novoEmail;
    }

    public void setNovoEmail(String novoEmail) {
        this.novoEmail = novoEmail;
    }
}
