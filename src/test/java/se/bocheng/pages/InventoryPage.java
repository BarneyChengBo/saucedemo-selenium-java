package se.bocheng.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class InventoryPage {
    private final WebDriver driver;
    private final By inventoryItems = By.className("inventory_item");
    private final By backpackAddToCartButton = By.id("add-to-cart-sauce-labs-backpack");
    private final By cartLink = By.cssSelector("[data-test='shopping-cart-link']");
    private final By sortDropdown = By.cssSelector("[data-test='product-sort-container']");
    private final By productNames = By.cssSelector("[data-test='inventory-item-name']");
    private final By productPrices = By.cssSelector("[data-test='inventory-item-price']");


    public InventoryPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isLoaded() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(inventoryItems));
        return !driver.findElements(inventoryItems).isEmpty();
    }

    public void addBackpackToCart() {
        driver.findElement(backpackAddToCartButton).click();
    }

    public void openCart() {
        driver.findElement(cartLink).click();
    }

    public void sortBy(String value) {
        WebElement dropdown = driver.findElement(sortDropdown);
        Select select = new Select(dropdown);
        select.selectByValue(value);
    }

    public List<String> getProductNames() {
        List<WebElement> namesElements = driver.findElements(productNames);
        List<String> names = new ArrayList<>();
        for (WebElement element : namesElements) {
            String text = element.getText();
            names.add(text);
        }
        return names;

    }

    public List<Double> getProductPrices() {
        List<WebElement> priceElements = driver.findElements(productPrices);
        List<Double> prices = new ArrayList<>();
        for (WebElement element : priceElements) {
            String text = element.getText();
            text = text.replace("$","");
            double price = Double.parseDouble(text);
            prices.add(price);
        }
        return prices;
    }


}
