package br.com.dsm.cpftoolkitapi.security;

/*
 * Este enum implementa e contém os métodos para recuperar o motivo,
 * que será configurado de acordo com o resultado da validação.
 */
enum Reason {

    TRUSTED_CPF(0, true),
    NULL_CPF(1, false),
    EMPTY_CPF(2, false),
    INVALID_PATTERN(3, false),
    INVALID_CPF(4, false);

    private int code;
    private boolean status;

    Reason(int code, boolean status) {
        this.code = code;
        this.status = status;
    }

    public int getCode() {
        return code;
    }

    public boolean hasTrustedStatus() {
        return status;
    }
}