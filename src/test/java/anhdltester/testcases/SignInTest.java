package test.java.anhdltester.testcases;

import main.java.dinhtester.dataprovider.DataProviderManager;
import main.java.dinhtester.helper.Log;
import main.java.dinhtester.helper.ValidateUIHelper;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import test.java.anhdltester.base.BaseSetup;
import test.java.anhdltester.pages.DashboardPage;
import test.java.anhdltester.pages.SignPage;

public class SignInTest extends BaseSetup {

    public WebDriver driver = null;
    private ValidateUIHelper validateHelper;
    private SignPage signPage;
    DashboardPage dashboardPage;


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
        if(expect.equals("true")){
            dashboardPage.clickMenu();
            dashboardPage.logout();
        }
    }
}
