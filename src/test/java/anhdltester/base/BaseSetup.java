package test.java.anhdltester.base;

import main.java.dinhtester.helper.Log;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

public class BaseSetup {
    public static WebDriver driver;


    private WebDriver initChromeDriver(String webUrl) {
        System.out.println("Launching chrome driver");
        ChromeOptions options = new ChromeOptions();
        options.setBrowserVersion("131");
        driver = new ChromeDriver(options);
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        driver.get(webUrl);
        return driver;
    }


    private WebDriver initEdgeDriver(String webUrl) {
        System.out.println("Launching Edge driver");
        driver = new EdgeDriver();
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        driver.get(webUrl);
        return driver;
    }


    private void setDriver(String browserType, String webUrl) {
        switch (browserType) {
            case "chrome":
                driver = initChromeDriver(webUrl);
                break;
            case "edge":
                driver = initEdgeDriver(webUrl);
                break;
            default:
                driver = initChromeDriver(webUrl);
        }

    }

    public static WebDriver getDriver() {
        return driver;
    }


    @Parameters({"browserType", "webUrl"})
    @BeforeSuite
    public void initBaseSetup(String browserType, String webUrl) {
        try {
            setDriver(browserType, webUrl);
        } catch (Exception e) {
            Log.error("Init driver fail");
        }
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() throws InterruptedException {
        Thread.sleep(3000);
        Log.info("Call tear down");
        driver.quit();
    }

}

