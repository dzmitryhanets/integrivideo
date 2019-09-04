import org.testng.annotations.Test;
import pages.BillingPage;

public class BillingTest extends BaseTest {
    @Test
    public void cardIsAdded() {
        new BillingPage(driver)
                .openBillingPage()
                .clickAddBtn()
                .inputCardParameters("5555555555554444", "11", "2019", "name name")
                .clackAddCardBtn()
                .verifyAddedCard(1);
    }

    @Test
    public void cardIsRemoved() {
        new BillingPage(driver)
                .openBillingPage()
                .clickAddBtn()
                .inputCardParameters("4242424242424242", "11", "2019", "name name")
                .clackAddCardBtn()
                .removeCard(1)
                .verifyMessage("Payment method successfully removed");
    }

    @Test
    public void invalidCardShouldNotBeAdded() {
        new BillingPage(driver)
                .openBillingPage()
                .clickAddBtn()
                .inputCardParameters("1111 1111 1111 1111", "11", "2019", "User User")
                .clackAddCardBtn()
                .verifyMessage("Credit card is invalid");
    }
}
