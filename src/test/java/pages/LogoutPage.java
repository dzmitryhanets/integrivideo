package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class LogoutPage {
    WebDriver driver;
    WebDriverWait wait;

    public LogoutPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 10);
    }

    String pageURL = "https://dev.integrivideo.com/app/billing";
    private By logoutBtn = By.xpath("//span[@class='iv-icon iv-icon-exit-right']");

    public void logOut() {
        driver.get(pageURL);
        driver.findElement(logoutBtn).click();
        Assert.assertEquals(driver.getCurrentUrl(), "https://dev.integrivideo.com/");
        driver.quit();
    }
}
