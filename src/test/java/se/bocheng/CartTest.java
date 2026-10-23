package se.bocheng;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.bocheng.pages.CartPage;
import se.bocheng.pages.InventoryPage;
import se.bocheng.pages.LoginPage;

import static org.assertj.core.api.Assertions.assertThat;

public class CartTest extends BaseTest {
    private InventoryPage inventoryPage;

    @BeforeEach
    void logInAndOpenInventory() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
        loginPage.loginAs("standard_user", "secret_sauce");
        inventoryPage = new InventoryPage(driver);
        assertThat(inventoryPage.isLoaded()).isTrue();
    }

    @Test
    void addingTwoItemsUpdatesCartBadge() {
        inventoryPage.addBikeLightToCart();
        inventoryPage.addBackpackToCart();
        assertThat(inventoryPage.getCartBadgeCount()).isEqualTo(2);
    }

    @Test
    void canRemoveItemFromCart() {
        inventoryPage.addBikeLightToCart();
        inventoryPage.addBackpackToCart();
        assertThat(inventoryPage.getCartBadgeCount()).isEqualTo(2);

        inventoryPage.openCart();
        CartPage cartPage = new CartPage(driver);
        assertThat(cartPage.getItemCount()).isEqualTo(2);
        cartPage.removeBikeLight();
        assertThat(cartPage.getItemCount()).isEqualTo(1);
    }

    @Test
    void canRemoveItemFromInventoryPage() {
        inventoryPage.addBikeLightToCart();
        inventoryPage.addBackpackToCart();
        assertThat(inventoryPage.getCartBadgeCount()).isEqualTo(2);

        inventoryPage.removeBikeLight();
        assertThat(inventoryPage.getCartBadgeCount()).isEqualTo(1);
    }


}
