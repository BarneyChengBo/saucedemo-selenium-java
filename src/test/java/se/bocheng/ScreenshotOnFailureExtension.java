package se.bocheng;

import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;

public class ScreenshotOnFailureExtension implements AfterTestExecutionCallback {

    @Override
    public void afterTestExecution(ExtensionContext context) throws Exception {
        if (context.getExecutionException().isPresent()) {
            BaseTest test = (BaseTest) context.getRequiredTestInstance();
            WebDriver driver = test.driver;

            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

            String fileName = context.getRequiredTestMethod().getName() + "_" + System.currentTimeMillis() + ".png";
            Path target = Path.of("target", "screenshots", fileName);
            Files.createDirectories(target.getParent());
            Files.copy(screenshot.toPath(), target);

            System.out.println("Screenshot saved: " + target);
        }
    }

}
