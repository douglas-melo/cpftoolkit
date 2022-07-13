package br.com.dsm.cpftoolkit.util;

import java.util.Optional;

/*
 * Classe para verificar ocorrências empty do CPF.
 *
 * Possui este método para verificar ocorrências empty, a fim
 * de formatar o CPF corretamente.
 */
final class EmptyHandler {

   private final NullHandler nullHandler;

    EmptyHandler(NullHandler nullHandler) {
        this.nullHandler = nullHandler;
    }

    public String checkForEmptyOccurrence() {
        return Optional.of(nullHandler.checkForNullOccurrence())
                .filter(p -> !p.trim().isEmpty())
                .orElseThrow(() -> new IllegalCPFArgumentException(
                        new ExceptionMessage().getIllegalCPFArgumentMessage())
                );
    }
}
