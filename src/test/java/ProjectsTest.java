import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.LoginPageFactory;
import pages.ProjectsPage;

import java.util.concurrent.TimeUnit;

public class ProjectsTest {
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

    @Test
    public void newProjectShouldBeAdded() {
        ProjectsPage page = new ProjectsPage(driver);
        page
                .getProjectsPage()
                .clickAddProject()
                .inputProjectFields("test", "test", "test.com")
                .createProject()
                .verifyProjects(4);
    }

    @Test
    public void projectShouldBeEdited() {
        ProjectsPage page = new ProjectsPage(driver);
        page
                .getProjectsPage()
                .clickCreatedProject(1)
                .getEditProjectForm()
                .inputProjectFields("New edited", "Edited", "Edited.com")
                .createProject()
                .verifyEditedProject(1, "NE");
    }

    @Test
    public void componentShouldBeAdded() {
        ProjectsPage page = new ProjectsPage(driver);
        page
                .getProjectsPage()
                .clickCreatedProject(2)
                .clickAddComponent()
                .inputComponentForm(2, "name")
                .getProjectsPage()
                .clickCreatedProject(2)
                .verifyComponent(20);
    }

    @Test
    public void componentShouldBeEdited() {
        ProjectsPage page = new ProjectsPage(driver);
        page
                .getProjectsPage()
                .clickCreatedProject(2)
                .getComponent(2)
                .editComponent("edited name")
                .getComponent(2)
                .verifyEditedComponent("edited name");
    }
}
