package br.com.dsm.cpftoolkit.security;

import java.util.Arrays;

/*
 * Classe para calcular o dígito verificador do CPF inconsistente.
 *
 * Possui os métodos para calcular o dígito verificador a fim
 * de verificar a consistência do CPF.
 */
class CheckDigitChecker {

    private final String cpf;

    CheckDigitChecker(String cpf) {
        this.cpf = cpf;
    }

    final public int validateCheckDigit() {
        return getCPFCheckDigits().equals(getFirstCheckDigit() + "" + getSecondCheckDigit()) ? 0 : 1;
    }

    private String getCPFCheckDigits() {
        return cpf.substring(9, 11);
    }

    private String getCPFWithoutCheckDigits() {
        return cpf.substring(0, 9);
    }

    private String getCPFWithFirstCheckDigit() {
        return getCPFWithoutCheckDigits() + "" + getFirstCheckDigit();
    }

    private int applyRemainderOperation(int checkDigit) {
        return checkDigit == 10 ? 0 : checkDigit;
    }

    private int reduceCPFDigits(int[] digits) {
        return Arrays.stream(digits).sum();
    }

    private int getFirstCheckDigit() {
        int[] digits = new int[9];

        for (int i = 0; i < getCPFWithoutCheckDigits().length(); i++) {
            digits[i] = ((i + 1) * (getCPFWithoutCheckDigits().charAt(i) - '0'));
        }
        int firstCheckDigit = reduceCPFDigits(digits) % 11;
        return applyRemainderOperation(firstCheckDigit);
    }

    private int getSecondCheckDigit() {
        int[] digits = new int[10];

        for (int y = 0; y < getCPFWithFirstCheckDigit().length(); y++) {
            digits[y] = ((y) * (getCPFWithFirstCheckDigit().charAt(y) - '0'));
        }
        int secondCheckDigit = reduceCPFDigits(digits) % 11;
        return applyRemainderOperation(secondCheckDigit);
    }
}
