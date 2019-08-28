package calculator;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class MultiplyTest {
    Calculator calc;

    @BeforeMethod
    public void calculatorCreation() {
        calc = new Calculator();
    }

    @Test(priority = 2)
    public void multiplyingIsCalculated() {
        Assert.assertEquals(calc.multiply(2, 7), 14.0);
    }

    @Test(priority = 1)
    public void multiplyingByZeroIsCalculated() {
        Assert.assertEquals(calc.multiply(0, 7), 0.0);
    }

    @AfterMethod
    public void calculatorRemoving() {
        calc = null;
    }
}
