package br.com.dsm.cpftoolkit.security;

/*
 * Esta classe recebe o CPF e constrói cada dependência através dos métodos
 * de instanciação.
 */
final class StatusProviderBuilder {

    private final String cpf;
    private UntrustedCPF untrustedCPF;
    private InconsistencyFinder inconsistencyFinder;
    private DescriptionMessage descriptionMessage;
    private DescriptionAppender descriptionAppender;

    StatusProviderBuilder(String cpf) {
        this.cpf = cpf;
    }

    public StatusProviderBuilder setUnstrustedCPF() {
        untrustedCPF = new UntrustedCPF(cpf);
        return this;
    }

    public StatusProviderBuilder setInconsistencyFinder() {
        inconsistencyFinder = new InconsistencyFinder(untrustedCPF);
        return this;
    }

    public StatusProviderBuilder setDescriptionMessage() {
        descriptionMessage = new DescriptionMessage(untrustedCPF);
        return this;
    }

    public StatusProviderBuilder setDescriptionAppender() {
        descriptionAppender = new DescriptionAppender(inconsistencyFinder, descriptionMessage);
        return this;
    }

    public StatusProvider build() {
        return new StatusProvider(inconsistencyFinder, descriptionAppender);
    }
}
