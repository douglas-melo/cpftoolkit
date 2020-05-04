package br.com.dsm.cpftoolkit.security;

/**
 * Esta classe fornece os métodos para a validação do CPF.
 *
 * @author Douglas Melo
 */
public final class CPFToolkitAuth {

    private StatusProvider statusProvider;

    /**
     * Constrói o objeto e inicializa o atributo.
     * @param cpf O CPF, necessário para inicializar o atributo.
     */
    public CPFToolkitAuth(String cpf) {
        statusProvider = new StatusReporter(cpf)
                .putUnstrustedCPF()
                .putUnstrustedCPFValidator()
                .putReasonProvider()
                .putMessageBus()
                .putMessageReporter()
                .report();
    }

    /**
     * Retorna o status booleano da validação do CPF.
     * @return true caso o CPF seja válido.
     */
    public boolean isCPFValid() {
        return statusProvider.provideStatus();
    }

    /**
     * Retorna o motivo indicando o status da validação.
     * <br><br>
     *
     * TRUSTED_CPF <br>
     * NULL_CPF <br>
     * EMPTY_CPF <br>
     * INVALID_PATTERN <br>
     * INVALID_CPF <br>
     *
     * @return Uma String como o motivo indicando o status da validação.
     */
    public String getReason() {
        return statusProvider.provideReason();
    }

    /**
     * Retorna o motivo indicando o status da validação para o formato JSON.
     * <br><br>
     *
     * trustedcpf <br>
     * nullcpf <br>
     * emptycpf <br>
     * invalidpattern <br>
     * invalidcpf <br>
     *
     * @return Uma String como o motivo indicando o status da validação para o formato JSON.
     */
    public String getReasonToJSON() {
        return statusProvider.provideReason().toLowerCase().replaceAll("[_]", "");
    }

    /**
     * Retorna um inteiro indicando o status da validação.
     * <br><br>
     *
     * 0 - TRUSTED_CPF <br>
     * 1 - NULL_CPF <br>
     * 2 - EMPTY_CPF <br>
     * 3 - INVALID_PATTERN <br>
     * 4 - INVALID_CPF <br>
     *
     * @return Um inteiro indicando o status da validação.
     */
    public int getCode() {
        return statusProvider.provideCode();
    }

    /**
     * Retorna uma mensagem com a descrição do status da validação.
     * @return Uma String como mensagem de descrição do status da validação.
     */
    public String getMessqage() {
        return statusProvider.provideMessage();
    }
}
