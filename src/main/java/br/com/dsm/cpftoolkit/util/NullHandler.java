package br.com.dsm.cpftoolkit.util;

import java.util.Optional;

/*
 * Classe para verificar ocorrências null do CPF.
 *
 * Possui este método para verificar ocorrências null, a fim
 * de formatar o CPF corretamente.
 */
final class NullHandler {

    private final UntrustedCPF cpf;

    NullHandler(UntrustedCPF cpf) {
        this.cpf = cpf;
    }

    public String checkForNullOccurrence() {
        return Optional.ofNullable(cpf.getCPF())
                .orElseThrow(() -> new IllegalCPFArgumentException(
                        new ExceptionMessage().getIllegalCPFArgumentMessage())
                );
    }
}
