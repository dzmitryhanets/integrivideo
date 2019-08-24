package calculator;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class DivisionTest {
    Calculator calc;

    @BeforeMethod
    public void calculatorCreation() {
        calc = new Calculator();
    }

    @Test
    public void divisionIsCalculated() {
        Assert.assertEquals(calc.divide(18, 6), 3.0);
    }

}
