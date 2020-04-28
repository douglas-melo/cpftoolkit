package br.com.dsm.cpftoolkit.util;

import java.util.Optional;
import java.util.regex.Pattern;

/*
 * Classe responsável pela validação do padrão dos dígitos do CPF.
 */
public final class PatternValidator {

    private String cpf;

    private PatternValidator(String cpf) {
        this.cpf = checkPatternConsistency(checkEmptyOccurrences(checkNullOccurrences(cpf)));
    }

    public static PatternValidator configureCPF(String cpf) {
        return new PatternValidator(cpf);
    }

    public String getCPF() {
        return cpf;
    }

    private String getIllegalCPFArgumentMessage() {
        return "Este CPF não possui um padrão de dígitos válido.";
    }

    private String getIllegalCPFPatternMessage(String cpf) {
        return "O CPF " + cpf + " não possui um padrão de dígitos válido.";
    }

    private String checkNullOccurrences(String cpf) {
        return Optional
                .ofNullable(cpf)
                .orElseThrow(() -> new IllegalCPFArgumentException(getIllegalCPFArgumentMessage()));
    }

    private String checkEmptyOccurrences(String cpf) {
        return Optional
                .of(cpf)
                .filter(p -> !p.trim().isEmpty())
                .orElseThrow(() -> new IllegalCPFArgumentException(getIllegalCPFArgumentMessage()));
    }

    private String removeDelimiter(String cpf) {
        return cpf.replaceAll("[\\s.\\-]", "");
    }

    private String checkPatternConsistency(String cpf) {
        final String CPF_REGEX_PATTERN = "^(\\d)(?!\\1+$)\\d{10}$";

        final String trimmedCPF = removeDelimiter(cpf);

        if (!Pattern.matches(CPF_REGEX_PATTERN, trimmedCPF)) {
            throw new IllegalCPFPatternException(getIllegalCPFPatternMessage(cpf));
        }
        return trimmedCPF;
    }
}
