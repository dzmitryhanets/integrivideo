package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class LoginPageFactory {
    WebDriver driver;
    WebDriverWait wait;

    String URL = "https://dev.integrivideo.com/login";
    @FindBy(name = "email")
    private WebElement emailField;
    @FindBy(name = "password")
    private  WebElement passwordField;
    @FindBy(xpath = "//button[@class = 'btn btn-primary']")
    private  WebElement loginBtn;
    @FindBy(xpath = "//*[contains(text(),'Projects')]")
    private WebElement openedPage;

    public LoginPageFactory(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 10);
        PageFactory.initElements(driver, this);
    }

    public LoginPageFactory openPage(){
        driver.get(URL);
        return this;
    }

    public LoginPageFactory login(String userEmail, String password) {
        emailField.sendKeys(userEmail);
        passwordField.sendKeys(password);
        loginBtn.click();
        wait.until(ExpectedConditions.textToBe(By.xpath("//*[contains(text(),'Projects')]"), "Projects"));
        return this;
    }

    public ProjectsPage verifyLoginSuccess(String expectedPage) {
        Assert.assertEquals(openedPage.getText(), expectedPage);
        return new ProjectsPage();
    }
}
