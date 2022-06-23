package br.com.dsm.cpftoolkit.security;

final class  CheckDigitHandler implements EvaluableCPF {

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
