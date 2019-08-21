package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.util.List;

public class IntegriVideoChatPage {
    WebDriver driver;
    WebDriverWait wait;
    public static final String URL = "https://dev.integrivideo.com/demo/chat/new";
    private By inviteLink = By.id("invite-users-to-chat");
    private By textArea = By.xpath("//*[@placeholder='Start typing here']");
    private By sendMessageButton = By.xpath("//*[@title='Send message']");
    private By editButton = By.xpath("//span[@class='iv-icon iv-icon-pencil integri-chat-edit-message']");
    private By editArea = By.xpath("//div[@class='integri-chat-message ']/textarea");
    private By message = By.cssSelector(".integri-chat-message-text");
    private By deleteButton = By.cssSelector((".integri-chat-remove-message"));
    private By scriptCode = By.xpath("//code");
    private By linkTextMsg = By.xpath("//*[@class='integri-chat-message-text']//a[@target='_blank']");
    private By emptyEditedTextMsg = By.cssSelector(".integri-notify-error");
    private By trialMessageScreen = By.xpath("//div[@class='sign-up']/..");

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

    public void inputAndVerifyLongText(int messageNumber,int stringLength) {
        String text = "A";
        for (int i = 1; i < stringLength; i++){
            text = text + "A";
        }
        driver.findElement(textArea).sendKeys(text);
        sendText();
        verifyText(messageNumber, text);
    }

    public void sendText(){
        driver.findElement(sendMessageButton).click();
    }

    public void sendTextWithEnter(){
        driver.findElement(textArea).sendKeys(Keys.ENTER);
    }

    public void verifyText(int messageNumber, String expectedString) {
        List<WebElement> messages = driver.findElements(message);
        String actualString = messages.get(messageNumber - 1).getText();
        Assert.assertEquals(actualString, expectedString);
    }

    public void verifyLinkMsg(String expectedLinkMsg) {
        wait.until(ExpectedConditions.textToBe(linkTextMsg, expectedLinkMsg));
        String actualLinkMsg = driver.findElement(linkTextMsg).getText();
        Assert.assertEquals(actualLinkMsg, expectedLinkMsg);
    }

    public void verifyEmptyTextErrorMsg() {
        String errorMessage = "Message cannot be empty!";
        wait.until(ExpectedConditions.textToBe(emptyEditedTextMsg, errorMessage));
        String actualErrorMsg = driver.findElement(emptyEditedTextMsg).getText();
        Assert.assertEquals(actualErrorMsg, errorMessage);
    }

    public void sendMultipleMessages(int messagesCount, String text) {
        for (int i = 1; i <= messagesCount; i++){
            inputText(text);
            sendTextWithEnter();
            if (i == 11){
                break;
            }
            wait.until(ExpectedConditions.numberOfElementsToBe(message, i));
        }
    }

    public void verifyTrialMsgText() {
        String trialMsg = "This is trial version";
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(trialMessageScreen)));
        String actualTrialMsg = driver.findElement(trialMessageScreen).getText();
        Assert.assertTrue(actualTrialMsg.contains(trialMsg));
    }

    public void clickInvite(){
        driver.findElement(inviteLink).click();
    }

    public void verifyInviteLink(String expectedLink) {
        try {
            String actualLink = (String) Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor);
            Assert.assertEquals(actualLink, expectedLink);
        } catch (UnsupportedFlavorException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void editMessageBtn(String editedText) {
        driver.findElement(editButton).click();
        driver.findElement(editArea).click();
        driver.findElement(editArea).clear();
        driver.findElement(editArea).sendKeys(editedText);
        driver.findElement(editArea).sendKeys(Keys.ENTER);
        wait.until(ExpectedConditions.textToBe(message, editedText));
    }

    public void editAnyMessageBtn(int messageNumber, String editedText) {
        driver.findElements(editButton).get(messageNumber - 1).click();
        driver.findElement(editArea).click();
        driver.findElement(editArea).clear();
        driver.findElement(editArea).sendKeys(editedText);
        driver.findElement(editArea).sendKeys(Keys.ENTER);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(editArea));
    }

    public void removeMessage() {
        driver.findElement(deleteButton).click();
        Alert alert = driver.switchTo().alert();
        alert.accept();
        wait.until(ExpectedConditions.textToBe(message, "removed..."));
    }

    public void clickScript(){
        driver.findElement(scriptCode).click();
    }

    public String getCodeText(){
        return driver.findElement(scriptCode).getText().replace("\n", "").replace("\r", "");
    }

    public void verifyScript(String expectedScript) {
        try {
            String actualScript = (String) Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor);
            Assert.assertEquals(actualScript, expectedScript);
        } catch (UnsupportedFlavorException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void removeListMessage(int messageIndex) {
        List<WebElement> messages = driver.findElements(message);
        driver.findElements(deleteButton).get(messageIndex - 1).click();
        Alert alert = driver.switchTo().alert();
        alert.accept();
        //wait.until(ExpectedConditions.textToBe(message, "removed..."));
    }

}
