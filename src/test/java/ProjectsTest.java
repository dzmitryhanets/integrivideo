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
                .clickAddProject()
                .inputProjectFields("test", "test", "test.com")
                .createProject()
                .verifyProjects(3);
    }

    @Test
    public void projectShouldBeEdited() {
        ProjectsPage page = new ProjectsPage(driver);
        page
                .clickCreatedProject(1)
                .getEditProjectForm()
                .inputProjectFields("New edited", "Edited", "Edited.com")
                .createProject()
                .verifyEditedProject(1, "NE");
    }
}
