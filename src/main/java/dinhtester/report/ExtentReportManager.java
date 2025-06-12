package main.java.dinhtester.report;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import main.java.dinhtester.listener.ReportListener;

import java.util.Objects;

public class ExtentReportManager {
    public static ExtentReports extentReports;

    public synchronized static void getExtentReports() {
        if (Objects.isNull(extentReports)) {
            extentReports = new ExtentReports();
            ExtentSparkReporter reporter = new ExtentSparkReporter("./ExtentReports/ExtentReport.html");
            reporter.config().setReportName("Demo Extent Report");
            extentReports.attachReporter(reporter);
            extentReports.setSystemInfo("Framework Name", "Selenium Java Framework");
            extentReports.setSystemInfo("Author", "DinhPX");
            extentReports.setSystemInfo("Browser", getBrowserType());
        }
    }

    public static void flushReports() {
        extentReports.flush();
    }

    public static String getBrowserType() {
        return ReportListener.browser;
    }


}