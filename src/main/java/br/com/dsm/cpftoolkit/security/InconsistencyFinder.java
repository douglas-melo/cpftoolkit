package br.com.dsm.cpftoolkit.security;

import java.util.ArrayList;
import java.util.List;

/*
 * Classe para validar o CPF inconsistente.
 *
 * Agrupa as classes manipuladoras dos dígitos, e faz a checagem em cada objeto
 * em busca de inconsistências.
 */
final class InconsistencyFinder {

    private final UntrustedCPF cpf;

    InconsistencyFinder(UntrustedCPF cpf) {
        this.cpf = cpf;
    }

    private List<EvaluableCPF> listOfHandlers() {
        ArrayList<EvaluableCPF> listOfHandlers = new ArrayList<>();

        listOfHandlers.add(new NullHander(cpf));
        listOfHandlers.add(new EmptyHandler(cpf));
        listOfHandlers.add(new PatternHandler(cpf));
        listOfHandlers.add(new CheckDigitHandler(cpf));

        return listOfHandlers;
    }

    private Reason findForInconsistency() {
        return listOfHandlers()
                .stream()
                .filter(e -> e.validateDigits() == 1)
                .map(e -> e.getReason())
                .findFirst()
                .orElse(Reason.TRUSTED_CPF);
    }

    public Reason getReason() {
        return findForInconsistency();
    }
}
