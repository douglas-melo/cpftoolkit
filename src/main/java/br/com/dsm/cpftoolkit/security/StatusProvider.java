package br.com.dsm.cpftoolkit.security;

/*
 * Esta classe fornece o motivo, mensagem de descrição, e o código da validação,
 * que serão consumidos pela API.
 */
final class StatusProvider {

    private final InconsistencyFinder inconsistencyFinder;
    private final DescriptionAppender descriptionAppender;

    StatusProvider(InconsistencyFinder inconsistencyFinder, DescriptionAppender descriptionAppender) {
        this.inconsistencyFinder = inconsistencyFinder;
        this.descriptionAppender = descriptionAppender;
    }

    public String provideReason() {
        return inconsistencyFinder.getReason().toString();
    }

    public boolean provideStatus() {
        return inconsistencyFinder.getReason().hasTrustedStatus();
    }

    public int provideCode() {
        return inconsistencyFinder.getReason().getCode();
    }

    public String provideDescription() {
        return descriptionAppender.getDescription();
    }
}
