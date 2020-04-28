package br.com.dsm.cpftoolkit.util;

/*
 * Esta API fornece os métodos utilitários para o tratamento dos dígitos do CPF.
 */
public final class CPFToolkitFormatter {

    private CPFToolkitFormatter() {
        throw new AssertionError("This class must not be instantiated.");
    }

    private static PatternValidator patternValidator(String cpf) {
        return PatternValidator.configureCPF(cpf);
    }

    public static String removeDelimiter(String cpf) {
        return patternValidator(cpf).getCPF();
    }

    public static String insertDelimiter(String cpf) {
        return patternValidator(cpf).getCPF().replaceAll("(^.{3})(.{3})(.{3})", "$1.$2.$3-");
    }

    public static String getCheckDigit(String cpf) {
        return patternValidator(cpf).getCPF().substring(9, 11);
    }

    public static String getFirstThreeDigits(String cpf) {
        return patternValidator(cpf).getCPF().substring(0, 3);
    }

    public static String getMiddleThreeDigits(String cpf) {
        return patternValidator(cpf).getCPF().substring(3, 6);
    }

    public static String getLastThreeDigits(String cpf) {
        return patternValidator(cpf).getCPF().substring(6, 9);
    }
}
