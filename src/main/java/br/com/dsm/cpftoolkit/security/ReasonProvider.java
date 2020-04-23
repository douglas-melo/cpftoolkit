package br.com.dsm.cpftoolkit.security;

/*
 * Esta classe é responsavel por registrar e fornecer o motivo
 * durante a validação dos dígitos.
 */
final class ReasonProvider {

    private Reason reason;
    private UntrustedCPFValidator untrustedCPFValidator;

    ReasonProvider(UntrustedCPFValidator untrustedCPFValidator) {
        this.untrustedCPFValidator = untrustedCPFValidator;

        registerCause();
    }

    private void registerCause() {
        reason = untrustedCPFValidator.findForOccurence().getReason();
    }

    public Reason getReason() {
        return reason;
    }
}

