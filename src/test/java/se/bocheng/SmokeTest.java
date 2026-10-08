package se.bocheng;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.assertj.core.api.Assertions.assertThat;

public class SmokeTest {
    private WebDriver driver;

    @BeforeEach
    void setUp() {
        driver = new ChromeDriver();
    }

    @Test
    void homePageHasCorrectTitle() {
        driver.get("https://www.saucedemo.com");
        String pageTitle = driver.getTitle();
        assertThat(pageTitle).isEqualTo("Swag Labs");
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }
}