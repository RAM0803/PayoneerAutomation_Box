package Core;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URISyntaxException;


public abstract class BaseTest {

    protected WebDriver driver;
    String browser = "Chrome";

    @BeforeMethod
    public void initializeTest() throws IOException, URISyntaxException {
        driver = Driver.initialize(browser);
        driver.manage().deleteAllCookies();
    }

    @AfterMethod
    public void tearDownTest(Method method) {
        Screenshot.takeScreenShot(driver, method.getName());
        driver.manage().deleteAllCookies();
        Driver.terminate(driver, false);
    }

}


