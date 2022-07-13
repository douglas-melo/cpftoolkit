package br.com.dsm.cpftoolkit.util;

/*
 * Classe responsável pela verificação do padrão dos dígitos do CPF.
 *
 * Recebe da API o CPF, faz a verificação em cada objeto manipulador
 * em busca de incosistências, e fornece para API uma instância estática da classe.
 */
final class PatternValidator {

    private final String cpf;

    private PatternValidator(String cpf) {
        this.cpf = cpf;
    }

    public static PatternValidator configureCPF(String cpf) {
        return new PatternValidator(cpf);
    }

    public String getCPF() {
        return new PatternHandler(
                new EmptyHandler(
                        new NullHandler(
                                new UntrustedCPF(cpf)
                        )
                )
        ).checkPatternConsistency();
    }
}
