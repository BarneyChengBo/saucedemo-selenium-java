package se.bocheng;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

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

        assertThat(driver.findElements(By.className("inventory_item"))).isNotEmpty();
    }

    @Test
    void lockedOutUserShowsErrorMessage() {
        driver.findElement(By.id("user-name")).sendKeys("locked_out_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();

        WebElement errorMessage = driver.findElement(By.cssSelector("[data-test='error']"));
        assertThat(errorMessage.isDisplayed()).isTrue();
        assertThat(errorMessage.getText()).isEqualTo("Epic sadface: Sorry, this user has been locked out.");

    }

    @Test
    void wrongPasswordShowsErrorMessage() {
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("wrong_password");
        driver.findElement(By.id("login-button")).click();

        WebElement errorMessage = driver.findElement(By.cssSelector("[data-test='error']"));
        assertThat(errorMessage.isDisplayed()).isTrue();
        assertThat(errorMessage.getText()).isEqualTo("Epic sadface: Username and password do not match any user in this service");

    }

    @Test
    void emptyUsernameShowsErrorMessage() {

        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();

        WebElement errorMessage = driver.findElement(By.cssSelector("[data-test='error']"));
        assertThat(errorMessage.isDisplayed()).isTrue();
        assertThat(errorMessage.getText()).isEqualTo("Epic sadface: Username is required");

    }

    @Test
    void emptyPasswordShowsErrorMessage() {

        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("login-button")).click();

        WebElement errorMessage = driver.findElement(By.cssSelector("[data-test='error']"));
        assertThat(errorMessage.isDisplayed()).isTrue();
        assertThat(errorMessage.getText()).isEqualTo("Epic sadface: Password is required");

    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }
}
