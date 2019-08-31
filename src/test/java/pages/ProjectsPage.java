package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class ProjectsPage {
    WebDriver driver;
    WebDriverWait wait;

    String projectURL = "https://dev.integrivideo.com/app/projects";
    private By addProjectIcon = By.xpath("//a[@href='/app/projects/new']");
    private By button = By.xpath("//button[@class='btn']");
    private By nameInputField = By.xpath("//input[@name='name']");
    private By descriptionTextField = By.xpath("//textarea[@name='description']");
    private By domainsInputField = By.xpath("//input[@name='domains[]'][1]");
    private By addedProject = By.xpath("//div[@class='project']");
    private By editLink = By.xpath("//div[@class='col-2 actions']//a[contains(text(),'Edit')]");
    private By circleTitle = By.xpath("//div[@class='circle']");
    private By addComponentBtn = By.xpath("//div[@class='status']");
    private By componentTypeSelect = By.xpath("//option");
    private By componentNameInput = By.xpath("//input[@placeholder='New component']");
    private By component = By.xpath("//div[@class='component']");
    private By linkToComponents = By.xpath("//a[@class='nav-link']");

    public ProjectsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 10);
    }

    public ProjectsPage clickAddProject() {
        driver.findElement(addProjectIcon).click();
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(button)));
        return this;
    }

    public ProjectsPage inputProjectFields(String name, String description, String domain) {
        driver.findElement(nameInputField).click();
        driver.findElement(nameInputField).clear();
        driver.findElement(nameInputField).sendKeys(name);
        driver.findElement(descriptionTextField).click();
        driver.findElement(descriptionTextField).clear();
        driver.findElement(descriptionTextField).sendKeys(description);
        driver.findElement(domainsInputField).click();
        driver.findElement(domainsInputField).clear();
        driver.findElement(domainsInputField).sendKeys(domain);
        return this;
    }

    public ProjectsPage createProject() {
        driver.findElement(button).click();
        return this;
    }

    public ProjectsPage verifyProjects(int expectedCount) {
        Assert.assertEquals(driver.findElements(addedProject).size(), expectedCount);
        return this;
    }

    public ProjectsPage clickCreatedProject(int projectNumber) {
        driver.findElements(addedProject).get(projectNumber - 1).click();
        return this;
    }

    public ProjectsPage getEditProjectForm() {
        driver.findElement(editLink).click();
        return this;
    }

    public ProjectsPage verifyEditedProject(int projectNumber, String expectedName) {
        String actualName = driver.findElements(circleTitle).get(projectNumber - 1).getText();
        Assert.assertEquals(actualName, expectedName);
        return this;
    }

    public ProjectsPage clickAddComponent() {
        driver.findElement(addComponentBtn).click();
        return this;
    }

    public ProjectsPage inputComponentForm(int option, String name) {
        driver.findElements(componentTypeSelect).get(option - 1).click();
        driver.findElement(componentNameInput).click();
        driver.findElement(componentNameInput).clear();
        driver.findElement(componentNameInput).sendKeys(name);
        createProject();
        wait.until(ExpectedConditions.textToBe(button, "Update"));
        return this;
    }

    public ProjectsPage getProjectsPage() {
        driver.navigate().to(projectURL);
        return this;
    }

    public ProjectsPage verifyComponent(int componentsCount) {
        driver.findElements(linkToComponents).get(1).click();
        Assert.assertEquals(driver.findElements(component).size(), componentsCount);
        return this;
    }
}
