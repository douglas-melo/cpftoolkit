package br.com.dsm.cpftoolkit.security;

/*
 * Esta API fornece os métodos utilitários para a validação do CPF.
 */
public final class CPFToolkitAPI {

    private CPFToolkitAPI() {
        throw new AssertionError("This class must not be instantiated.");
    }

    private static StatusProvider statusProvider(String cpf) {
        return StatusProvider
                .configureCPF(cpf)
                .putUnstrustedCPF()
                .putUnstrustedCPFValidator()
                .putReasonProvider()
                .putMessageBus()
                .putMessageReporter();
    }

    public static boolean isCPFTrusted(String cpf) {
        return statusProvider(cpf).provideStatus();
    }

    public static Reason getReason(String cpf) {
        return statusProvider(cpf).provideReason();
    }

    public static String getReasonToJSON(String cpf) {
        return statusProvider(cpf).provideReason().toString().toLowerCase().replaceAll("[_]", "");
    }

    public static int getCode(String cpf) {
        return statusProvider(cpf).provideCode();
    }

    public static String getMessage(String cpf) {
        return statusProvider(cpf).provideMessage();
    }
}
