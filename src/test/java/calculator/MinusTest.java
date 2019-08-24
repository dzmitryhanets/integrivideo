package calculator;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class MinusTest {
    Calculator calc;

    @BeforeMethod
    public void calculatorCreation() {
        calc = new Calculator();
    }

    @Test
    public void subtractIsCalculatedTest() {
        Assert.assertEquals(calc.minus(7, 5), 2.0);
    }

}
