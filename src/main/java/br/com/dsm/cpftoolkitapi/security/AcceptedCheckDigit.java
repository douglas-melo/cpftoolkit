package br.com.dsm.cpftoolkitapi.security;

/*
 * Esta classe fornece o motivo válido caso a validação do dígito verificador
 * seja positiva.
 *
 * Não implementou a interface diretamente porque o contrato de validação
 * dos dígitos não é necessário nesta classe.
 */
final class AcceptedCheckDigit extends CheckDigitValidator {

    @Override
    public Reason getReason() {
        return Reason.TRUSTED_CPF;
    }
}
