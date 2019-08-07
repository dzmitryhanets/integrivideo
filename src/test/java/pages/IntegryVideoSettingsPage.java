package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class IntegryVideoSettingsPage {
    WebDriver driver;
    WebDriverWait wait;
    private By settingsBtn = By.cssSelector(".integri-chat-settings");
    private By settingsCrossBtn = By.xpath("//*[contains(text(),'Settings')]//*[contains(@class,'close-integri-modal')]");
    private By settingsModal = By.xpath("//div[@class='integri-modal-head']");
    private By nameInput = By.xpath("//input[@name='userName']");
    private By saveBtn = By.cssSelector(".integri-user-settings-save");
    private By sessionName = By.xpath("//div[@class='integri-session-user-name']");
    private By inputEmail = By.xpath("//input[@name='userEmail']");

    public IntegryVideoSettingsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 10);
    }

    public void clickSettingsBtn(){
        driver.findElement(settingsBtn).click();
    }

    public void closeSettingsModal(){
        driver.findElement(settingsCrossBtn).click();
        Assert.assertTrue(!driver.findElement(settingsModal).isDisplayed());
    }

    public void insertName(String name){
        driver.findElement(nameInput).click();
        driver.findElement(nameInput).clear();
        driver.findElement(nameInput).sendKeys(name);
    }

    public void saveForm(){
        driver.findElement(saveBtn).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(settingsModal));
    }

    public void verifyName(String expectedName){
        wait.until(ExpectedConditions.textToBe(sessionName,expectedName));
        String actualName = driver.findElement(sessionName).getText();
        Assert.assertEquals(actualName, expectedName);
    }

    public void insertEmail(String email){
        driver.findElement(inputEmail).click();
        driver.findElement(inputEmail).sendKeys(email);
    }

    public void verifyEmail(String expectedEmail){
        saveForm();
        String actualEmail = driver.findElement(inputEmail).getAttribute("value");
        Assert.assertEquals(actualEmail, expectedEmail);
    }

}
