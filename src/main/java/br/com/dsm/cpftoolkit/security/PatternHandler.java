package br.com.dsm.cpftoolkit.security;

import java.util.regex.Pattern;

/*
 * Classe para validar o padrão dos dígitos do CPF inconsistente.
 *
 * O REGEX captura somente dígitos, 11 dígitos, com o range entre [0-9],
 * os dígitos definidos no range não podem se repetir todas às 11 vezes.
 *
 * REGEX ^(\d)(?!\1+$)\d{10}$
 */
final class PatternHandler implements EvaluableCPF {

    private final UntrustedCPF cpf;

    PatternHandler(UntrustedCPF cpf) {
        this.cpf = cpf;
    }

    @Override
    public int validateDigits() {
        return Pattern.matches("^(\\d)(?!\\1+$)\\d{10}$", cpf.getCPFWithoutDelimiter()) ? 0 : 1;
    }

    @Override
    public Reason getReason() {
        return Reason.INVALID_PATTERN;
    }
}
