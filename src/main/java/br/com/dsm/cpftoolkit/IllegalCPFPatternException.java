package br.com.dsm.cpftoolkit;

/**
 * Esta exceção indica que um CPF com o padrão de dígitos inválido está
 * tentando ser passado como argumento.
 *
 * @author Douglas Melo
 * */
final public class IllegalCPFPatternException extends IllegalArgumentException {

    private static final long serialVersionUID = 4411146091886139226L;

    /**
     * Constrói um IllegalCPFPatternException com a mensagem descrevendo
     * a exceção.
     *
     * @param message a mensagem descrevendo a exceção.
     * */
    IllegalCPFPatternException(String message) {
        super(message);
    }
}