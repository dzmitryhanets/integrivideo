import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import pages.BillingPage;
import pages.LoginPageFactory;
import pages.LogoutPage;

import java.util.concurrent.TimeUnit;

public class BaseTest {
    WebDriver driver;

    @BeforeClass
    public void openDriver(){
        System.setProperty("webdriver.chrome.driver", "src/test/resources/webdrivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        LoginPageFactory loginPage = new LoginPageFactory(driver);
        loginPage
                .openPage()
                .login("tms4@mailinator.com", "Password01")
                .verifyLoginSuccess("Projects");
    }

    @AfterClass
    public void closeDriver() {
        LogoutPage page = new LogoutPage(driver);
        page
                .logOut();
    }
}
