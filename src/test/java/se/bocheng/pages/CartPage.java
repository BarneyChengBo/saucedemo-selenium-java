package se.bocheng.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CartPage {
    private final WebDriver driver;
    private final By cartItems = By.className("cart_item");
    private final By checkoutButton = By.cssSelector("[data-test='checkout']");

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    public int getItemCount() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(cartItems));
        return driver.findElements(cartItems).size();
    }

    public void checkOut() {
        driver.findElement(checkoutButton).click();
    }


}
