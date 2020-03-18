package br.com.dsm.cpftoolkit;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

/**
 * Classe que representa o objeto CPF.<p/>
 *
 * Os atributos serão configurados e validados até a obtenção do
 * estado válido do objeto. Além de ser um anti-pattern, nesse caso específico
 * algumas regras de convenção como nomenclatura de métodos foram quebradas
 * para melhorar a legibidade do nome CPF.
 *
 * @author Douglas Melo
 * */
final class Cpf {

    private String cpf;
    private CPFSuccessStatus cpfSuccessStatus;
    private CPFErrorStatus cpfErrorStatus;
    private boolean cpfValidationStatus;

    /**
     * Construtor da classe.
     *
     * @param cpf necessário para instanciar o objeto e inicializar o atributo.
     * */
    Cpf(String cpf) {
        this.cpf = cpf;
    }

    /**
     * Retorna o CPF, se houver spaces, "." e "-" na String
     * substitui pelo padrão do REGEX.
     *
     * @return o CPF.
     * */
    public String getCPF() {
        return this.cpf.replaceAll("[\\s.\\-]", "");
    }

    /**
     * Retorna um Optional vazio em casos de dígitos null,
     * ou retorna o CPF caso exista.
     *
     * @return um Optional vazio, ou o CPF.
     * */
    private Optional<String> getOptionalCPF() {
        return Optional.ofNullable(this.cpf);
    }

    /**
     * Valida os dígitos null retornando true caso o CPF
     * exista, ou false em casos de dígitos null. Se houver um retorno null,
     * configura o tipo do erro, atribuindo o tipo definido no Enum à variável cpfErrorStatus.
     *
     * @return o status booleano da validação de dígitos nulos.
     * */
    private boolean validateNullCPFDigits() {

        boolean nullDigitsValidationStatus = this.getOptionalCPF().isPresent();

        if (!nullDigitsValidationStatus) {
            cpfErrorStatus = CPFErrorStatus.NULL_CPF;
        }
        return nullDigitsValidationStatus;
    }

    /**
     * Valida Strings vazias retornando true caso o CPF
     * exista, ou false em casos de Strings vazias.
     * Se houver um retorno vazio, configura o tipo do erro,
     * atribuindo o tipo definido no Enum à variável cpfErrorStatus.
     *
     * @return o status booleano da validação de dígitos vazios.
     * */
    private boolean validateEmptyCPFDigits() {

        boolean emptyDigitsValidationStatus = !getCPF().isEmpty();

        if (!emptyDigitsValidationStatus) {
            cpfErrorStatus = CPFErrorStatus.EMPTY_CPF;
        }
        return emptyDigitsValidationStatus;
    }

    /**
     * Valida o padrão dos dígitos do CPF, comparando-o com o padrão da expressão regular
     * definido em CPF_REGEX_PATTERN. Retorna true, em casos de match do CPF com o padrão do REGEX,
     * ou retorna false caso contrário. Se o match retornar false, configura o tipo do erro,
     * atribuindo o tipo definido no Enum à variável cpfErrorStatus.
     *
     * @return o status booleano da validação do padrão dos dígitos.
     * */
    private boolean validateCPFDigitsPattern() {

        /*
        * O REGEX captura somente dígitos, 11 dígitos, com o range entre [0-9],
        * os dígitos definidos no range não podem se repetir todas às 11 vezes.
        * */
        final String CPF_REGEX_PATTERN = "^(\\d)(?!\\1+$)\\d{10}$";

        // Tenta casar o padrão dos dígitos do CPF, com o padrão estabelecido pelo regex.
        boolean digitsPatternValidationStatus = Pattern.matches(CPF_REGEX_PATTERN, getCPF());

        if (!digitsPatternValidationStatus) {

            // Configura a descrição do erro, passando o CPF com o padrão de dígitos inválido.
            CPFErrorStatus.INVALID_CPF_DIGITS_PATTERN.setDescription
            ("O Cpf " + getCPF() + " não possui um padrão de dígitos válido.");

            // Configura o erro, atribuindo o tipo do enum.
            cpfErrorStatus = CPFErrorStatus.INVALID_CPF_DIGITS_PATTERN;
        }
        return digitsPatternValidationStatus;
    }

    /**
     * Determina se CPF é ou não válido.<p/>
     *
     * Obtem os dígitos verificadores utilizando o cálculo mod 11, conforme as normas
     * da Receita Federal, e faz a validação, verificando se existe igualdade entre os
     * dígitos verificadores provenientes do cálculo, e os dígitos recebidos do atributo que
     * representa o CPF.<br/><br/>
     *
     * Configura a variável de status de validação de acordo com o resultado do cálculo,
     * true se houver igualdade entre os dígitos verificadores, e false caso contrário.
     * Se não houver igualdade entre os dígitos verificadores configura o tipo do erro,
     * atribuindo o tipo definido no Enum à variável cpfErrorStatus.
     *
     * @return o status booleano da validação dos dígitos verificadores.
     * */
    private boolean validateCPFCheckDigits() {

        String checkDigits = getCPF().substring(9, 11);
        StringBuilder cpfWithoutCheckDigits = new StringBuilder(getCPF().substring(0, 9));

        List<Integer> processedDigits = new ArrayList<>(19);

        /*
         * Insere na lista os dígitos, que já estão sendo multiplicados pela variável de
         * incremento, à medida em que ocorre o incremento. Neste caso a váriavel é o multiplicador.
         * */
        for (int i = 0; i < cpfWithoutCheckDigits.length(); i++) {
            processedDigits.add((i + 1) * (cpfWithoutCheckDigits.charAt(i) - '0'));
        }

        // Soma todos os elementos da lista.
        int sumOfMultiplicatedElements = processedDigits
                .stream()
                .mapToInt(Integer::intValue)
                .sum();

        int firstCheckDigit = sumOfMultiplicatedElements % 11;

        // Se o resto da divisão for igual a 10, o dígito verificador será configurado para 0.
        if (sumOfMultiplicatedElements % 11 == 10) {
            firstCheckDigit = 0;
        }

        /*
         * Atribui o cpf sem os dígitos verificadores à nova variável, já com o primeiro dígito verificador incluso
         * proveniente do cálculo anterior, para a realização do cálculo do segundo dígito verificador.
         * */
        StringBuilder cpfWithFirstCheckDigit = cpfWithoutCheckDigits.append(firstCheckDigit);

        // Cálculo do segundo dígito verificador.
        for (int y = 0; y < cpfWithFirstCheckDigit.length(); y++) {
            processedDigits.add((y) * (cpfWithFirstCheckDigit.charAt(y) - '0'));
        }

        int SecondSumOfMultiplicatedElements = processedDigits
                .subList(9, 19)
                .stream()
                .mapToInt(Integer::intValue)
                .sum();

        int secondCheckDigit = SecondSumOfMultiplicatedElements % 11;

        if (SecondSumOfMultiplicatedElements % 11 == 10) {
            secondCheckDigit = 0;
        }

        String validatedCheckDigits = firstCheckDigit + "" + secondCheckDigit;
        boolean checkDigitsValidationStatus = validatedCheckDigits.equals(checkDigits);

        // Configura o status de validação do CPF atribuindo o resultado booleano da comparação.
        this.cpfValidationStatus = checkDigitsValidationStatus;

        if (!checkDigitsValidationStatus) {

            CPFErrorStatus.INVALID_CPF.setDescription
            ("O CPF " + getCPF() + " é inválido. Não foi possível calcular o dígito verificador.");

            cpfErrorStatus = CPFErrorStatus.INVALID_CPF;
        }

        // Configura o sucesso da validação, atribuindo o valor do enum.
        cpfSuccessStatus = CPFSuccessStatus.VALID_CPF;

        return checkDigitsValidationStatus;
    }

    /**
     * Chama os métodos validadores, o próximo método somente será
     * chamado caso a condição anterior seja verdadeira.
     *
     * @return o status booleano da validação de cada um dos métodos validadores.
     * */
    public boolean validateCPFDigits() {
        return
        validateNullCPFDigits   () &&
        validateEmptyCPFDigits  () &&
        validateCPFDigitsPattern() &&
        validateCPFCheckDigits  ();
    }

    /**
     * Retorna o status booleano do CPF, que foi configurado de acordo com o resultado
     * do cálculo e da verificação de igualdade entre os dígitos verificadores do CPF processado,
     * e do CPF recebido. Retorna true para CPF válido, ou false para CPF inválido.
     *
     * @return o status booleano da validação do CPF.
     * */
    public boolean isCPFValid() {
        return this.cpfValidationStatus;
    }

    /**
     * Retorna o tipo do erro definido no Enum:<p/>
     *
     * NULL_CPF - Para CPF nulo.<br/>
     * EMPTY_CPF - Para CPF vazio.<br/>
     * INVALID_CPF_DIGITS_PATTERN - Para CPF com o padrão de dígitos inválido.<br/>
     * INVALID_CPF - Para CPF inválido.<br/>
     *
     * @return o tipo do erro.
     * */
    public CPFErrorStatus getCPFErrorStatus() {
        return this.cpfErrorStatus;
    }

    /**
     * Retorna o tipo definido no Enum como código de sucesso:<p/>
     *
     * VALID_CPF - Para CPF válido.
     *
     * @return o tipo como código de sucesso.
     * */
    public CPFSuccessStatus getCPFSuccessStatus() {
        return this.cpfSuccessStatus;
    }
}
