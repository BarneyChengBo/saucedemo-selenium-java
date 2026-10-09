package se.bocheng;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

import static org.assertj.core.api.Assertions.assertThat;

public class LoginTest {
    private WebDriver driver;

    @BeforeEach
    void setUp() {
        driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com");
    }

    @Test
    void standardUserCanLogin() {
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("inventory_item")));

        assertThat(driver.findElements(By.className("inventory_item"))).isNotEmpty();
    }

    @ParameterizedTest
    @CsvSource({
            "locked_out_user, secret_sauce, 'Epic sadface: Sorry, this user has been locked out.'",
            "standard_user, wrong_password, 'Epic sadface: Username and password do not match any user in this service'",
            "'', secret_sauce, 'Epic sadface: Username is required'",
            "standard_user, '', 'Epic sadface: Password is required'"
    })
    void invalidLoginShowsErrorMessage(String username, String password, String expectedError) {
        driver.findElement(By.id("user-name")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("login-button")).click();

        WebElement errorMessage = driver.findElement(By.cssSelector("[data-test='error']"));
        assertThat(errorMessage.isDisplayed()).isTrue();
        assertThat(errorMessage.getText()).isEqualTo(expectedError);
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }
}
