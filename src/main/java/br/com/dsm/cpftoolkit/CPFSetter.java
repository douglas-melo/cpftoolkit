package br.com.dsm.cpftoolkit;

import java.util.Optional;

/**
 * Classe responsável por fornecer os métodos validadores
 * para a API, e obter o CPF em um estado consistente
 * antes de construí-lo com um estado válido.
 *
 * @author Douglas Melo
 * */
final class CPFSetter {

    private Cpf cpf;
    private String trustedCPF;

    /**
     * Construtor da classe.
     *
     * @param cpf necessário para instanciar o objeto e inicializar o atributo.
     * */
    CPFSetter(Cpf cpf) {
        this.cpf = cpf;
    }

    /**
     * Retorna o status da validação do CPF.
     *
     * @return o status booleano da validação do CPF.
     * */
    public boolean validateCPF() {
        return cpf.validateCPFDigits();
    }

    /**
     * Se o CPF for válido incializa a variável trustedCPF
     * com um CPF em estado válido.
     * */
    public void buildATrustedCPF() {

        if (cpf.isCPFValid()) {
            this.trustedCPF = cpf.getCPF();
        }
    }

    /**
     * Retorna um CPF em estado válido, se existir. Se o Optional retornar vazio,
     * lança um IllegalCPFStateException com a descrição do erro para indicar a
     * inconsistência do CPF. Este retorno será utilizado pelos métodos de tratamento de CPF
     * da API {@link CPFToolkit}, isto garantirá a validade do CPF atual sendo tratado, e evitará
     * chamadas à métodos de tratamento de CPF em CPFs ainda inconsistentes.
     *
     * @return um CPF em estado válido.
     * @throws IllegalCPFStateException se o Optional retornar vazio.
     * */
    public String getATrustedCPF() {

        Optional<String> getOptionalTrustedCPF = Optional.ofNullable(this.trustedCPF);

        return getOptionalTrustedCPF.orElseThrow(() ->
                new IllegalCPFStateException(getErrorStatusDescription()));
    }

    /**
     * Este método lança um AssertionCPFError quando chamado, nesta classe ele será chamado
     * para validar o retorno dos metodos de tratamento de CPFs inválidos, para casos onde
     * houver tentativas de passar para o método um CPF já válido.
     *
     * @throws AssertionCPFError será lançado se houver a tentativa de validar um CPF válido.
     * */
    private void runAssertionCPFError() {
        throw new AssertionCPFError("Este CPF já é válido, não é possível validá-lo.");
    }

    /**
     * Este método lança um IllegalCPFStateException quando chamado. Nesta classe ele será usado
     * para validar chamadas à métodos que retornam o código de sucesso de validação do CPF, para
     * casos onde houver tentativas de acessar o código de sucesso em CPFs ainda inconsistentes.
     *
     * @param message a mensagem descrevendo a exceção.
     * @throws IllegalCPFStateException será lançado se um CPF inconsistente tentar acessar o
     * código de sucesso de validação.
     * */
    private void runIllegalCPFStateException(String message) throws IllegalCPFStateException {
        throw new IllegalCPFStateException(message);
    }

    /**
     * Retorna a descrição do erro definido no Enum se o CPF for false,
     * caso o CPF seja true um AssertionError será lançado se o método for chamado.
     *
     * @return a mensagem com a descrição do erro.
     * @throws AssertionError se houver uma tentativa de acessar o método
     * passando um CPF que já é válido.
     * */
    private String getErrorStatusDescription() {
        return getErrorStatus().getDescription();
    }

    /**
     * Retorna o tipo do erro definido no Enum se o CPF for false,
     * caso o CPF seja true um AssertionError será lançado se o método for chamado.<p/>
     *
     * NULL_CPF - Para CPF nulo.<br/>
     * EMPTY_CPF - Para CPF vazio.<br/>
     * INVALID_CPF_DIGITS_PATTERN - Para CPF com o padrão de dígitos inválido.<br/>
     * INVALID_CPF - Para CPF inválido.<br/>
     *
     * @return o tipo do erro.
     * @throws AssertionError se houver uma tentativa de validar um CPF que já é válido.
     * */
    public CPFErrorStatus getErrorStatus() {

        if (cpf.isCPFValid()) {
            runAssertionCPFError();
        }
        return cpf.getCPFErrorStatus();
    }

    /**
     * Retorna o valor que representa o sucesso de validação definino no Enum se o CPF for true,
     * se o CPF for false um IllegalCPFStateException será lançado caso o método seja chamado.<p/>
     *
     * VALID_CPF - Para CPF válido.
     *
     * @return o valor que representa o sucesso de validação.
     * @throws IllegalCPFStateException descrevendo a inconsistencia do CPF,
     * se houver uma tentativa de chamar este método e o CPF for false.
     * */
    public CPFSuccessStatus getSuccessStatus() {

        if (!cpf.isCPFValid()) {
            runIllegalCPFStateException(getErrorStatusDescription());
        }
        return cpf.getCPFSuccessStatus();
    }
}