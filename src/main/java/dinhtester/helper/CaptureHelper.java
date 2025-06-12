package main.java.dinhtester.helper;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;


public class CaptureHelper{

    private static SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss");

    public static void captureScreenshot(WebDriver driver, String screenName) {
        try {
            String path = System.getProperty("user.dir") + File.separator + "ExportData/Images";
            File file = new File(path);
            if (!file.exists()) {
                Log.info("No Folder: " + path);
                file.mkdir();
                Log.info("Folder created: " + file);
            }

            Log.info("Driver for Screenshot: " + driver);
            // Create reference of TakesScreenshot
            TakesScreenshot ts = (TakesScreenshot) driver;
            // Call the capture screenshot function - getScreenshotAs
            File source = ts.getScreenshotAs(OutputType.FILE);
            // result.getName() gets the name of the test case and assigns it to the screenshot file name
            FileUtils.copyFile(source, new File(path + "/" + screenName + "_" + dateFormat.format(new Date()) + ".png"));
            Log.info("Screenshot taken: " + screenName);
            Log.info("Screenshot taken current URL: " + driver.getCurrentUrl());
        } catch (Exception e) {
            System.out.println("Exception while taking screenshot: " + e.getMessage());
        }
    }


}