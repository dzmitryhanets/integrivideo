import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.IntegriVideoChatPage;
import java.util.concurrent.TimeUnit;

public class TestsExecutor {
    WebDriver driver;
    @BeforeTest
    public void openDriver(){
        System.setProperty("webdriver.chrome.driver", "src/test/resources/webdrivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }
    @Test
    public void messageIsDisplayedTest() {
        IntegriVideoChatPage chat = new IntegriVideoChatPage(driver);
        chat.openPage();
        chat.inputText("test");
        chat.sendText();
        chat.verifyText("test");
    }

    @Test
    public void editedMessageIsDisplayedTest() {
        IntegriVideoChatPage chat = new IntegriVideoChatPage(driver);
        chat.openPage();
        chat.inputText("test");
        chat.sendText();
        chat.clickEditMessageBtn("Edited Text");
        chat.verifyText("Edited Text");
    }

    @Test
    public void inviteLinkIsCorrect() {
        IntegriVideoChatPage chat = new IntegriVideoChatPage(driver);
        chat.openPage();
        chat.clickInvite();
        chat.verifyInviteLink(driver.getCurrentUrl());
    }

    @Test
    public void messageIsDeleted() {
        IntegriVideoChatPage chat = new IntegriVideoChatPage(driver);
        chat.openPage();
        chat.inputText("test");
        chat.sendText();
        chat.removeMessage();
        chat.verifyText("removed...");
    }

    @Test
    public void scriptIsCorrect() {
        IntegriVideoChatPage chat = new IntegriVideoChatPage(driver);
        chat.openPage();
        chat.clickScript();
        chat.verifyScript(chat.getCodeText());
    }

    @AfterTest
    public void closeDriver () {
        driver.quit();
        }

    }
