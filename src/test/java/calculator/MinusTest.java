package calculator;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class MinusTest {
    Calculator calc;

    @BeforeMethod
    public void calculatorCreation() {
        calc = new Calculator();
    }

    @Test(description = "check subtraction calculation")
    public void subtractIsCalculatedTest() {
        Assert.assertEquals(calc.minus(7, 5), 2.0);
    }

    @Test(dataProvider="subtraction")
    public void fewSubtractionsAreCalculated(double minuend, double subtrahend, double expectedResult) {
        Assert.assertEquals(calc.minus(minuend, subtrahend), expectedResult);
    }

    @Test(description = "check subtraction calculation")
    public void subtractNegativeIsCalculatedTest() {
        Assert.assertEquals(calc.minus(-7, -5), -2.0);
    }

    @DataProvider
    public Object[][] subtraction() {
        return new Object[][]{
                {10, 0, 10},
                {5, 1, 4},
                {0, 10, -10}
        };
    }

    @AfterMethod
    public void calculatorRemoving() {
        calc = null;
    }
}
