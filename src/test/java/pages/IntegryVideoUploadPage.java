package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.File;
import java.util.List;

public class IntegryVideoUploadPage{
    WebDriver driver;
    WebDriverWait wait;
    private By dragAndDropBtn = By.cssSelector(".integri-chat-manual-upload");
    private By uploadModal = By.cssSelector(".integri-file-upload");
    private By inputFile = By.xpath("//input[@class='integri-hide']");
    private By uploadStartBtn = By.cssSelector(".integri-file-upload-start");
    private By fileToAttach = By.cssSelector(".integri-file-item-name");
    private By loader = By.xpath("//*[contains(text(),'Upload files')]");
    private By attachedFileName = By.cssSelector(".integri-chat-message-attachment-file-name");

    public IntegryVideoUploadPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 10);
    }

    public void clickDragAndDrop(){
        driver.findElement(dragAndDropBtn).click();
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(uploadModal)));
    }

    public void startFileInput(String filePath){
        File file = new File(filePath);
        String path = file.getAbsolutePath();
        driver.findElement(inputFile).sendKeys(path);
        wait.until(ExpectedConditions.presenceOfElementLocated(fileToAttach));
    }

    public void uploadFile() {
        driver.findElement(uploadStartBtn).click();
        wait.until(ExpectedConditions.invisibilityOf(driver.findElement(loader)));
    }

    public void verifyAttachedFile(String expectedFileName){
        String actualFileName = driver.findElement(attachedFileName).getText();
        Assert.assertTrue(actualFileName.contains(expectedFileName));
    }

    public void verifyMultipleAttachments(int expectedNumber){
        List<WebElement> uploadFiles = driver.findElements(attachedFileName);
        int actualNumber = uploadFiles.size();
        Assert.assertEquals(actualNumber, expectedNumber);
    }

}
