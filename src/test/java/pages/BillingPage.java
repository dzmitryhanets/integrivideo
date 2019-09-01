package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.concurrent.TimeUnit;

public class BillingPage {
    WebDriver driver;
    WebDriverWait wait;

    public BillingPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 10);
    }

    String billingURL = "https://dev.integrivideo.com/app/billing";
    private By addNewBtn = By.xpath("//*[contains(text(),'Add new')]");
    private By cardInput = By.xpath("//input[@class='form-control']");
    private By addCardBtn = By.xpath("//button[@class='btn']");
    private By errorMsg = By.xpath("//span[@data-notify='message']");
    private By payPalBtn = By.xpath("//div[@data-funding-source='paypal']");

    public BillingPage openBillingPage() {
        driver.get(billingURL);
        return this;
    }

    public BillingPage clickAddBtn() {
        driver.findElement(addNewBtn).click();
        return this;
    }

    public BillingPage inputCardParameters(String cardNumber, String month, String year, String name) {
        driver.findElements(cardInput).get(0).click();
        driver.findElements(cardInput).get(0).sendKeys(cardNumber);
        driver.findElements(cardInput).get(1).click();
        driver.findElements(cardInput).get(1).sendKeys(month);
        driver.findElements(cardInput).get(2).click();
        driver.findElements(cardInput).get(2).sendKeys(year);
        driver.findElements(cardInput).get(3).click();
        driver.findElements(cardInput).get(3).sendKeys(name);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        return this;
    }

    public BillingPage clackAddCardBtn() {
        driver.findElement(addCardBtn).click();
        return this;
    }

    public BillingPage verifyInvalidCard() {
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(errorMsg)));
        String message = driver.findElement(errorMsg).getText();
        Assert.assertEquals(message, "Credit card is invalid");
        return this;
    }

    public BillingPage clickPayPalBtn() {
        driver.findElement(payPalBtn).click();
        return this;
    }


}
