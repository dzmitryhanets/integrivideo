import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Email {
    public static final String URL = "https://dev.integrivideo.com/demo/chat/new";
    public static final String email = "some@mail.com";
    @Test
    public void emailIsSavedTest() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/webdrivers/chromedriver.exe");
        ChromeDriver driver = new ChromeDriver();
        driver.get(URL);
        driver.manage().window().maximize();
        WebElement element = (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.elementToBeClickable(By.cssSelector(".integri-chat-settings")));
        element.click();
        WebElement emailInput = (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='userEmail']")));
        emailInput.click();
        emailInput.clear();
        emailInput.sendKeys(email);
        WebElement saveButton = driver.findElement(By.cssSelector(".integri-user-settings-save"));
        saveButton.click();
        Thread.sleep(1000);
        element.click();
        Assert.assertEquals(emailInput.getAttribute("value"), email);
        driver.quit();
    }
}
