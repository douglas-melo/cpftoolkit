package br.com.dsm.cpftoolkit.security;

/*
 * Esta classe contém os métodos para recuperar a mensagem de
 * descrição da validação.
 */
final class DescriptionMessage {

    private final UntrustedCPF cpf;

    DescriptionMessage(UntrustedCPF cpf) {
        this.cpf = cpf;
    }

    public String getTrustedCPFMessage() {
        return "O CPF " + cpf.getCPF() + " é válido.";
    }

    public String getNullCPFMessage() {
        return "O CPF informado é nulo.";
    }

    public String getEmptyCPFMessage() {
        return "O CPF informado é vazio.";
    }

    public String getInvalidCPFPatternMessage() {
        return "O CPF " + cpf.getCPF() + " não possui um padrão de dígitos válido.";
    }

    public String getInvalidCPFMessage() {
        return "O CPF " + cpf.getCPF() + " é inválido. Não foi possível calcular o dígito verificador.";
    }
}
