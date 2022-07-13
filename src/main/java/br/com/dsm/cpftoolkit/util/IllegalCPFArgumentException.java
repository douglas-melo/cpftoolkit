package br.com.dsm.cpftoolkit.util;

/*
 * Esta exceção será lançada nos casos em que um CPF inconsistente for
 * passado como argumento.
 *
 * e.g. um CPF nulo, ou vazio, que dentro do contexto da aplicação são inválidos.
 */
class IllegalCPFArgumentException extends IllegalArgumentException {

    private static final long serialVersionUID = 4479187854237180518L;

    IllegalCPFArgumentException(String message) {
        super(message);
    }
}
