package br.com.dsm.cpftoolkit.security;

/*
 * Esta classe recebe o CPF e constrói cada dependência através dos métodos
 * de instanciação.
 */
final class StatusReporter {

    private String cpf;
    private UntrustedCPF untrustedCPF;
    private UntrustedCPFValidator untrustedCPFValidator;
    private ReasonProvider reasonProvider;
    private MessageBus messageBus;
    private MessageReporter messageReporter;

    StatusReporter(String cpf) {
        this.cpf = cpf;
    }

    public StatusReporter putUnstrustedCPF() {
        untrustedCPF = new UntrustedCPF(cpf);
        return this;
    }

    public StatusReporter putUnstrustedCPFValidator() {
        untrustedCPFValidator = new UntrustedCPFValidator(untrustedCPF);
        return this;
    }

    public StatusReporter putReasonProvider() {
        reasonProvider = new ReasonProvider(untrustedCPFValidator);
        return this;
    }

    public StatusReporter putMessageBus() {
        messageBus = new MessageBus(untrustedCPF);
        return this;
    }

    public StatusReporter putMessageReporter() {
        messageReporter = new MessageReporter(reasonProvider, messageBus);
        return this;
    }

    public StatusProvider report() {
        return new StatusProvider(reasonProvider, messageReporter);
    }
}
