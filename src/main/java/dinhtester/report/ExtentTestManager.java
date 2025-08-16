package main.java.dinhtester.report;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import test.java.anhdltester.base.BaseSetup;

import java.util.HashMap;
import java.util.Map;

public class ExtentTestManager {
    static WebDriver driver;
    static Map<Integer, ExtentTest> extentTestMap = new HashMap<>();
    static ExtentReports extent;

    public static ExtentTest getTest() {
        System.out.println("Call to getTest");
        return extentTestMap.get((int) Thread.currentThread().getId());
    }

    public static synchronized void saveToReport(String testName, String desc) {
        ExtentReportManager.getExtentReports();
        extent = ExtentReportManager.extentReports;
        /*Tạo extentTest trong hàm saveToReport để pass lỗi getTest() is null*/
        ExtentTest test = extent.createTest(testName, desc);
        extentTestMap.put((int) Thread.currentThread().getId(), test);
    }

    public static void addScreenShot(String message) {
        String base64Image = "data:image/png;base64,"
                + ((TakesScreenshot) BaseSetup.getDriver()).getScreenshotAs(OutputType.BASE64);
        getTest().log(Status.INFO, message, MediaEntityBuilder.createScreenCaptureFromBase64String(base64Image).build());
    }

    public static void addScreenShot(Status status, String message) {
        driver = BaseSetup.getDriver();
        String base64Image = "data:image/png;base64,"
                + ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
        System.out.println("Come addScreenShot");
        getTest().log(status, message, MediaEntityBuilder.createScreenCaptureFromBase64String(base64Image).build());
    }

    public static void logMessage(String message) {
        getTest().log(Status.INFO, message);
    }

    public static void logMessage(Status status, String message) {
        getTest().log(status, message);
    }


}
