package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class IntegriVideoChatPage {
    public static final String URL = "https://dev.integrivideo.com/demo/chat/new";
    WebDriver driver;
    By inviteLink = By.id("invite-users-to-chat");
    By textArea = By.xpath("//*[@placeholder='Start typing here']");
    By sendMessageButton = By.xpath("//*[@title='Send message']");

    public IntegriVideoChatPage(WebDriver driver) {
        this.driver = driver;
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
}
