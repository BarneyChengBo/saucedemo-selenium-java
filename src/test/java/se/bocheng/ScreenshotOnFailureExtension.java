package se.bocheng;

import io.qameta.allure.Allure;
import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.ByteArrayInputStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class ScreenshotOnFailureExtension implements AfterTestExecutionCallback {

    @Override
    public void afterTestExecution(ExtensionContext context) throws Exception {
        if (context.getExecutionException().isPresent()) {
            BaseTest test = (BaseTest) context.getRequiredTestInstance();
            WebDriver driver = test.driver;

            byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);

            String fileName = context.getRequiredTestMethod().getName() + "_" + System.currentTimeMillis() + ".png";
            Path target = Path.of("target", "screenshots", fileName);
            Files.createDirectories(target.getParent());
            Files.write(target, screenshot);

            Allure.addAttachment("Screenshot on failure", "image/png", new ByteArrayInputStream(screenshot), "png");

            System.out.println("Screenshot saved: " + target);
        }
    }

}
