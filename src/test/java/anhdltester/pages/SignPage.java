package test.java.anhdltester.pages;


import main.java.dinhtester.helper.ValidateUIHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class SignPage {
    private final WebDriver driver;
    private final By emailInput = By.id("email");
    private final By passwordInput = By.id("password");
    public final By loginSubmitBtn = By.id("loginSubmitButton");
    private final ValidateUIHelper validateHelper;

    public SignPage(WebDriver driver) {
        this.driver = driver;
        validateHelper = new ValidateUIHelper(this.driver);
    }


    public DashboardPage Signin(String email, String password, String expect) throws InterruptedException {
        System.out.println("expect out try " + expect);
        validateHelper.setText(emailInput, email);
        validateHelper.setText(passwordInput, password);
        validateHelper.clickElement(loginSubmitBtn);
        Thread.sleep(2000); //sleep to wait verify
        Assert.assertEquals(Boolean.toString(validateHelper.verifyPageUrl("https://demo.growcrm.io/home")), expect);
        return new DashboardPage(driver);
    }
}

