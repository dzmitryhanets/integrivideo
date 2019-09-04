import org.testng.annotations.Test;
import pages.ProjectsPage;

public class ProjectsTest extends BaseTest {

    @Test
    public void newProjectShouldBeAdded() {
        ProjectsPage page = new ProjectsPage(driver);
        page
                .getProjectsPage()
                .clickAddProject()
                .inputProjectFields("test", "test", "test.com")
                .createProject()
                .verifyProjects(7);
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
                .verifyComponent(22);
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
