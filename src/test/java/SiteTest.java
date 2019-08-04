import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SiteTest {
    public static final String URL = "https://dev.integrivideo.com/demo/chat/new";
    @Test
    public void siteShouldBeReachedTest() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/webdrivers/chromedriver.exe");
        ChromeDriver driver = new ChromeDriver();
        driver.get(URL);
        driver.manage().window().maximize();
        WebElement element = driver.findElement(By.xpath("//div[@class='integri-chat-sessions']"));
        Assert.assertTrue(element.isDisplayed());
        driver.quit();
    }
}
