package br.com.dsm.cpftoolkit.util;

/**
 * Esta API fornece os métodos utilitários para o tratamento dos dígitos do CPF.
 *
 * @author Douglas Melo
 */
public final class CPFToolkitFormatter {

    /**
     * O construtor privado lançará um {@link AssertionError} se houver qualquer tentativa
     * de instanciação desta classe internamente.
     */
    private CPFToolkitFormatter() {
        throw new AssertionError("This class must not be instantiated.");
    }

    /**
     * Retorna uma instância estática da classe {@link PatternChecker}.
     *
     * @param cpf O CPF.
     * @return Uma instância estática da classe {@link PatternChecker}.
     */
    private static PatternChecker patternChecker(String cpf) {
        return PatternChecker.getInstance(cpf);
    }

    /**
     * Remove os separadores do CPF.
     *
     * @param cpf O CPF.
     * @return O CPF com o formato XXXXXXXXXXX.
     */
    public static String removeDelimiter(String cpf) {
        return patternChecker(cpf).checkCPFPattern();
    }

    /**
     * Insere os separadores no CPF.
     *
     * @param cpf O CPF.
     * @return O CPF com o formato XXX.XXX.XXX-XX.
     */
    public static String insertDelimiter(String cpf) {
        return patternChecker(cpf).checkCPFPattern().replaceAll("(^.{3})(.{3})(.{3})", "$1.$2.$3-");
    }

    /**
     * Retorna os dois últimos dígitos do CPF.
     *
     * @param cpf O CPF.
     * @return O dígito verificador do CPF.
     */
    public static String getCheckDigit(String cpf) {
        return patternChecker(cpf).checkCPFPattern().substring(9, 11);
    }

    /**
     * Retorna os três primeiros dígitos do CPF.
     *
     * @param cpf O CPF.
     * @return Os três primeiros dígitos do CPF.
     */
    public static String getFirstThreeDigits(String cpf) {
        return patternChecker(cpf).checkCPFPattern().substring(0, 3);
    }

    /**
     * Retorna os três dígitos intermediários do CPF.
     *
     * @param cpf O CPF.
     * @return Os três dígitos intermediários do CPF.
     */
    public static String getMiddleThreeDigits(String cpf) {
        return patternChecker(cpf).checkCPFPattern().substring(3, 6);
    }

    /**
     * Retorna os três últimos dígitos do CPF.
     *
     * @param cpf O CPF.
     * @return Os três últimos dígitos do CPF.
     */
    public static String getLastThreeDigits(String cpf) {
        return patternChecker(cpf).checkCPFPattern().substring(6, 9);
    }
}
