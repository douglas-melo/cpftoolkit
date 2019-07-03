package br.com.dsm.cpftoolkit;

/**
 * Enum responsável por implementar e configurar o tipo, código, e
 * descrição dos erros de validação do CPF.
 *
 * @author Douglas Melo
 * */
enum CPFErrorStatus {

    NULL_CPF(1, "O CPF informado é nulo."),
    EMPTY_CPF(2, "O CPF informado é vazio."),
    INVALID_CPF_DIGITS_PATTERN(3, ""),
    INVALID_CPF(4, "");

    private int code;
    private String description;

    /**
     * Construtor do Enum.
     *
     * Constrói e inicializa os atributos e os valores.
     *
     * @param code o código do erro.
     * @param description a descrição do erro.
     * */
    CPFErrorStatus(int code, String description) {
        this.code = code;
        this.description = description;
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
     * */
    public int getCode() {
        return this.code;
    }

    /**
     * Retorna uma mensagem com a descrição do erro.
     *
     * @return a descrição do erro.
     * */
    public String getDescription() {
        return this.description;
    }

    /**
     * Configura a descrição do erro dos valores do Enum.
     * Neste caso será usado para configurar a descrição de INVALID_CPF_DIGITS_PATTERN,
     * e INVALID_CPF passando como argumento o CPF inconsistente.
     * */
    public void setDescription(String description) {
        this.description = description;
    }
}