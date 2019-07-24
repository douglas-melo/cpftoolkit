package br.com.dsm.cpftoolkit;

/**
 * API helper.<p/>
 *
 * Fornece os métodos utilitários para a validação e tratamento de CPFs.
 * Possui métodos utilitários overloaded para o tratamento de CPFs válidos.
 *
 * @author Douglas Melo
 * */
public final class CPFToolkit {

    private static CPFSetter cpfSetter;

    /**
     * Construtor da classe.<p/>
     *
     * O construtor privado não permitirá que a classe seja instanciada, se houver
     * uma tentativa de instanciação internamente um AssertionError será lançado
     * com a mensagem descrevendo o erro.
     *
     * @throws AssertionError se houver a tentativa de instanciar a classe.
     * */
    private CPFToolkit() {
        // Lança um AssertionError se houver tentativas de instanciar esta classe internamente.
        throw new AssertionError("Esta classe não deve ser instanciada internamente.");
    }

    /**
     * Este método lança um IllegalCPFPatternException quando chamado, será utilizado para validar
     * o argumento dos métodos overloaded de tratamento de CPFs válidos.
     *
     * @throws IllegalCPFPatternException será lançado caso o padrão de dígitos do CPF válido
     * estiver incorreto.
     * */
    private static void runIllegalCPFPatternException() throws IllegalCPFPatternException {
        throw new IllegalCPFPatternException("Este CPF não possui um padrão de dígitos válido.");
    }

    /**
     * Retorna o status booleano da validação do CPF.<p/>
     *
     * Retorna true caso seja válido ou false caso seja inválido,
     * se a validação for bem sucedida constrói um CPF em estado válido.
     *
     * @return o status booleano da validação do CPF.
     * */
    public static boolean validateCPF(String cpf) {

        cpfSetter = new CPFSetter(new Cpf(cpf));

        boolean validationStatus = cpfSetter.validateCPF();

        // tenta construir um CPF em estado válido.
        cpfSetter.buildATrustedCPF();

        return validationStatus;
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
     * @throws AssertionCPFError se houver uma tentativa de validar um CPF que já é válido.
     * */
    public static CPFErrorStatus getReason() {
        return cpfSetter.getErrorStatus();
    }

    /**
     * Retorna como String o tipo do erro definido do Enum.<p/>
     *
     * NULL_CPF - Para CPF nulo.<br/>
     * EMPTY_CPF - Para CPF vazio.<br/>
     * INVALID_CPF_DIGITS_PATTERN - Para CPF com o padrão de dígitos inválido.<br/>
     * INVALID_CPF - Para CPF inválido.<br/>
     *
     * @return o tipo do erro como String.
     * @throws AssertionCPFError se houver uma tentativa de validar um CPF que já é válido.
     * */
    public static String getReasonValue() {
        return getReason().toString();
    }

    /**
     * Retorna o tipo do erro definido do Enum com o padrão minúsculo para o Json.<p/>
     *
     * null_cpf - Para CPF nulo.<br/>
     * empty_cpf - Para CPF vazio.<br/>
     * invalid_cpf_digits_pattern - Para CPF com o padrão de dígitos inválido.<br/>
     * invalid_cpf - Para CPF inválido.<br/>
     *
     * @return o tipo do erro com o padrão minúsculo para Json.
     * @throws AssertionCPFError se houver uma tentativa de validar um CPF que já é válido.
     * */
    public static String getReasonValueToJson() {
        return getReasonValue().toLowerCase();
    }

    /**
     * Retorna a mensagem com a descrição do erro.
     *
     * @return a mensagem com a descrição do erro.
     * @throws AssertionCPFError se houver uma tentativa de validar um CPF que já é válido.
     * */
    public static String getMessage() {
        return getReason().getDescription();
    }

    /**
     * Retorna um inteiro como código do erro:<p/>
     *
     * 1 - Para CPF nulo.<br/>
     * 2 - Para CPF vazio.<br/>
     * 3 - Para CPF com o padrão de dígitos inválido.<br/>
     * 4 - Para CPF inválido.<br/>
     *
     * @return um inteiro como código do erro.
     * @throws AssertionCPFError se houver uma tentativa de validar um CPF que já é válido.
     * */
    public static int getCode() {
        return getReason().getCode();
    }

    /**
     * Retorna como String o código do erro:<p/>
     *
     * 1 - Para CPF nulo.<br/>
     * 2 - Para CPF vazio.<br/>
     * 3 - Para CPF com o padrão de dígitos inválido.<br/>
     * 4 - Para CPF inválido.<br/>
     *
     * @return o código do erro como String.
     * @throws AssertionCPFError se houver uma tentativa de validar um CPF que já é válido.
     * */
    public static String getCodeValue() {
        return String.valueOf(getCode());
    }

    /**
     * Retorna o CPF validado sem formatação.
     *
     * @return o CPF validado sem formatação.
     * @throws IllegalCPFStateException se o método for chamado em um CPF inconsistente
     * que ainda não foi validado.
     * */
    public static String getCPF() {
        return cpfSetter.getATrustedCPF();
    }

    /**
     * Retorna o CPF validado formatado no formato XXX.XXX.XXX-XX.
     *
     * @return retorna o CPF validado formatado.
     * @throws IllegalCPFStateException se o método for chamado em um CPF inconsistente
     * que ainda não foi validado.
     * */
    public static String getFormattedCPF() {
        return getCPF().replaceAll("(^.{3})(.{3})(.{3})", "$1.$2.$3-");
    }

    /**
     * Retorna os três primeiros dígitos do CPF validado.
     *
     * @return os três primeiros dígitos do CPF validado.
     * @throws IllegalCPFStateException se o método for chamado em um CPF inconsistente
     * que ainda não foi validado.
     * */
    public static String getFirstThreeDigits() {
        return getCPF().substring(0, 3);
    }

    /**
     * Retorna os três dígitos do meio do CPF validado.
     *
     * @return os três dígitos do meio do CPF validado.
     * @throws IllegalCPFStateException se o método for chamado em um CPF inconsistente
     * que ainda não foi validado.
     * */
    public static String getMiddleThreeDigits() {
        return getCPF().substring(3, 6);
    }

    /**
     * Retorna os últimos três dígitos do CPF validado.
     *
     * @return os últimos três dígitos do CPF validado.
     * @throws IllegalCPFStateException se o método for chamado em um CPF inconsistente
     * que ainda não foi validado.
     * */
    public static String getLastThreeDigits() {
        return getCPF().substring(6, 9);
    }

    /**
     * Retorna o dígito verificador do CPF validado.
     *
     * @return o dígito verificador do CPF validado.
     * @throws IllegalCPFStateException se o método for chamado em um CPF inconsistente
     * que ainda não foi validado.
     * */
    public static String getCheckDigits() {
        return getCPF().substring(9, 11);
    }

    /**
     * Retorna o CPF formatado no formato XXX.XXX.XXX-XX.
     *
     * @param cpf o CPF.
     * @return o CPF formatado no formato XXX.XXX.XXX-XX.
     * @throws IllegalCPFPatternException caso o padrão de dígitos do CPF
     * estiver incorreto.
     * */
    public static String getFormattedCPF(String cpf) {

        String cpfValidated = CPFPatternValidator.validateCPFPattern(cpf);

        if (cpfValidated.isEmpty()) {
            runIllegalCPFPatternException();
        }
        return cpfValidated.replaceAll("(^.{3})(.{3})(.{3})", "$1.$2.$3-");
    }

    /**
     * Retorna os três primeiros dígitos do CPF.
     *
     * @param cpf o CPF.
     * @return os três primeiros dígitos do CPF
     * @throws IllegalCPFPatternException caso o padrão de dígitos do CPF
     * estiver incorreto.
     * */
    public static String getFirstThreeDigits(String cpf) {

        String cpfValidated = CPFPatternValidator.validateCPFPattern(cpf);

        if (cpfValidated.isEmpty()) {
            runIllegalCPFPatternException();
        }
        return cpfValidated.substring(0, 3);
    }

    /**
     * Retorna os três dígitos do meio do CPF.
     *
     * @param cpf o CPF
     * @return os três dígitos do meio do CPF.
     * @throws IllegalCPFPatternException caso o padrão de dígitos do CPF.
     * estiver incorreto.
     * */
    public static String getMiddleThreeDigits(String cpf) {

        String cpfValidated = CPFPatternValidator.validateCPFPattern(cpf);

        if (cpfValidated.isEmpty()) {
            runIllegalCPFPatternException();
        }
        return cpfValidated.substring(3, 6);
    }

    /**
     * Retorna os últimos três dígitos do CPF.
     *
     * @param cpf o CPF.
     * @return os últimos três dígitos do CPF.
     * @throws IllegalCPFPatternException caso o padrão de dígitos do CPF.
     * estiver incorreto.
     * */
    public static String getLastThreeDigits(String cpf) {

        String cpfValidated = CPFPatternValidator.validateCPFPattern(cpf);

        if (cpfValidated.isEmpty()) {
            runIllegalCPFPatternException();
        }
        return cpfValidated.substring(6, 9);
    }

    /**
     * Retorna o dígito verificador do CPF.
     *
     * @param cpf o CPF.
     * @return o dígito verificador do CPF.
     * @throws IllegalCPFPatternException caso o padrão de dígitos do CPF.
     * estiver incorreto.
     * */
    public static String getCheckDigits(String cpf) {

        String cpfValidated = CPFPatternValidator.validateCPFPattern(cpf);

        if (cpfValidated.isEmpty()) {
            runIllegalCPFPatternException();
        }
        return cpfValidated.substring(9, 11);
    }

    /**
     * Retorna o valor que representa o sucesso de validação definino no Enum se o CPF for true,
     * se o CPF for false um IllegalCPFStateException será lançado caso o método seja chamado.<p/>
     *
     * VALID_CPF - Para CPF válido.
     *
     * @return o valor que representa o sucesso de validação.
     * @throws IllegalCPFStateException se houver uma tentativa de chamar este método e o CPF for false.
     * */
    public static CPFSuccessStatus getSuccessStatus() {
        return cpfSetter.getSuccessStatus();
    }

    /**
     * Retorna como String o valor que representa o sucesso de validação definido no Enum se o CPF for true,
     * se o CPF for false um IllegalCPFStateException será lançado caso o método seja chamado.<p/>
     *
     * VALID_CPF - Para CPF válido.
     *
     * @return como String o valor que representa o sucesso de validação.
     * @throws IllegalCPFStateException se houver uma tentativa de chamar este método e o CPF for false.
     * */
    public static String getSuccessStatusValue() {
        return getSuccessStatus().toString();
    }

    /**
     * Retorna o valor que representa o sucesso de validação definino no Enum com o padrão minúsculo para o Json.<p/>
     *
     * valid_cpf - Para CPF válido.
     *
     * @return o valor que representa o sucesso de validação para o Json.
     * @throws IllegalCPFStateException se houver uma tentativa de chamar este método e o CPF for false.
     * */
    public static String getSuccessStatusToJson() {
        return getSuccessStatusValue().toLowerCase();
    }

    /**
     * Retorna um inteiro como código de sucesso de validação:<p/>
     *
     * 0 - Para CPF válido.
     *
     * @return um inteiro como código de sucesso de validação.
     * @throws IllegalCPFStateException se houver uma tentativa de chamar este método e o CPF for false.
     * */
    public static int getSuccessCode() {
        return getSuccessStatus().getCode();
    }

    /**
     * Retorna como String o código de sucesso de validação:<p/>
     *
     * 0 - Para CPF válido.
     *
     * @return o código de sucesso de validação como String.
     * @throws IllegalCPFStateException se houver uma tentativa de chamar este método e o CPF for false.
     * */
    public static String getSuccessCodeValue() {
        return String.valueOf(getSuccessCode());
    }
}