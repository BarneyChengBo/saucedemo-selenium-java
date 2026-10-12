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
import se.bocheng.pages.LoginPage;

import java.time.Duration;

import static org.assertj.core.api.Assertions.assertThat;

public class LoginTest {
    private WebDriver driver;
    private LoginPage loginPage;

    @BeforeEach
    void setUp() {
        driver = new ChromeDriver();
        loginPage = new LoginPage(driver);
        loginPage.open();

    }

    @Test
    void standardUserCanLogin() {
        loginPage.loginAs("standard_user","secret_sauce");

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

        loginPage.loginAs(username,password);

        String errorMessage = loginPage.getErrorMessage();
        assertThat(loginPage.isErrorDisplayed()).isTrue();
        assertThat(errorMessage).isEqualTo(expectedError);
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }
}
