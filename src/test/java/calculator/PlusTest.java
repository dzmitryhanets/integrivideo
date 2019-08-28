package calculator;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class PlusTest {
    Calculator calc;

    @BeforeMethod
    public void calculatorCreation() {
        calc = new Calculator();
    }

    @Test(retryAnalyzer = Retry.class, threadPoolSize = 30, invocationCount = 100)
    public void sumIsCalculated() {
        Assert.assertEquals(1, 99, 100.0);
    }

    @Test(retryAnalyzer = Retry.class, threadPoolSize = 3, invocationCount = 10)
    public void sumNegativeIsCalculated() {
        Assert.assertEquals(-1, -101, 100.0);
    }

    @AfterMethod
    public void calculatorRemoving() {
        calc = null;
    }
}
