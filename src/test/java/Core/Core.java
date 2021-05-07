package Core;

import org.apache.commons.codec.binary.Base64;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class Core {

    protected static WebDriver driver;
    public static Actions action;

    public static void setDriver(WebDriver _driver) {
        driver = _driver;
        action = new Actions(driver);
    }

    public static WebDriver getDriver() {
        return driver;
    }

    public static void standardClick(By by) {
        waitUntilElementIsDisplayed(by);
        try {
            driver.findElement(by).click();
        } catch (ElementClickInterceptedException e) {
            pause(10, TimeUnit.SECONDS);
            System.out.println("Element " + by.toString() + " wasn't clickable because another element overlapped it. Trying again in 10 seconds.");
            driver.findElement(by).click();
        }
    }

    public static void pause(int timer, TimeUnit unit) {
        driver.manage().timeouts().implicitlyWait(timer, unit);
    }


    public static String getText(By by) {
        WebElement element = driver.findElement(by);
        return element.getText();
    }

    public static void clearThenSetText(By by, String value) {
        waitUntilElementIsDisplayed(by);
        WebElement element = driver.findElement(by);
        element.clear();
        element.sendKeys(value);
    }

    public static boolean getVisibilityOfElement(By by) {
        waitUntilElementIsDisplayed(by);
        try {
            if(driver.findElement(by).isDisplayed()) {
                return true;
            }
        } catch(Exception e) {}
        return false;
    }

    public static void selectOptionByText(By by, String text) {
        Select select = new Select(driver.findElement(by));
        select.selectByVisibleText(text);
    }

    public static boolean waitUntilElementIsDisplayed(By by) {
        boolean result = false;
        WebElement locatedElement = null;
        try {
            WebDriverWait wait = new WebDriverWait(driver, 20);
            locatedElement = wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        } catch(Exception e) {
            System.out.print("Waited " + 20 + " seconds for element to be visible: " + by.toString());
        }
        if (locatedElement != null) {
            System.out.print("Element is visible: " + by.toString());
            result = true;
        }
        return result;
    }

    public static String decodePswd(String encodedPswd){
        byte[] encodedPassword = Base64.decodeBase64(encodedPswd.getBytes());
        return new String(encodedPassword);
    }
}
