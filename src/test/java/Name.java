import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Name {
    public static final String URL = "https://dev.integrivideo.com/demo/chat/new";
    public static final String userName = "TestUser";
    @Test
    public void nameIsSavedTest() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/webdrivers/chromedriver.exe");
        ChromeDriver driver = new ChromeDriver();
        driver.get(URL);
        driver.manage().window().maximize();
        WebElement element = driver.findElement(By.cssSelector(".integri-chat-settings"));
        element.click();
        WebElement nameInput = (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='userName']")));
        nameInput.click();
        nameInput.clear();
        nameInput.sendKeys(userName);
        WebElement saveButton = driver.findElement(By.cssSelector(".integri-user-settings-save"));
        saveButton.click();
        Thread.sleep(1000);
        WebElement actualName = driver.findElement(By.xpath("//div[@class='integri-session-user-name']"));
        String name = actualName.getText();
        Assert.assertEquals(name, userName);
        driver.quit();
    }
}
