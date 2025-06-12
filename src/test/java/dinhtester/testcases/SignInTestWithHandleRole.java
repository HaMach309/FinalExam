package test.java.dinhtester.testcases;

import main.java.dinhtester.dataprovider.DataProviderManager;
import main.java.dinhtester.helper.Log;
import main.java.dinhtester.helper.ValidateUIHelper;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import test.java.dinhtester.base.BaseSetup;
import test.java.dinhtester.pages.DashboardPage;
import test.java.dinhtester.pages.SignPage;

public class SignInTestWithHandleRole extends BaseSetup {

    public WebDriver driver = null;
    private ValidateUIHelper validateHelper;
    private SignPage signPage;
    DashboardPage dashboardPage;


//
//    @Test(dataProvider = "loginDataAdmin", dataProviderClass = DataProviderManager.class)
//    public void signInWithAdmin(String username, String password, String expect) throws InterruptedException {
//        Log.info("Start testcase with admin account");
//        if(dashboardPage != null){
//            dashboardPage = null;
//        }
//        driver = getDriver();
//        driver.get("https://demo.growcrm.io/login");
//        Thread.sleep(2000);
//        validateHelper = new ValidateUIHelper(driver);
//        signPage = new SignPage(driver);
//        validateHelper.waitForPageLoaded();
//        dashboardPage = signPage.Signin(username, password, expect);
//        Thread.sleep(3000);
//        if(expect.equals("false")){
//            dashboardPage.clickMenu();
//            dashboardPage.logout();
//        }
//
//    }

    @Test(dataProvider = "loginDataUser", dataProviderClass = DataProviderManager.class)
    public void signInWithUser(String username, String password, String expect) throws InterruptedException {
        Log.info("Start testcase with user account");
        if(dashboardPage != null){
            dashboardPage = null;
        }
        driver = getDriver();
        driver.get("https://demo.growcrm.io/login");
        Thread.sleep(2000);
        validateHelper = new ValidateUIHelper(driver);
        signPage = new SignPage(driver);
        dashboardPage = signPage.Signin(username, password, expect);
        Thread.sleep(2000);
        if(expect.equals("false")){
            dashboardPage.clickMenu();
            dashboardPage.logout();
        }
    }
}
