package br.com.dsm.cpftoolkit.security;

/*
 * Esta classe representa o CPF, a princípio inconsistente, que
 * será repassado para cada objeto validator.
 */
final class UntrustedCPF {

    private final String cpf;

    UntrustedCPF(String cpf) {
        this.cpf = cpf;
    }

    public String getCPFWithoutDelimiter() {
        return cpf.replaceAll("[\\s.\\-]", "");
    }

    public String getCPF() {
        return cpf;
    }
}
