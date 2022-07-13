package br.com.dsm.cpftoolkit.util;

/*
 * Esta classe representa o CPF, seu padrão de dígitos será verificado antes
 * de ser utilizado pela API.
 */
final class UntrustedCPF {

    private final String cpf;

    UntrustedCPF(String cpf) {
        this.cpf = cpf;
    }

    public String getCPF() {
        return cpf;
    }
}
