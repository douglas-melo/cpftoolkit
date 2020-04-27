package br.com.dsm.cpftoolkit.security;

import java.util.HashMap;
import java.util.Map;

/*
 * Esta classe mapeia a mensagem de descrição ao motivo da validação.
 * A captura da mensagem de descrição será feita a partir do critério do motivo da validação.
 */
final class MessageReporter {

    private ReasonProvider reasonProvider;
    private MessageBus messageBus;
    private String message;

    MessageReporter(ReasonProvider reasonProvider, MessageBus messageBus) {
        this.reasonProvider = reasonProvider;
        this.messageBus = messageBus;

        loadMessageCatalog();
    }

    private void loadMessageCatalog() {
        Map<Reason, String> messageCatalog = new HashMap<>();

        messageCatalog.put(Reason.NULL_CPF, messageBus.getNullMessage());
        messageCatalog.put(Reason.EMPTY_CPF, messageBus.getEmptyMessage());
        messageCatalog.put(Reason.INVALID_PATTERN, messageBus.getPatternMessage());
        messageCatalog.put(Reason.INVALID_CPF, messageBus.getCheckDigitsMessage());
        messageCatalog.put(Reason.TRUSTED_CPF, messageBus.getSuccessMessage());

        message = messageCatalog.get(reasonProvider.getReason());
    }

    public String reportMessage() {
        return message;
    }
}
