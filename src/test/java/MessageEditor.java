import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class MessageEditor {
    public static final String URL = "https://dev.integrivideo.com/demo/chat/new";
    public static final String text = "Test message";
    public static final String editedText = "This text is edited";
    @Test
    public void messageIsEditedTest() {
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
        WebElement editButton = (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[@class='iv-icon iv-icon-pencil integri-chat-edit-message']")));
        editButton.click();
        WebElement editArea = driver.findElement(By.xpath("//div[@class='integri-chat-message ']/textarea"));
        editArea.click();
        editArea.clear();
        editArea.sendKeys(editedText);
        editArea.sendKeys(Keys.ENTER);
        JavascriptExecutor js = (JavascriptExecutor)driver;
        WebElement displayedMessage = (WebElement) js.executeScript("return jQuery.find('.integri-chat-message-text');");
        /*WebElement displayedMessage = (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='integri-chat-message-text']")));*/
        String displayedText = displayedMessage.getText();
        Assert.assertEquals(displayedText, editedText);
        driver.quit();
    }
}
