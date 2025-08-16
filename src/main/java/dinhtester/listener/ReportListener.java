package main.java.dinhtester.listener;

import com.aventstack.extentreports.Status;
import main.java.dinhtester.helper.CaptureHelper;
import main.java.dinhtester.helper.Log;
import main.java.dinhtester.report.ExtentReportManager;
import main.java.dinhtester.report.ExtentTestManager;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import test.java.anhdltester.base.BaseSetup;


public class ReportListener implements ITestListener {
    public static String browser;
    WebDriver driver;

    public String getTestName(ITestResult result) {
        return result.getTestName() != null ? result.getTestName()
                : result.getMethod().getConstructorOrMethod().getName();
    }

    public String getTestDescription(ITestResult result) {
        return result.getMethod().getDescription() != null ? result.getMethod().getDescription() : getTestName(result);
    }

    @Override
    public void onStart(ITestContext iTestContext) {
        driver = BaseSetup.getDriver();
        Log.info("Start testing " + iTestContext.getName());
        browser = iTestContext.getCurrentXmlTest().getParameter("browserType").trim().toUpperCase();
        System.out.println(browser);
        ExtentReportManager.getExtentReports();
        iTestContext.setAttribute("WebDriver", driver);
    }

    @Override
    public void onFinish(ITestContext iTestContext) {
        Log.info("End testing " + iTestContext.getName());
        ExtentReportManager.flushReports();
    }

    @Override
    public void onTestStart(ITestResult iTestResult) {
        Log.info(getTestName(iTestResult) + " test is starting...");
        ExtentTestManager.saveToReport(iTestResult.getName(), iTestResult.getTestName());//Khi bị lỗi vd path file excel bi loi thi se ko chay qua dc
        ExtentTestManager.logMessage(getTestName(iTestResult) + " test is starting");

    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        Log.info(getTestName(iTestResult) + " test is passed.");

        //ExtentReports log operation for passed tests.
        ExtentTestManager.logMessage(Status.PASS, getTestDescription(iTestResult));
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        Log.error(getTestName(iTestResult) + " test is failed.");
        try {
            System.out.println(iTestResult.getThrowable());
            CaptureHelper.captureScreenshot(BaseSetup.getDriver(), getTestName(iTestResult));
        } catch (Exception e) {
            Log.error(e.getMessage());
        }
        ExtentTestManager.addScreenShot(Status.FAIL, getTestName(iTestResult));
        ExtentTestManager.logMessage(Status.FAIL, iTestResult.getThrowable().toString());
        ExtentTestManager.logMessage(Status.FAIL, iTestResult.getName() + " is failed.");
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        Log.warn(getTestName(iTestResult) + " test is skipped.");
        ExtentTestManager.logMessage(Status.SKIP, getTestName(iTestResult) + " test is skipped.");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
        Log.error("Test failed but it is in defined success ratio " + getTestName(iTestResult));
        ExtentTestManager.logMessage("Test failed but it is in defined success ratio " + getTestName(iTestResult));
    }


}