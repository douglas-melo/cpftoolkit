package br.com.dsm.cpftoolkit.security;

import java.util.regex.Pattern;

/*
 * Classe para validar o padrão dos dígitos do CPF inconsistente.
 *
 * O REGEX captura somente dígitos, 11 dígitos, com o range entre [0-9],
 * os dígitos definidos no range não podem se repetir todas às 11 vezes.
 */
final class PatternValidator implements EvaluableCPF {

    private final UntrustedCPF cpf;

    PatternValidator(UntrustedCPF cpf) {
        this.cpf = cpf;
    }

    private String getCPF() {
        return cpf.getCPF().replaceAll("[\\s.\\-]", "");
    }

    @Override
    public int validateDigits() {
        final String CPF_REGEX_PATTERN = "^(\\d)(?!\\1+$)\\d{10}$";
        return Pattern.matches(CPF_REGEX_PATTERN, getCPF()) ? 0 : 1;
    }

    @Override
    public Reason getReason() {
        return Reason.INVALID_PATTERN;
    }
}
