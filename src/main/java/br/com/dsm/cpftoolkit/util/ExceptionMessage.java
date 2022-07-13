package br.com.dsm.cpftoolkit.util;

/*
 * Esta classe contém os métodos para recuperar a mensagem de
 * exceção, para os casos excepcionais da verificação dos dígitos do CPF.
 */
final class ExceptionMessage {

    public String getIllegalCPFArgumentMessage() {
        return "Este CPF não possui um padrão de dígitos válido.";
    }

    public String getIllegalCPFPatternMessage(String cpf) {
        return "O CPF " + cpf + " não possui um padrão de dígitos válido.";
    }
}
