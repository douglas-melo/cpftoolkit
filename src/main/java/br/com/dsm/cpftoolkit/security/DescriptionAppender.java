package br.com.dsm.cpftoolkit.security;

import java.util.HashMap;
import java.util.Map;

/*
 * Esta classe anexa a mensagem de descrição ao motivo da validação.
 * A captura da mensagem de descrição será feita a partir do critério do motivo da validação.
 */
final class DescriptionAppender {

    private final InconsistencyFinder inconsistencyFinder;
    private final DescriptionMessage descriptionMessage;

    DescriptionAppender(InconsistencyFinder inconsistencyFinder, DescriptionMessage descriptionMessage) {
        this.inconsistencyFinder = inconsistencyFinder;
        this.descriptionMessage = descriptionMessage;
    }

    private String findDescriptionByReason() {
        Map<Reason, String> descriptionByReason = new HashMap<>();

        descriptionByReason.put(Reason.NULL_CPF, descriptionMessage.getNullCPFMessage());
        descriptionByReason.put(Reason.EMPTY_CPF, descriptionMessage.getEmptyCPFMessage());
        descriptionByReason.put(Reason.INVALID_PATTERN, descriptionMessage.getInvalidCPFPatternMessage());
        descriptionByReason.put(Reason.INVALID_CPF, descriptionMessage.getInvalidCPFMessage());
        descriptionByReason.put(Reason.TRUSTED_CPF, descriptionMessage.getTrustedCPFMessage());

        return descriptionByReason.get(inconsistencyFinder.getReason());
    }

    public String getDescription() {
        return findDescriptionByReason();
    }
}
