package br.com.dsm.cpftoolkit.security;

/*
 * Classe para validar os dígitos empty do CPF inconsistente.
 *
 * Possui os métodos para validar os dígitos empty a fim
 * de obter um CPF consistente.
 */
final class EmptyValidator implements EvaluableCPF {

    private final UntrustedCPF cpf;

    EmptyValidator(UntrustedCPF cpf) {
        this.cpf = cpf;
    }

    @Override
    public int validateDigits() {
        return !cpf.getCPF().trim().isEmpty() ? 0 : 1;
    }

    @Override
    public Reason getReason() {
        return Reason.EMPTY_CPF;
    }
}
