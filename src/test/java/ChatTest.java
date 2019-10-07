import io.qameta.allure.Description;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.IntegriVideoChatPage;
import pages.IntegryVideoSettingsPage;
import pages.IntegryVideoUploadPage;
import pages.LoginPageFactory;
import utils.AllureUtils;

import java.util.concurrent.TimeUnit;

public class ChatTest {
    WebDriver driver;
    @BeforeTest
    public void openDriver(){
        System.setProperty("webdriver.chrome.driver", "src/test/resources/webdrivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test (description = "Отправить любое сообщение используя Кнопку")
    @Description ("Проверка отправления сообщения при нажатии кнопки Send")
    public void messageIsDisplayedTest() {
        IntegriVideoChatPage chat = new IntegriVideoChatPage(driver);
        chat.openPage();
        chat.inputText("test");
        chat.sendText();
        chat.verifyText(1, "test");
        AllureUtils.takeScreenshot(driver);
    }

    @Test (description = "Отправить любое сообщение используя Enter")
    public void messageIsSentByEnterTest() {
        IntegriVideoChatPage chat = new IntegriVideoChatPage(driver);
        chat.openPage();
        chat.inputText("test");
        chat.sendTextWithEnter();
        chat.verifyText(1, "test");
    }

    @Test (description = "Отправить сообщение из 1000 символов")
    public void longMessageIsDisplayedTest() {
        IntegriVideoChatPage chat = new IntegriVideoChatPage(driver);
        chat.openPage();
        chat.inputAndVerifyLongText(1, 1000);
    }

    @Test (description = "Отправить сообщение со ссылкой")
    public void linkMessageIsDisplayedTest(){
        IntegriVideoChatPage chat = new IntegriVideoChatPage(driver);
        chat.openPage();
        chat.inputText("https://tut.by");
        chat.sendText();
        chat.verifyLinkMsg("https://tut.by");
    }

    @Test (description = "Отправить сообщение содержащее Javascript code")
    public void injectionMessageIsDisplayedTest() {
        IntegriVideoChatPage chat = new IntegriVideoChatPage(driver);
        chat.openPage();
        chat.inputText("<html><body><p>test</p></body></html>");
        chat.sendText();
        chat.verifyText(1, "test");
    }

    @Test (description = "Отредактировать сообщение")
    public void editedMessageIsDisplayedTest() {
        IntegriVideoChatPage chat = new IntegriVideoChatPage(driver);
        chat.openPage();
        chat.inputText("test");
        chat.sendText();
        chat.editMessageBtn("Edited Text");
        chat.verifyText(1, "Edited Text");
    }

    @Test (description = "Отредактировать сообщение удалив весь текст")
    public void emptyEditedMessageErrorTest(){
        IntegriVideoChatPage chat = new IntegriVideoChatPage(driver);
        chat.openPage();
        chat.inputText("test");
        chat.sendText();
        chat.editMessageBtn("");
        chat.verifyEmptyTextErrorMsg();
    }

    @Test (description = "Удалить сообщение")
    public void messageIsDeleted() {
        IntegriVideoChatPage chat = new IntegriVideoChatPage(driver);
        chat.openPage();
        chat.inputText("test");
        chat.sendText();
        chat.removeMessage();
        chat.verifyText(1, "removed...");
    }

    @Test (description = "Отправить 11 сообщений")
    public void trialMessageIsDisplayedTest(){
        IntegriVideoChatPage chat = new IntegriVideoChatPage(driver);
        chat.openPage();
        chat.sendMultipleMessages(11, "test");
        chat.verifyTrialMsgText();
    }

    @Test (description = "Изменить имя")
    public void nameIsSaved(){
        IntegriVideoChatPage chat = new IntegriVideoChatPage(driver);
        chat.openPage();
        IntegryVideoSettingsPage settings = new IntegryVideoSettingsPage(driver);
        settings.clickSettingsBtn();
        settings.insertName("Valera");
        settings.saveForm();
        settings.verifyName("Valera");
    }

    @Test (description = "Изменить email")
    public void emailIsSaved(){
        IntegriVideoChatPage chat = new IntegriVideoChatPage(driver);
        chat.openPage();
        IntegryVideoSettingsPage settings = new IntegryVideoSettingsPage(driver);
        settings.clickSettingsBtn();
        settings.insertEmail("test@mail.com");
        settings.verifyEmail("test@mail.com");
    }

    @Test (description = "Изменить Photo URL")
    public void avatarIsSaved(){
        IntegriVideoChatPage chat = new IntegriVideoChatPage(driver);
        chat.openPage();
        IntegryVideoSettingsPage settings = new IntegryVideoSettingsPage(driver);
        settings.clickSettingsBtn();
        settings.insertAvatar("https://static.tvtropes.org/pmwiki/pub/images/stormtroopers_chase_han_and_chewie_7d44b7c5.jpeg");
        settings.saveForm();
        settings.verifyAvatar("https://static.tvtropes.org/pmwiki/pub/images/stormtroopers_chase_han_and_chewie_7d44b7c5.jpeg");
    }


    @Test (description = "Проверить работу пноки Invite")
    public void inviteLinkIsCorrect(){
        IntegriVideoChatPage chat = new IntegriVideoChatPage(driver);
        chat.openPage();
        chat.clickInvite();
        chat.verifyInviteLink(driver.getCurrentUrl());
    }

    @Test (description = "Проверить работу кнопки с кодом")
    public void scriptIsCorrect(){
        IntegriVideoChatPage chat = new IntegriVideoChatPage(driver);
        chat.openPage();
        chat.clickScript();
        chat.verifyScript(chat.getCodeText());
    }

    @Test (description = "Загрузить 1 файл")
    public void uploadFileTest(){
        IntegriVideoChatPage chat = new IntegriVideoChatPage(driver);
        chat.openPage();
        IntegryVideoUploadPage uploadPage = new IntegryVideoUploadPage(driver);
        uploadPage.clickDragAndDrop();
        uploadPage.startFileInput("src/test/resources/files/test.txt");
        uploadPage.uploadFile();
        uploadPage.verifyAttachedFile("test.txt");
    }

    @Test (description = "Загрузить 2 файла")
    public void uploadFewFilesTest(){
        IntegriVideoChatPage chat = new IntegriVideoChatPage(driver);
        chat.openPage();
        IntegryVideoUploadPage uploadPage = new IntegryVideoUploadPage(driver);
        uploadPage.clickDragAndDrop();
        uploadPage.startFileInput("src/test/resources/files/test.txt");
        uploadPage.startFileInput("src/test/resources/files/test.txt");
        uploadPage.uploadFile();
        uploadPage.verifyMultipleAttachments(2);
    }

    @Test
    public void settingsModalIsClosed(){
        IntegriVideoChatPage chat = new IntegriVideoChatPage(driver);
        chat.openPage();
        IntegryVideoSettingsPage settings = new IntegryVideoSettingsPage(driver);
        settings.clickSettingsBtn();
        settings.closeSettingsModal();
    }

    @Test (description = "")
    public void additionalTest() {
        IntegriVideoChatPage chat = new IntegriVideoChatPage(driver);
        chat.openPage();
        chat.sendMultipleMessages(3, "test");
        chat.editAnyMessageBtn(2, "Edited Text");
        chat.verifyText(2, "Edited Text");
        chat.removeListMessage(3);
    }

    @AfterTest
    public void closeDriver(){
        driver.quit();
    }

}
