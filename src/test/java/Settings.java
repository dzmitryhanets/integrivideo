import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Settings {
    public static final String URL = "https://dev.integrivideo.com/demo/chat/new";
    @Test
    public void settingsModalIsDisplayedTest() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/webdrivers/chromedriver.exe");
        ChromeDriver driver = new ChromeDriver();
        driver.get(URL);
        driver.manage().window().maximize();
        WebElement element = driver.findElement(By.cssSelector(".integri-chat-settings"));
        element.click();
        WebElement settings =  (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".integri-modal-shown")));
        Assert.assertTrue(settings.isDisplayed());
        driver.quit();
    }
}
