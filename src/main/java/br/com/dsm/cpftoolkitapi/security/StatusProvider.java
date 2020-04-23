package br.com.dsm.cpftoolkitapi.security;

/*
 * Esta classe agrupa e instancia as dependências.
 * Fornece o motivo, mensagem de descrição, e o código da validação,
 * que serão consumidos pela API.
 */
final class StatusProvider {

    private String cpf;
    private UntrustedCPF untrustedCPF;
    private UntrustedCPFValidator untrustedCPFValidator;
    private ReasonProvider reasonProvider;
    private MessageBus messageBus;
    private MessageReporter messageReporter;

    private StatusProvider(String cpf) {
        this.cpf = cpf;
    }

    public static StatusProvider configureCPF(String cpf) {
        return new StatusProvider(cpf);
    }

    public StatusProvider putUnstrustedCPF() {
        untrustedCPF = new UntrustedCPF(cpf);
        return this;
    }

    public StatusProvider putUnstrustedCPFValidator() {
        untrustedCPFValidator = new UntrustedCPFValidator(untrustedCPF);
        return this;
    }

    public StatusProvider putReasonProvider() {
        reasonProvider = new ReasonProvider(untrustedCPFValidator);
        return this;
    }

    public StatusProvider putMessageBus() {
        messageBus = new MessageBus(untrustedCPF);
        return this;
    }

    public StatusProvider putMessageReporter() {
        messageReporter = new MessageReporter(reasonProvider, messageBus);
        return this;
    }

    public Reason provideReason() {
        return reasonProvider.getReason();
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
