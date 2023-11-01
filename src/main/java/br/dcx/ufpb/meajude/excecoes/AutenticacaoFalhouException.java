package br.dcx.ufpb.meajude.excecoes;

public class AutenticacaoFalhouException extends RuntimeException {

    public AutenticacaoFalhouException(String message) {
        super(message);
    }

    public AutenticacaoFalhouException(String message, Throwable cause) {
        super(message, cause);
    }
}
