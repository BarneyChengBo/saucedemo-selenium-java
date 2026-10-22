package se.bocheng;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.bocheng.pages.InventoryPage;
import se.bocheng.pages.LoginPage;

import java.util.Comparator;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class SortingTest extends BaseTest {

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
    void canSortByNameZtoA() {
        inventoryPage.sortBy("za");
        List<String> productNamesList = inventoryPage.getProductNames();
        assertThat(productNamesList).isSortedAccordingTo(Comparator.reverseOrder());
    }

    @Test
    void canSortByNameAtoZ() {
        inventoryPage.sortBy("za");
        List<String> productNamesList = inventoryPage.getProductNames();
        assertThat(productNamesList).isSortedAccordingTo(Comparator.reverseOrder());

        inventoryPage.sortBy("az");
        productNamesList = inventoryPage.getProductNames();
        assertThat(productNamesList).isSorted();
    }

    @Test
    void canSortByPriceLowToHigh() {
        inventoryPage.sortBy("lohi");
        List<Double> productPriceList = inventoryPage.getProductPrices();
        assertThat(productPriceList).isSorted();
    }

    @Test
    void canSortByPriceHighToLow() {
        inventoryPage.sortBy("hilo");
        List<Double> productPriceList = inventoryPage.getProductPrices();
        assertThat(productPriceList).isSortedAccordingTo(Comparator.reverseOrder());
    }


}
