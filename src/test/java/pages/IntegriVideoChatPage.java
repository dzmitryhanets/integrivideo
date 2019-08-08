package pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

public class IntegriVideoChatPage {
    public static final String URL = "https://dev.integrivideo.com/demo/chat/new";
    WebDriver driver;
    WebDriverWait wait;
    By inviteLink = By.id("invite-users-to-chat");
    By textArea = By.xpath("//*[@placeholder='Start typing here']");
    By sendMessageButton = By.xpath("//*[@title='Send message']");
    By editButton = By.xpath("//span[@class='iv-icon iv-icon-pencil integri-chat-edit-message']");
    By editArea = By.xpath("//div[@class='integri-chat-message ']/textarea");
    By message = By.cssSelector(".integri-chat-message-text");
    By deleteButton = By.cssSelector((".integri-chat-remove-message"));
    By scriptCode = By.xpath("//code");

    public IntegriVideoChatPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 10);
    }

    public void openPage(){
        driver.get(URL);
    }

    public void inputText(String text){
        driver.findElement(textArea).sendKeys(text);
    }

    public void sendText(){
        driver.findElement(sendMessageButton).click();
    }

    public void sendTextWithEnter(){
        driver.findElement(textArea).sendKeys(Keys.ENTER);
    }

    public void verifyText(String expectedString){
        String actualString = driver.findElement(message).getText();
        Assert.assertEquals(actualString, expectedString);
    }

    public void clickInvite(){
        driver.findElement(inviteLink).click();
    }

    public void verifyInviteLink(String expectedLink){
        try {
            String actualLink = (String) Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor);
            Assert.assertEquals(actualLink, expectedLink);
        } catch (UnsupportedFlavorException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void clickEditMessageBtn(String editedText){
        driver.findElement(editButton).click();
        driver.findElement(editArea).click();
        driver.findElement(editArea).clear();
        driver.findElement(editArea).sendKeys(editedText);
        driver.findElement(editArea).sendKeys(Keys.ENTER);
        wait.until(ExpectedConditions.textToBe(message, editedText));
    }

    public void removeMessage(){
        driver.findElement(deleteButton).click();
        Alert alert = driver.switchTo().alert();
        alert.accept();
        wait.until(ExpectedConditions.textToBe(message, "removed..."));
    }

    public void clickScript(){
        driver.findElement(scriptCode).click();
    }

    public String getCodeText() {
        return driver.findElement(scriptCode).getText().replace("\n", "").replace("\r", "");
    }

    public void verifyScript(String expectedScript){
        try {
            String actualScript = (String) Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor);
            Assert.assertEquals(actualScript, expectedScript);
        } catch (UnsupportedFlavorException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
