package br.com.dsm.cpftoolkit.security;

/*
 * Esta classe fornece os métodos para a validação do CPF.
 */
public final class CPFToolkitAuth {

    private StatusProvider statusProvider;

    CPFToolkitAuth(String cpf) {
        statusProvider = new StatusReporter(cpf)
                .putUnstrustedCPF()
                .putUnstrustedCPFValidator()
                .putReasonProvider()
                .putMessageBus()
                .putMessageReporter()
                .report();
    }

    public boolean getValidationStatus() {
        return statusProvider.provideStatus();
    }

    public String getReason() {
        return statusProvider.provideReason();
    }

    public String getReasonToJSON() {
        return statusProvider.provideReason().toLowerCase().replaceAll("[_]", "");
    }

    public int getCode() {
        return statusProvider.provideCode();
    }

    public String getMessage() {
        return statusProvider.provideMessage();
    }
}
