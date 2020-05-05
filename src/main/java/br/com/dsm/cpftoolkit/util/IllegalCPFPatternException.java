package br.com.dsm.cpftoolkit.util;

/*
 * Esta exceção será lançada nos casos em que um CPF com o padrão dos dígitos
 * inconsistente for passado, e.g. um CPF com todos os 11 dígitos repetidos,
 * ou com caracteres não permitidos.
 */
final class IllegalCPFPatternException extends IllegalCPFArgumentException {

    private static final long serialVersionUID = 4280132351419811186L;

    IllegalCPFPatternException(String message) {
        super(message);
    }
}
