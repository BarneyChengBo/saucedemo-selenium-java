package se.bocheng.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CheckoutOverviewPage {
    private final WebDriver driver;
    private final By finishButton = By.cssSelector("[data-test='finish']");

    public CheckoutOverviewPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickFinish() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(finishButton));
        driver.findElement(finishButton).click();
    }

}
