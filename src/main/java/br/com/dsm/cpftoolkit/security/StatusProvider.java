package br.com.dsm.cpftoolkit.security;

/*
 * Esta classe fornece o motivo, mensagem de descrição, e o código da validação,
 * que serão consumidos pela API.
 */
final class StatusProvider {

    private final ReasonProvider reasonProvider;
    private final MessageReporter messageReporter;

    StatusProvider(ReasonProvider reasonProvider, MessageReporter messageReporter) {
        this.reasonProvider = reasonProvider;
        this.messageReporter = messageReporter;
    }

    public String provideReason() {
        return reasonProvider.getReason().toString();
    }

    public boolean provideStatus() {
        return reasonProvider.getReason().hasTrustedStatus();
    }

    public int provideCode() {
        return reasonProvider.getReason().getCode();
    }

    public String provideMessage() {
        return messageReporter.reportMessage();
    }
}
