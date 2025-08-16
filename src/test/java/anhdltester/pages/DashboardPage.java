package test.java.anhdltester.pages;

import main.java.dinhtester.helper.ValidateUIHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class DashboardPage {
    private WebDriver driver;
    private ValidateUIHelper validateHelper;
    private final By menu = By.xpath("//span[@id='topnav_username']");
    private final By logout = By.xpath("//a[@href='/logout']");
    public DashboardPage(WebDriver driver){
        this.driver = driver;
        validateHelper = new ValidateUIHelper(this.driver);
    }

    public void verifyPageTitle(String title){
        Assert.assertTrue(validateHelper.verifyPageTitle(title), "Title not match");
    }
    public void clickMenu(){
        validateHelper.clickElement(menu);
    }
    public SignPage logout(){
        validateHelper.clickElement(logout);
        return new SignPage(driver);
    }
}
