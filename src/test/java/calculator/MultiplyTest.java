package calculator;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class MultiplyTest {
    Calculator calc;

    @BeforeMethod
    public void calculatorCreation() {
        calc = new Calculator();
    }

    @Test
    public void multiplyingIsCalculated() {
        Assert.assertEquals(calc.multiply(2, 7), 14.0);
    }

}
