package br.com.dsm.cpftoolkit.security;

/*
 * Esta classe é responsavel por registrar e fornecer o motivo
 * durante a validação dos dígitos.
 */
final class ReasonProvider {

    private Reason reason;
    private final UntrustedCPFValidator untrustedCPFValidator;

    ReasonProvider(UntrustedCPFValidator untrustedCPFValidator) {
        this.untrustedCPFValidator = untrustedCPFValidator;

        registerCause();
    }

    private void registerCause() {
        reason = untrustedCPFValidator.findForOccurrence().getReason();
    }

    public Reason getReason() {
        return reason;
    }
}

