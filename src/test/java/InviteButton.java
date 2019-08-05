import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

public class InviteButton {
    public static final String URL = "https://dev.integrivideo.com/demo/chat/new";
    @Test
    public void inviteLinkIsCopiedTest() throws IOException, UnsupportedFlavorException {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/webdrivers/chromedriver.exe");
        ChromeDriver driver = new ChromeDriver();
        driver.get(URL);
        driver.manage().window().maximize();
        String link1 = driver.getCurrentUrl();
        WebElement element = driver.findElement(By.id("invite-users-to-chat"));
        element.click();
        String link2 = (String) Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor);
        Assert.assertEquals(link1, link2);
        driver.quit();
    }
}
