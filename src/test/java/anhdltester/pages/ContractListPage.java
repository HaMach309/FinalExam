package test.java.anhdltester.pages;

import lombok.SneakyThrows;
import main.java.dinhtester.helper.ValidateUIHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

public class ContractListPage {
    private WebDriver driver;
    private ValidateUIHelper validateHelper;
    private final By addContractBtn = By.xpath("//button[@data-modal-title='Create A New Contract']");
    private final By clentBox = By.xpath("//div[@id='commonModalBody']/descendant::span[1]/span[1]/span/span[2]");
    private final By clentTxtBox = By.xpath("//*[@id='main-body']/span/span/span[1]/input");
    private final By contractTitleBox = By.id("doc_title");
    private final By startDateBox = By.xpath("//input[@class='form-control form-control-sm pickadate' and @name='doc_date_start']");
    private final By submitBtn = By.id("commonModalSubmitButton");
    private final By listContractClientName = By.xpath("//td[@class='col_doc_title']/a[text()]");
    private final By listCompanyNameOfClinet = By.xpath("//table//tr//td[@class='clients_col_company displayed tableconfig_column_2']//a");
    private final By listClientNameOfProject = By.xpath("//td[contains(@class, 'projects_col_project')]/a[text()]");

    public ContractListPage(WebDriver driver) {
        this.driver = driver;
        validateHelper = new ValidateUIHelper(this.driver);

    }

    public void clickAddBtn() {
        validateHelper.clickElement(addContractBtn);
    }

    @SneakyThrows
    public void registeContract(String clentName, String contractTitle, String startDate) {
        validateHelper.clickElement(clentBox);
        Thread.sleep(3000);
        validateHelper.setText(clentTxtBox, clentName);
        Thread.sleep(5000);
        validateHelper.pressEnter();
        Thread.sleep(3000);
        validateHelper.setText(contractTitleBox, contractTitle);
        validateHelper.setText(startDateBox, startDate);
        Thread.sleep(2000);
        validateHelper.clickElement(submitBtn);
    }

     public void verifyContractInList(String contractName) {
        List<String> listContractName = new ArrayList<>();
        driver.findElements(listContractClientName).forEach(e ->
                listContractName.add(e.getText()));
        Assert.assertTrue(listContractName.contains(contractName));
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
