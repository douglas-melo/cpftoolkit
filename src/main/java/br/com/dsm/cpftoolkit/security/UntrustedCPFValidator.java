package br.com.dsm.cpftoolkit.security;

import java.util.ArrayList;
import java.util.List;

/*
 * Classe para validar o CPF inconsistente.
 *
 * Agrupa as classes validadoras dos dígitos, e faz a checagem em cada objeto
 * em busca de inconsistências.
 */
final class UntrustedCPFValidator {

    private UntrustedCPF cpf;
    private List<EvaluableCPF> evaluableDigits;
    private EvaluableCPF occurrence;

    UntrustedCPFValidator(UntrustedCPF cpf) {
        this.cpf = cpf;

        loadEvaluableDigitsRepository();
        setOccurrence();
    }

    private void loadEvaluableDigitsRepository() {
        ArrayList<EvaluableCPF> evaluableDigitsRepository = new ArrayList<>();
        evaluableDigitsRepository.add(new NullValidator(cpf));
        evaluableDigitsRepository.add(new EmptyValidator(cpf));
        evaluableDigitsRepository.add(new PatternValidator(cpf));
        evaluableDigitsRepository.add(new CheckDigitValidator(cpf));

        evaluableDigits = evaluableDigitsRepository;
    }

    private void setOccurrence() {
        occurrence = evaluableDigits.stream().filter(p -> p.validateDigits() == 1).findFirst().orElseGet(AcceptedCheckDigit::new);
    }

    public EvaluableCPF findForOccurrence() {
        return occurrence;
    }
}
