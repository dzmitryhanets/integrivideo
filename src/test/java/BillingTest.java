import org.testng.annotations.Test;
import pages.BillingPage;

public class BillingTest extends BaseTest {
    @Test
    public void invalidCardShouldNotBeAdded() {
        BillingPage page = new BillingPage(driver);
        page
                .openBillingPage()
                .clickAddBtn()
                .inputCardParameters("1111 1111 1111 1111", "11", "2019", "User User")
                .clackAddCardBtn()
                .verifyInvalidCard();
    }
}
