package br.com.dsm.cpftoolkit.test;

import br.com.dsm.cpftoolkit.AssertionCPFError;
import br.com.dsm.cpftoolkit.CPFToolkit;
import br.com.dsm.cpftoolkit.IllegalCPFPatternException;
import br.com.dsm.cpftoolkit.IllegalCPFStateException;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AppTest {

    /*
    * Testa o retorno do método validateCPF
    * */
    @Test
    public void validateCPFReturnTest() {

        boolean validCPF = CPFToolkit.validateCPF("58856187558");
        Assert.assertTrue(validCPF);

        boolean invalidCPF = CPFToolkit.validateCPF("58856187555");
        Assert.assertFalse(invalidCPF);
    }

    /*
    * Testa o lançamento do AssertionError, tentando capturar algum erro em um CPF válido
    * com o método getReason().
    * */
    @Test(expected = AssertionCPFError.class)
    public void AssertionCPFErrorExpectedTest() throws AssertionCPFError {

        boolean validCPF = CPFToolkit.validateCPF("58856187558");
        CPFToolkit.getReason();
    }

    /*
    * Testa o lançamento da exception IllegalCPFPatternException, passando um CPF com padrão de dítitos
    * inválido para o método overloaded getFirstThreeDigits.
    * */
    @Test(expected = IllegalCPFPatternException.class)
    public void IllegalCPFPatternExceptionExpectedTest() throws IllegalCPFPatternException {

        String cpfWithInvalidPattern = CPFToolkit.getFirstThreeDigits("99999999999");
    }

    /*
    * Testa o lançamento da exception IllegalCPFStateException, realizando uma chamada ilegal ao
    * método getFormattedCPF passando um CPF inconsistente.
    * */
    @Test(expected = IllegalCPFStateException.class)
    public void illegalCalledToGetFormattedCPF() throws IllegalCPFStateException {

        boolean invalidCPF = CPFToolkit.validateCPF("58856187555");
        CPFToolkit.getFormattedCPF();
    }

    /*
    * Testa os erros capturados pelo método getReasonValue().
    * */
    @Test
    public void getReasonReturnTest() {

        String nullCPF = null;

        CPFToolkit.validateCPF(nullCPF);
        assertEquals("NULL_CPF", CPFToolkit.getReasonValue());

        String emptyCPF = "";

        CPFToolkit.validateCPF(emptyCPF);
        assertEquals("EMPTY_CPF", CPFToolkit.getReasonValue());

        String cpfWithInvalidPattern = "*/@#&(9065";

        CPFToolkit.validateCPF(cpfWithInvalidPattern);
        assertEquals("INVALID_CPF_DIGITS_PATTERN", CPFToolkit.getReasonValue());

        String invalidCPF = "58856187555";

        CPFToolkit.validateCPF(invalidCPF);
        assertEquals("INVALID_CPF", CPFToolkit.getReasonValue());
    }

    /*
    * Testa o código dos erros capturados pelo método getCode().
    * */
    @Test
    public void getCodeReturnTest() {

        String nullCPF = null;

        CPFToolkit.validateCPF(nullCPF);
        assertEquals(1, CPFToolkit.getCode());

        String emptyCPF = "";

        CPFToolkit.validateCPF(emptyCPF);
        assertEquals(2, CPFToolkit.getCode());

        String cpfWithInvalidPattern = "*/@#&(9065";

        CPFToolkit.validateCPF(cpfWithInvalidPattern);
        assertEquals(3, CPFToolkit.getCode());

        String invalidCPF = "58856187555";

        CPFToolkit.validateCPF(invalidCPF);
        assertEquals(4, CPFToolkit.getCode());
    }

    /*
    * Testa á chamada ao método getSuccessStatus().
    * */
    @Test
    public void getSuccessStatusReturnTest() {

        String validCPF = "58856187558";

        CPFToolkit.validateCPF(validCPF);
        assertEquals("VALID_CPF", CPFToolkit.getSuccessStatusValue());
    }

    /*
    * Testa á chamada ao método getSuccessCode().
    * */
    @Test
    public void getSuccessCodeReturnTest() {

        String validCPF = "58856187558";

        CPFToolkit.validateCPF(validCPF);
        assertEquals(0, CPFToolkit.getSuccessCode());
    }

    /*
    * Testa o lançamento da exception IllegalCPFStateException, realizando uma chamada ilegal ao
    * método getSuccessStatus(), passando um CPF inconsistente.
    * */
    @Test(expected = IllegalCPFStateException.class)
    public void illegalCalledToGetSuccessStatusTest() throws IllegalCPFStateException {

        String invalidCPF = "7775$((*";

        CPFToolkit.validateCPF(invalidCPF);
        CPFToolkit.getSuccessStatus();
    }
}