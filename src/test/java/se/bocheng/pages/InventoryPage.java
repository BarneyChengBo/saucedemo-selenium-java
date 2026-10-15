package se.bocheng.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class InventoryPage {
    private final WebDriver driver;
    private final By inventoryItems = By.className("inventory_item");
    private final By backpackAddToCartButton = By.id("add-to-cart-sauce-labs-backpack");
    private final By cartLink = By.cssSelector("[data-test='shopping-cart-link']");


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






}
