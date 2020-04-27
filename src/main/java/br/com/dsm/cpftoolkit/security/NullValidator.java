package br.com.dsm.cpftoolkit.security;

import java.util.Optional;

/*
 * Classe para validar os dígitos null do CPF inconsistente.
 *
 * Possui os métodos para validar os dígitos null a fim
 * de obter um CPF consistente.
 */
final class NullValidator implements EvaluableCPF {

    private UntrustedCPF cpf;

    NullValidator(UntrustedCPF cpf) {
        this.cpf = cpf;
    }

    private Optional<String> hasNullDigits() {
        return Optional.ofNullable(cpf.getCPF());
    }

    @Override
    public int validateDigits() {
        return hasNullDigits().isPresent() ? 0 : 1;
    }

    @Override
    public Reason getReason() {
        return Reason.NULL_CPF;
    }
}
