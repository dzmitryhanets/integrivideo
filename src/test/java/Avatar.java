import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Avatar {
    public static final String URL = "https://dev.integrivideo.com/demo/chat/new";
    public static final String avatar = "https://static.tvtropes.org/pmwiki/pub/images/stormtroopers_chase_han_and_chewie_7d44b7c5.jpeg";
    @Test
    public void avatarIsDisplayedTest() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/webdrivers/chromedriver.exe");
        ChromeDriver driver = new ChromeDriver();
        driver.get(URL);
        driver.manage().window().maximize();
        WebElement element = (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.elementToBeClickable(By.cssSelector(".integri-chat-settings")));
        element.click();
        WebElement imageInput = (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='userPic']")));
        imageInput.click();
        imageInput.sendKeys(avatar);
        WebElement saveButton = driver.findElement(By.cssSelector(".integri-user-settings-save"));
        saveButton.click();
        Thread.sleep(1000);
        WebElement actualAvatar = driver.findElement(By.xpath("//div[@class='integri-chat-session']/div"));
        String url = actualAvatar.getAttribute("style");
        Assert.assertTrue(url.contains(avatar));
        driver.quit();
    }
}
