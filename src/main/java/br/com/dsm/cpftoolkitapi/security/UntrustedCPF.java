package br.com.dsm.cpftoolkitapi.security;

/*
 * Esta classe representa o CPF, a princípio inconsistente, que
 * será repassado para cada objeto validator.
 */
final class UntrustedCPF {

    private String cpf;

    UntrustedCPF(String cpf) {
        this.cpf = cpf;
    }

    public String getCPF() {
        return cpf;
    }
}
