package calculator;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static jdk.nashorn.internal.objects.Global.Infinity;

public class DivisionTest {
    Calculator calc;

    @BeforeMethod
    public void calculatorCreation() {
        calc = new Calculator();
    }

    @Test
    public void divisionByZeroIsCalculated() {
        Assert.assertEquals(calc.divide(18, 0), Infinity);
    }

    @Test(retryAnalyzer = Retry.class, threadPoolSize = 3, invocationCount = 100)
    public void divisionIsCalculated() {
        Assert.assertEquals(calc.divide(18, 6), 3.0);
    }

    @Test(threadPoolSize = 10, invocationCount = 5)
    public void divisionZeroByNumberIsCalculated() {
        Assert.assertEquals(calc.divide(0, 6), 0.0);
    }

    @AfterMethod
    public void calculatorRemoving() {
        calc = null;
    }
}
