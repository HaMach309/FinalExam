package test.java.dinhtester.testcases;

import main.java.dinhtester.dataprovider.DataProviderManager;
import main.java.dinhtester.helper.Log;
import main.java.dinhtester.helper.ValidateUIHelper;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import test.java.dinhtester.base.BaseSetup;
import test.java.dinhtester.pages.ClientListPage;
import test.java.dinhtester.pages.DashboardPage;
import test.java.dinhtester.pages.SignPage;

public class CreateUserFlow extends BaseSetup {
    public WebDriver driver = null;
    private ValidateUIHelper validateHelper;
    private SignPage signPage;
    DashboardPage dashboardPage;
    ClientListPage clientListPage;

    @Test(priority = 1, dataProvider = "loginDataUser", dataProviderClass = DataProviderManager.class)
    public void login(String username, String password, String expect) throws InterruptedException {//bi loi khong the chay vao test khi khong khai bao tham so expect
        Log.info("Start testcase with admin account");
        driver = getDriver();
        driver.get("https://demo.growcrm.io/login");
        Thread.sleep(2000);
        validateHelper = new ValidateUIHelper(driver);
        signPage = new SignPage(driver);
        dashboardPage = signPage.Signin(username, password);
        Thread.sleep(3000);
    }

    @Test(priority = 2)
    public void createClient() throws InterruptedException {
        driver.get("https://demo.growcrm.io/clients");
        clientListPage = new ClientListPage(driver);
        clientListPage.clickAddBtn();
        Thread.sleep(5000);
        clientListPage.registerClient("ABC2", "First", "Last", "demo2@test.com", "Web Development");

        //Verify
        Thread.sleep(3000);
        clientListPage.verifyUserInList("ABC2");

    }


}
