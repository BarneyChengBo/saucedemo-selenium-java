package se.bocheng;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.HashMap;
import java.util.Map;


public abstract class BaseTest {
    protected WebDriver driver;

    @BeforeEach
    void startBrowser() {
        // Disable Chrome password manager to prevent the "Change your password" popup from blocking clicks
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        prefs.put("profile.password_manager_leak_detection", false);

        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", prefs);

        // Run without a browser window when started with -Dheadless=true
        boolean headless = Boolean.parseBoolean(System.getProperty("headless" ,"false"));
        if (headless) {
            options.addArguments("--headless=new");
            options.addArguments("--window-size=1920,1080");
        }

        driver = new ChromeDriver(options);
    }

    @AfterEach
    void quitBrowser() {
        driver.quit();
    }

}
