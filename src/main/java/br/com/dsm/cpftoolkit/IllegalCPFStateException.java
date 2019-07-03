package br.com.dsm.cpftoolkit;

/**
 * Esta exceção indica que um CPF inconsistente está tentando
 * ser acessado.
 *
 * @author Douglas Melo
 * */
final public class IllegalCPFStateException extends IllegalStateException {

    private static final long serialVersionUID = 2659011668421358833L;

    /**
     * Constrói um IllegalCPFStateException com a mensagem descrevendo
     * a exceção.
     *
     * @param message a mensagem descrevendo a exceção.
     * */
    IllegalCPFStateException(String message) {
        super(message);
    }
}