package main.java.dinhtester.helper;

import main.java.dinhtester.report.ExtentTestManager;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;

import java.time.Duration;

public class ValidateUIHelper {
    public WebDriver driver;
    public WebDriverWait wait;



    public ValidateUIHelper(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(this.driver, Duration.ofSeconds(10));

    }

    public void setText(By element, String value) {
        Log.info(("Set text " + value + " to " + element));
        if (ExtentTestManager.getTest() != null) {
            ExtentTestManager.logMessage("Set text " + value + " to " + element);
        }
        wait.until(ExpectedConditions.elementToBeClickable(element));
        driver.findElement(element).clear();
        driver.findElement(element).sendKeys(value);
    }

    public void clickElement(By element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
        Log.info("Click element " + element);
        if (ExtentTestManager.getTest() != null) {
            ExtentTestManager.logMessage("Click element " + element);
        }
        driver.findElement(element).click();
    }

    public void selectOptionByValue(String value, By element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
        Select select = new Select(driver.findElement(element));
        select.selectByValue(value);
    }

    public void selectOptionByText(String text, By element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
        Select select = new Select(driver.findElement(element));
        select.selectByVisibleText(text);
    }
    public void pressEnter() {
        Actions actions = new Actions(driver);
        actions.sendKeys(Keys.ENTER).perform();
    }

    public void pressTab() {
        Actions actions = new Actions(driver);
        actions.sendKeys(Keys.TAB).perform();
    }
    public String getPageTitle() {
        Log.info("Get title page");
        if (ExtentTestManager.getTest() != null) {
            ExtentTestManager.logMessage("Get title page");
        }
        return driver.getTitle();
    }

    public boolean verifyPageTitle(String pageTitle) {
        Log.info("Verify title page");
        if (ExtentTestManager.getTest() != null) {
            ExtentTestManager.logMessage("Verify title page");
        }
        return getPageTitle().equals(pageTitle);
    }


    public String getPageUrl() {
        Log.info("Get current url page");
        if (ExtentTestManager.getTest() != null) {
            ExtentTestManager.logMessage("Get current url page");
        }
        return driver.getCurrentUrl();
    }

    public boolean verifyPageUrl(String pageUrl) {
        Log.info("Verify current url page");
        if (ExtentTestManager.getTest() != null) {
            ExtentTestManager.logMessage("Verify current url page");
        }
        return getPageUrl().contains(pageUrl);
    }


}
