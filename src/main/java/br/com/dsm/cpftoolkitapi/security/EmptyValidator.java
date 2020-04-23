package br.com.dsm.cpftoolkitapi.security;

/*
 * Classe para validar os dígitos empty do CPF inconsistente.
 *
 * Possui os métodos para validar os dígitos empty a fim
 * de obter um CPF consistente.
 */
final class EmptyValidator implements EvaluableCPF {

    private UntrustedCPF cpf;

    EmptyValidator(UntrustedCPF cpf) {
        this.cpf = cpf;
    }

    private String getCPF() {
        return cpf.getCPF().replaceAll("[\\s]", "");
    }

    @Override
    public int validateDigits() {
        return !getCPF().isEmpty() ? 0 : 1;
    }

    @Override
    public Reason getReason() {
        return Reason.EMPTY_CPF;
    }
}
