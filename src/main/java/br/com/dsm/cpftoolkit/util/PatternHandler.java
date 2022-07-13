package br.com.dsm.cpftoolkit.util;

import java.util.regex.Pattern;

/*
 * Classe para verificar o padrão dos dígitos do CPF.
 *
 * Possui este método para verificar o padrão dos dígitos, a fim
 * de formatar o CPF corretamente.
 */
final class PatternHandler {

    private final String cpf;

    PatternHandler(EmptyHandler emptyHandler) {
        this.cpf = emptyHandler.checkForEmptyOccurrence();
    }

    private String getCPFWithoutDelimiter() {
        return cpf.replaceAll("[\\s.\\-]", "");
    }

    public String checkPatternConsistency() {
        if (!Pattern.matches("^(\\d)(?!\\1+$)\\d{10}$", getCPFWithoutDelimiter())) {
            throw new IllegalCPFPatternException(
                    new ExceptionMessage().getIllegalCPFPatternMessage(cpf)
            );
        }
        return getCPFWithoutDelimiter();
    }
}
