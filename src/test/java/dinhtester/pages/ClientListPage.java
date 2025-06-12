package test.java.dinhtester.pages;

import main.java.dinhtester.helper.ValidateUIHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

public class ClientListPage {
    private WebDriver driver;
    private ValidateUIHelper validateHelper;
    private final By addClientBtn = By.xpath("//button[@data-modal-title='Add Client']");
    private final By companyNameTxtBox = By.id("client_company_name");
    private final By firstNameTxtBox = By.id("first_name");
    private final By lastNameTxtBox = By.id("last_name");
    private final By emailTxtBox = By.id("email");
    private final By categoryListBox = By.id("client_categoryid");
    private final By submitBtn = By.id("commonModalSubmitButton");
    private final By listElementCompanyName = By.xpath("//table//tr//td[@class='clients_col_company displayed tableconfig_column_2']//a");

    public ClientListPage(WebDriver driver){
        this.driver = driver;
        validateHelper = new ValidateUIHelper(this.driver);
    }

    public void verifyPageTitle(String title){
        Assert.assertTrue(validateHelper.verifyPageTitle(title), "Title not match");
    }
    public void clickAddBtn(){
        validateHelper.clickElement(addClientBtn);
    }

    public void registerClient(String companyName, String firstName, String lastName, String email, String category){
        validateHelper.setText(companyNameTxtBox, companyName);
        validateHelper.setText(firstNameTxtBox, firstName);
        validateHelper.setText(lastNameTxtBox, lastName);
        validateHelper.setText(emailTxtBox, email);
        validateHelper.selectOptionByText(category, categoryListBox);
        validateHelper.clickElement(submitBtn);
    }

    public void verifyUserInList(String company){
        List<String> listCompanyName = new ArrayList<>();
        driver.findElements(listElementCompanyName).forEach(e ->
                listCompanyName.add(e.getText()));
        Assert.assertTrue(listCompanyName.contains(company));
    }


}
