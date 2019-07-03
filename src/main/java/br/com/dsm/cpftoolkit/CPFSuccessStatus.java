package br.com.dsm.cpftoolkit;

/**
 * Enum responsável por implementar o tipo e o código de
 * sucesso de validação do CPF.
 *
 * @author Douglas Melo
 * */
enum CPFSuccessStatus {

    VALID_CPF(0);

    private int code;

    /**
     * Construtor do Enum.
     *
     * Constrói e inicializa o atributo, o valor e o tipo.
     *
     * @param code o código do erro.
     * */
    CPFSuccessStatus(int code) {
        this.code = code;
    }

    /**
     * Retorna um inteiro como código de sucesso:<p/>
     *
     * 0 - Para CPF válido.
     *
     * @return um inteiro como código de sucesso.
     * */
    public int getCode() {
        return code;
    }
}