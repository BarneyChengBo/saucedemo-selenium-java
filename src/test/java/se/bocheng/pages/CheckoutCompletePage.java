package se.bocheng.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CheckoutCompletePage {
    private final WebDriver driver;
    private final By completeHeader = By.cssSelector("[data-test='complete-header']");

    public CheckoutCompletePage(WebDriver driver) {
        this.driver = driver;
    }

    public String getCompleteHeader() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(completeHeader));
        return driver.findElement(completeHeader).getText();
    }
}
