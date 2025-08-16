package test.java.anhdltester.pages;

import lombok.SneakyThrows;
import main.java.dinhtester.helper.ValidateUIHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

public class EstimateListPage {
    private WebDriver driver;
    private ValidateUIHelper validateHelper;
    private final By addEstimateBtn = By.xpath("//button[@data-modal-title='Create A New Estimate']");
    private final By clentBox = By.xpath("//div[@id='commonModalBody']/descendant::span[1]/span[1]/span/span[2]");
    private final By clentTxtBox = By.xpath("//*[@id=\"main-body\"]/span/span/span[1]/input");
    private final By estimateDateBox = By.xpath("//input[@class='form-control  form-control-sm pickadate' and @name='bill_date_add_edit']");
    private final By submitBtn = By.id("commonModalSubmitButton");
    private final By listCompanyNameOfClinet = By.xpath("//table//tr//td[@class='clients_col_company displayed tableconfig_column_2']//a");
    private final By listClientNameOfProject = By.xpath("//td[contains(@class, 'projects_col_project')]/a[text()]");


    public EstimateListPage(WebDriver driver) {
        this.driver = driver;
        validateHelper = new ValidateUIHelper(this.driver);

    }

    public void clickAddBtn() {
        validateHelper.clickElement(addEstimateBtn);
    }

    @SneakyThrows
    public void registerPayment(String clentName, String estimateDate) {
        validateHelper.clickElement(clentBox);
        Thread.sleep(3000);
        validateHelper.setText(clentTxtBox, clentName);
        Thread.sleep(5000);
        validateHelper.pressEnter();
        Thread.sleep(3000);
        validateHelper.setText(estimateDateBox, estimateDate);
        Thread.sleep(2000);
        validateHelper.clickElement(submitBtn);
    }

    public void verifyEstimate(String estimateTitleName){
        validateHelper.getPageTitle();
        System.out.println(validateHelper.getPageTitle());
        validateHelper.verifyPageTitle(estimateTitleName);
    }

    public boolean verifyClientValid(String clientName) {
        List<String> listCompanyName = new ArrayList<>();
        driver.findElements(listCompanyNameOfClinet).forEach(e ->
                listCompanyName.add(e.getText()));
        return (listCompanyName.contains(clientName));
    }

    public boolean verifyProjectValid(String projectName) {
        List<String> listClientName = new ArrayList<>();
        driver.findElements(listClientNameOfProject).forEach(e ->
                listClientName.add(e.getText()));
        return (listClientName.contains(projectName));
    }

}
