package se.bocheng;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public abstract class BaseTest {
    protected WebDriver driver;

    @BeforeEach
    void startBrowser() {
        driver = new ChromeDriver();
    }

    @AfterEach
    void quitBrowser() {
        driver.quit();
    }

}
