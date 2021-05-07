package Core;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.github.bonigarcia.wdm.WebDriverManager;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

public class Driver {

    /**
     * Set the driver object and it's associated properties
     *
     * @return The driver object to be used for the test run
     */
    public static WebDriver initialize(String browser) throws IOException, URISyntaxException {

        DesiredCapabilities capability;
        WebDriver driver;

        switch (browser.toUpperCase()) {
            case "FIREFOX":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;
            case "CHROME":
                ChromeOptions chromeOptions = setChromeOptions();
                chromeOptions.addArguments("test-type");
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver(chromeOptions);
                break;
            case "INTERNETEXPLORER":
                WebDriverManager.iedriver().setup();
                driver = new InternetExplorerDriver();
                break;
            default:
                throw new RuntimeException(
                        "An appropriate browser was not set. The following browser is invalid: " + browser);
        }
        Core.setDriver(driver);
        return driver;
    }

    /**
     * Set the system options to the Chrome binary
     */
    private static ChromeOptions setChromeOptions() {
        String userName = System.getProperty("user.name");
        String filePath = "C:\\Users\\" + userName + "\\Downloads";
        ChromeOptions options = new ChromeOptions();
        Map<String, Object> prefs = new HashMap<>();

        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        prefs.put("profile.default_content_setting_values.notifications", 2);
        prefs.put("download.default_directory", filePath);
        options.addArguments("window-size=1440,900");
        options.setExperimentalOption("prefs", prefs);
        options.addArguments("incognito");
        options.addArguments("ignore-certificate-errors");
        return options;
    }


    /**
     * Destroy the driver object
     *
     * @param clearCookies Determine whether cookies should be deleted before
     *                     closing
     */
    public static void terminate(WebDriver driver, boolean clearCookies) {

        if (clearCookies) {
            driver.manage().deleteAllCookies();
        }
        driver.quit();
    }
}
