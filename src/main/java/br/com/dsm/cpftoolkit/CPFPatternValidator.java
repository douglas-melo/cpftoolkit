package br.com.dsm.cpftoolkit;

import java.util.regex.Pattern;

/**
 * Classe helper para validar o argumento dos métodos overloaded
 * de tratamento de CPF da API {@link CPFToolkit}.
 *
 * @author Douglas Melo
 * */
final class CPFPatternValidator {

    /**
     * Construtor da classe.
     *
     * O construtor privado não permitirá que a classe seja instanciada, se houver
     * uma tentativa de instanciação internamente um AssertionError será lançado
     * com a mensagem descrevendo o erro.
     *
     * @throws AssertionError se houver tentativa de instanciar a classe internamente.
     * */
    private CPFPatternValidator() {
        // Lança um AssertionError se houver tentativas de instanciar esta classe internamente.
        throw new AssertionError("Esta classe não deve ser instanciada internamente.");
    }

    /**
     * Este método recebe um CPF e válida o padrão de dígitos.
     * Se der match com o REGEX retorna o CPF, caso contrário retorna
     * uma String vazia.
     *
     * @param cpf o CPF.
     * @return o CPF ou uma String vazia.
     * */
    public static String validateCPFPattern(String cpf) {

        // Remove os spaces, "." e "-" e substitui pelo padrão da expressão regular.
        cpf = cpf.replaceAll("[\\s.\\-]", "");

        final String CPF_REGEX_PATTERN = "^(\\d)(?!\\1+$)\\d{10}$";
        boolean isCPFMatch = Pattern.matches(CPF_REGEX_PATTERN, cpf);

        if (isCPFMatch) {
            return cpf;
        }
        return "";
    }
}