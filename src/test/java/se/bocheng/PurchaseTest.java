package se.bocheng;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.bocheng.pages.CartPage;
import se.bocheng.pages.InventoryPage;
import se.bocheng.pages.LoginPage;

import static org.assertj.core.api.Assertions.assertThat;

public class PurchaseTest extends BaseTest {
    private LoginPage loginPage;


    @BeforeEach
    void openLoginPage() {
        loginPage = new LoginPage(driver);
        loginPage.open();
    }

    @Test
    void standardUserCanCompletePurchase() {
        loginPage.loginAs("standard_user", "secret_sauce");
        InventoryPage inventoryPage = new InventoryPage(driver);
        assertThat(inventoryPage.isLoaded()).isTrue();
        inventoryPage.addBackpackToCart();
        inventoryPage.openCart();
        CartPage cartPage = new CartPage(driver);
        assertThat(cartPage.getItemCount()).isEqualTo(1);
        cartPage.checkOut();


    }

}
