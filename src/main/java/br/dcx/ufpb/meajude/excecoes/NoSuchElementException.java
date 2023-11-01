package br.dcx.ufpb.meajude.excecoes;

public class NoSuchElementException extends RuntimeException {
    public NoSuchElementException() {
        super();
    }

    public NoSuchElementException(String message) {
        super(message);
    }
}