package br.com.dsm.cpftoolkitapi.security;

/*
 * Esta interface fornece dois contratos para serem implementados.
 * Um para validação dos dígitos, e outro para capturar o motivo.
 */
interface EvaluableCPF {

    int validateDigits();
    Reason getReason();
}
