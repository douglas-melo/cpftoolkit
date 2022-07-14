package br.com.dsm.cpftoolkit.security;

/*
 * Classe para validar o dígito verificador do CPF inconsistente.
 *
 * Possui os métodos para validar o dígito verificador a fim
 * de obter um CPF consistente.
 */
final class CheckDigitHandler implements EvaluableCPF {

    private final UntrustedCPF cpf;

    CheckDigitHandler(UntrustedCPF cpf) {
        this.cpf = cpf;
    }

    @Override
    public int validateDigits() {
        return new CheckDigitChecker(cpf.getCPFWithoutDelimiter()).validateCheckDigit();
    }

    @Override
    public Reason getReason() {
        return Reason.INVALID_CPF;
    }
}
