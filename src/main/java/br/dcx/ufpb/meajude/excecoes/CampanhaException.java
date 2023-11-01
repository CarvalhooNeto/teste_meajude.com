package br.dcx.ufpb.meajude.excecoes;

public class CampanhaException extends RuntimeException {
    public CampanhaException(String message) {
        super(message);
    }

    public static class CampanhaNotFoundException extends CampanhaException {
        public CampanhaNotFoundException(Long campanhaId) {
            super("Campanha não encontrada com o ID: " + campanhaId);
        }
    }

    public static class CampanhaEncerradaException extends CampanhaException {
        public CampanhaEncerradaException() {
            super("A campanha já foi encerrada e não pode ser modificada.");
        }
    }

    public static class ValorInvalidoException extends CampanhaException {
        public ValorInvalidoException(String message) {
            super(message);
        }
    }
}
