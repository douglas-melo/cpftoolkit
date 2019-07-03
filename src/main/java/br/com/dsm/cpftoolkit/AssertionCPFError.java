package br.com.dsm.cpftoolkit;

/**
 * Este erro indica que um método de tratamento que serve unicamente
 * para validar CPFs inválidos está sendo chamado para tentar tratar um CPF válido.
 *
 * @author Douglas Melo
 * */
final public class AssertionCPFError extends AssertionError {

    /**
     * Constrói um AssertionCPFError com a mensagem descrevendo o
     * erro.
     *
     * @param detailMessage a mensagem descrevendo o erro.
     * */
    AssertionCPFError(String detailMessage) {
        super(detailMessage);
    }
}