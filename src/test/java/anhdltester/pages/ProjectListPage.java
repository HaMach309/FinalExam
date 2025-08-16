package test.java.anhdltester.pages;

import lombok.SneakyThrows;
import main.java.dinhtester.helper.ValidateUIHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;


public class ProjectListPage {
    private WebDriver driver;
    private ValidateUIHelper validateHelper;
    private final By addProjetcBtn = By.xpath("//button[@data-modal-title='Create A New Project']");
    private final By companyBox = By.xpath("(//span[@class='select2-selection__arrow'])[3]");
    private final By companyFilterBox = By.xpath("(//input[@role='textbox'])[7]");
    private final By projectTxtBox = By.id("project_title");
    private final By startDateBox = By.xpath("//input[@class='form-control form-control-sm pickadate' and @name='project_date_start']");
    private final By submitBtn = By.id("commonModalSubmitButton");
    private final By listCompanyNameOfClinet = By.xpath("//table//tr//td[@class='clients_col_company displayed tableconfig_column_2']//a");

    public ProjectListPage(WebDriver driver){
        this.driver = driver;
        validateHelper = new ValidateUIHelper(this.driver);
    }

    public void verifyPageTitle(String title){
        Assert.assertTrue(validateHelper.verifyPageTitle(title), "Title not match");
    }
    public void clickAddBtn(){
        validateHelper.clickElement(addProjetcBtn);
    }

    @SneakyThrows
    public void registerProject(String companyName, String projectName, String startDate){
        validateHelper.clickElement(companyBox);
        Thread.sleep(3000);
        validateHelper.setText(companyFilterBox, companyName);
        Thread.sleep(5000);
        validateHelper.pressEnter();
        validateHelper.setText(projectTxtBox, projectName);
        validateHelper.clickElement(startDateBox);
        validateHelper.setText(startDateBox, startDate);
        Thread.sleep(5000);
        validateHelper.pressEnter();
        validateHelper.clickElement(submitBtn);
    }

    public void verifyProject(String projectName){
        validateHelper.getPageTitle();
        System.out.println(validateHelper.getPageTitle());
        validateHelper.verifyPageTitle(projectName);
    }

    public boolean verifyClientValid(String clientName) {
        List<String> listCompanyName = new ArrayList<>();
        driver.findElements(listCompanyNameOfClinet).forEach(e ->
                listCompanyName.add(e.getText()));
        return (listCompanyName.contains(clientName));
    }


}
