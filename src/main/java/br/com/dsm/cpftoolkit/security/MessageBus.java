package br.com.dsm.cpftoolkit.security;

/*
 * Esta classe contém os métodos para recuperar a mensagem de
 * descrição da validação.
 */
final class MessageBus {

    private final UntrustedCPF cpf;

    MessageBus(UntrustedCPF cpf) {
        this.cpf = cpf;
    }

    public String getSuccessMessage() {
        return "O CPF " + cpf.getCPF() + " é válido.";
    }

    public String getNullMessage() {
        return "O CPF informado é nulo.";
    }

    public String getEmptyMessage() {
        return "O CPF informado é vazio.";
    }

    public String getPatternMessage() {
        return "O CPF " + cpf.getCPF() + " não possui um padrão de dígitos válido.";
    }

    public String getCheckDigitsMessage() {
        return "O CPF " + cpf.getCPF() + " é inválido. Não foi possível calcular o dígito verificador.";
    }
}
