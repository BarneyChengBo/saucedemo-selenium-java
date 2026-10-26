package se.bocheng.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CheckoutOverviewPage {
    private final WebDriver driver;
    private final By finishButton = By.cssSelector("[data-test='finish']");
    private final By subtotalLabel = By.cssSelector("[data-test='subtotal-label']");
    private final By taxLabel = By.cssSelector("[data-test='tax-label']");
    private final By totalLabel = By.cssSelector("[data-test='total-label']");

    public CheckoutOverviewPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickFinish() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(finishButton));
        driver.findElement(finishButton).click();
    }

    public double getSubtotal() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(subtotalLabel));
        String text = driver.findElement(subtotalLabel).getText();
        return parseAmount(text);
    }

    public double getTax() {
        String text = driver.findElement(taxLabel).getText();
        return parseAmount(text);
    }

    public double getTotal() {
        String text = driver.findElement(totalLabel).getText();
        return parseAmount(text);
    }

    private double parseAmount(String text) {
        int targetIndex = text.indexOf("$");
        String numberOnly = text.substring(targetIndex + 1);
        return Double.parseDouble(numberOnly);
    }

}
