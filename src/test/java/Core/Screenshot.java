package Core;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;

public class Screenshot {


    /**
     * Take a screenshot of the current page
     *
     * @param className Name of the current test class
     */
    public static String takeScreenShot(WebDriver driver, String className) {

        System.out.println("Screenshot taken for test: " + className);
        String screenshotPath = null;

        try {
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File destination = new File("src/TestScreenshot/images" + className);

            FileUtils.copyFileToDirectory(screenshot, destination);
            screenshot.delete();
            String path = screenshot.getCanonicalPath();
            System.out.println(path);
            screenshotPath = destination.getCanonicalPath() + path.substring(path.lastIndexOf("\\"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return screenshotPath;
    }
}
