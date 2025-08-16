package test.java.anhdltester.base;

import io.github.bonigarcia.wdm.WebDriverManager;
import main.java.dinhtester.helper.Log;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.*;

public class BaseSetupParell {
    public static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    private WebDriver initChromeDriver(String webUrl) {
        System.out.println("Launching chorme driver");
        WebDriver webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.get(webUrl);
        return webDriver;
    }


    private WebDriver initEdgeDriver(String webUrl) {
        System.out.println("Launching Edge driver");
        WebDriver webDriver = new EdgeDriver();
        webDriver.manage().window().maximize();
        webDriver.get(webUrl);
        return webDriver;
    }

    private void setDriver(String browserType, String webUrl) {
        switch (browserType) {
            case "chrome":
                driver.set(initChromeDriver(webUrl));
                break;
            case "edge":
                driver.set(initEdgeDriver(webUrl));
                break;
            default:
                driver.set(initChromeDriver(webUrl));
        }
    }


    @Parameters({"browserType", "webUrl"})
    @BeforeClass
    public void initBaseSetup(String browserType, String webUrl) {
        try {
            setDriver(browserType, webUrl);
        } catch (Exception e) {
            Log.error("Init driver fail");
        }
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() throws InterruptedException {
        Thread.sleep(3000);
        Log.info("Call tear down");
        driver.get().quit();

    }

}

