import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class EmptyChat {
    public static final String URL = "https://dev.integrivideo.com/demo/chat/new";
    public static final String text = "Test message";
    @Test
    public void messageIsDeletedTest() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/webdrivers/chromedriver.exe");
        ChromeDriver driver = new ChromeDriver();
        driver.get(URL);
        driver.manage().window().maximize();
        WebElement element = (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//textarea")));
        element.click();
        element.sendKeys(text);
        WebElement sendButton = driver.findElement(By.xpath("//span[@class='iv-icon iv-icon-paper-plane']"));
        sendButton.click();
        WebElement deleteButton = (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[@class='iv-icon iv-icon-trash2 integri-chat-remove-message']")));
        deleteButton.click();
        Alert alert = driver.switchTo().alert();
        alert.accept();
        Thread.sleep(1000);
        WebElement displayedMessage = driver.findElement(By.xpath("//div[@class='integri-chat-message-text']"));
        String displayedText = displayedMessage.getText();
        Assert.assertEquals(displayedText, "removed...");
        driver.quit();
    }
}
