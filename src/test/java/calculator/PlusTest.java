package calculator;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class PlusTest {
    Calculator calc;

    @BeforeMethod
    public void calculatorCreation() {
        calc = new Calculator();
    }

    @Test
    public void sumIsCalculated() {
        Assert.assertEquals(1, 99, 100.0);
    }

}
