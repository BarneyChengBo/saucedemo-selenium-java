package se.bocheng;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import se.bocheng.pages.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;

public class CheckoutTest extends BaseTest {

    private CheckoutInfoPage checkoutInfoPage;

    @BeforeEach
    void logInAndStartCheckout() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
        loginPage.loginAs("standard_user", "secret_sauce");

        InventoryPage inventoryPage = new InventoryPage(driver);
        assertThat(inventoryPage.isLoaded()).isTrue();
        inventoryPage.addBackpackToCart();
        inventoryPage.openCart();

        CartPage cartPage = new CartPage(driver);
        assertThat(cartPage.getItemCount()).isEqualTo(1);
        cartPage.checkout();

        checkoutInfoPage = new CheckoutInfoPage(driver);
    }

    @ParameterizedTest
    @CsvSource({
            "'', Zhang, 12345, 'Error: First Name is required'",
            "Chris, '', 12345, 'Error: Last Name is required'",
            "Chris, Zhang, '', 'Error: Postal Code is required'"
    })
    void missingRequiredFieldShowsErrorMessage(String firstName, String lastName, String postalCode, String expectedError) {
        checkoutInfoPage.fillInformation(firstName, lastName, postalCode);
        checkoutInfoPage.clickContinue();
        assertThat(checkoutInfoPage.isErrorDisplayed()).isTrue();
        String errorMessage = checkoutInfoPage.getErrorMessage();
        assertThat(errorMessage).isEqualTo(expectedError);
    }

    @Test
    void orderTotalEqualsSubtotalPlusTax() {
        checkoutInfoPage.fillInformation("Chris", "Zhang", "12345");
        checkoutInfoPage.clickContinue();

        CheckoutOverviewPage checkoutOverviewPage = new CheckoutOverviewPage(driver);
        double expectedSubtotal = 29.99;
        double subtotal = checkoutOverviewPage.getSubtotal();
        double tax = checkoutOverviewPage.getTax();
        double total = checkoutOverviewPage.getTotal();
        assertThat(subtotal).isEqualTo(expectedSubtotal);
        assertThat(total).isCloseTo(tax + subtotal, within(0.001));
    }
}
