package test.java.anhdltester.pages;

import main.java.dinhtester.helper.ValidateUIHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class UserListPage {
    private WebDriver driver;
    private ValidateUIHelper validateHelper;
    public WebDriverWait wait;
    private final By addUserBtn = By.xpath("//button[@data-modal-title='Create A New User']");
    private final By companyBox = By.xpath("(//span[@class='select2-selection__arrow'])[1]");
    private final By companyFilterBox = By.xpath("(//input[@role='textbox'])[5]");
    private final By firstNameTxtBox = By.id("first_name");
    private final By lastNameTxtBox = By.id("last_name");
    private final By emailTxtBox = By.id("email");
    private final By submitBtn = By.id("commonModalSubmitButton");
    private final By listElementCompanyName = By.xpath("//tr[starts-with(@id,'contact_')]//td[@class='contacts_col_first_name']/a");
    private final By listCompanyNameOfClinet = By.xpath("//table//tr//td[@class='clients_col_company displayed tableconfig_column_2']//a");


    public UserListPage(WebDriver driver) {
        this.driver = driver;
        validateHelper = new ValidateUIHelper(this.driver);
        wait = new WebDriverWait(this.driver, Duration.ofSeconds(5));

    }

    public void clickAddBtn() {
        validateHelper.clickElement(addUserBtn);
    }

    public void registerUser(String companyName, String firstName, String lastName, String email) throws InterruptedException {
        validateHelper.clickElement(companyBox);
        Thread.sleep(3000);
        validateHelper.setText(companyFilterBox, companyName);
        Thread.sleep(3000);
        validateHelper.pressEnter();
        validateHelper.setText(firstNameTxtBox, firstName);
        validateHelper.setText(lastNameTxtBox, lastName);
        validateHelper.setText(emailTxtBox, email);
        validateHelper.clickElement(submitBtn);

    }

    public void verifyUserInList(String user) {
        List<String> listUserName = new ArrayList<>();
        driver.findElements(listElementCompanyName).forEach(e ->
                listUserName.add(e.getText()));
        Assert.assertTrue(listUserName.contains(user));
    }


    public boolean verifyClientValid(String clientName) {
        List<String> listCompanyName = new ArrayList<>();
        driver.findElements(listCompanyNameOfClinet).forEach(e ->
                listCompanyName.add(e.getText()));
        return (listCompanyName.contains(clientName));
    }
}
