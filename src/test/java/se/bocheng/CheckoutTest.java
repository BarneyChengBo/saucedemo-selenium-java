package se.bocheng;

import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.WebDriver;
import se.bocheng.pages.CartPage;
import se.bocheng.pages.CheckoutInfoPage;
import se.bocheng.pages.InventoryPage;
import se.bocheng.pages.LoginPage;

import static org.assertj.core.api.Assertions.assertThat;

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


}
