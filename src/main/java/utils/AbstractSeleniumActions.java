package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AbstractSeleniumActions extends BaseTest {
    WebDriverWait wait;

    protected WebElement waitForVisibilityOf(By by, int duration) throws Exception {
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(duration));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    protected WebElement waitForPresenceOf(By by, int duration) throws Exception {
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(duration));
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }


    protected boolean checkLocatorPresence(final By by, int duration) {
        try {
            this.wait = new WebDriverWait(driver, Duration.ofSeconds(duration));
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        } catch (final Exception e) {
            return false;
        }
        return true;
    }

    protected WebElement findElementByListOfLocators(By[] locators, int duration) throws Exception {
        WebElement element = null;
        for (By locator : locators) {
            try {
                element = waitForVisibilityOf(locator, duration);
                return element;
            } catch (Exception e) {
                throw new Exception("NoSuchElement : can't locate the element with specified locators");
            }
        }
        throw new Exception("NoSuchElement : can't locate the element with specified locators");
    }

    protected WebElement findElementByLocatorWithVisibility(By locator, int duration) throws Exception {
        WebElement element = null;
        try {
            element = waitForVisibilityOf(locator, duration);
            return element;
        } catch (Exception e) {
            throw new Exception("NoSuchElement : can't locate the element with specified locators" + e.getMessage());
        }
    }

    protected WebElement findElementByLocatorwithPresence(By locator, int duration) throws Exception {
        WebElement element = null;
        try {
            element = waitForVisibilityOf(locator, duration);
            return element;
        } catch (Exception e) {
            throw new Exception("NoSuchElement : can't locate the element with specified locators" + e.getMessage());
        }
    }

    public void typeText(final By by, final String inputText, int duration) throws Exception {
        waitForPresenceOf(by,duration);
        driver.findElement(by).clear();
        driver.findElement(by).sendKeys(inputText);
    }

    public void clickOnElement(final By by, int duration) throws Exception {
        try {
            waitForPresenceOf(by,duration);
            driver.findElement(by).click();
        } catch (Exception e) {
            throw new Exception("Failed to click on element: " + e.getMessage());
        }
    }

    public String getTextFromElement(final By by, int duration) throws Exception {
        try {
            waitForPresenceOf(by, duration);
            WebElement element = driver.findElement(by);
            return element.getText();
        } catch (Exception e) {
            throw new Exception("Failed to get text from element: " + e.getMessage());
        }
    }
    private boolean isScreenSourceUnchanged(String initialScreenSource) {
        String currentScreenSource =driver.getPageSource();
        return initialScreenSource.equals(currentScreenSource);
    }
    public static boolean elementIsDisplayed(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }


}
