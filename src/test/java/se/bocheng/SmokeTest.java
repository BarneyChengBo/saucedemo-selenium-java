package se.bocheng;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SmokeTest extends BaseTest {

    @Test
    void homePageHasCorrectTitle() {
        driver.get("https://www.saucedemo.com");
        String pageTitle = driver.getTitle();
        assertThat(pageTitle).isEqualTo("Swag Labs");
    }

}