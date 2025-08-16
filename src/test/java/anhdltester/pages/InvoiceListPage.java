package test.java.anhdltester.pages;

import lombok.SneakyThrows;
import main.java.dinhtester.helper.ValidateUIHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

public class InvoiceListPage {
    private WebDriver driver;
    private ValidateUIHelper validateHelper;
    private final By addInvoiceBtn = By.xpath("//button[@data-modal-title='Create A New Invoice']");
    private final By clentBox = By.xpath("//div[@id='commonModalBody']/descendant::span[1]/span[1]/span/span[2]");
    private final By clentTxtBox = By.xpath("//body[@id='main-body']//span[3]/span/span[1]/input");
    private final By invoiceDateBox = By.xpath("//input[@class='form-control  form-control-sm pickadate' and @name='bill_date_add_edit']");
    private final By dueDateBox = By.xpath("//input[@class='form-control form-control-sm pickadate' and @name='bill_due_date_add_edit']");
    private final By submitBtn = By.id("commonModalSubmitButton");
    private final By listCompanyNameOfClinet = By.xpath("//table//tr//td[@class='clients_col_company displayed tableconfig_column_2']//a");
    private final By listClientNameOfProject = By.xpath("//td[contains(@class, 'projects_col_project')]/a[text()]");

    public InvoiceListPage(WebDriver driver) {
        this.driver = driver;
        validateHelper = new ValidateUIHelper(this.driver);
    }

    public void clickAddBtn() {
        validateHelper.clickElement(addInvoiceBtn);
    }

    @SneakyThrows
    public void registerInvoice(String clentName, String invoiceDate, String dueDate) {
        validateHelper.clickElement(clentBox);
        Thread.sleep(3000);
        validateHelper.setText(clentTxtBox, clentName);
        Thread.sleep(5000);
        validateHelper.pressEnter();
        Thread.sleep(3000);
        validateHelper.clickElement(invoiceDateBox);
        validateHelper.setText(invoiceDateBox, invoiceDate);
        validateHelper.pressTab();
        validateHelper.clickElement(dueDateBox);
        validateHelper.setText(dueDateBox, dueDate);
        Thread.sleep(2000);
        validateHelper.clickElement(submitBtn);
    }

    public void verifyInvoice(String invoiceTitleName){
        validateHelper.getPageTitle();
        System.out.println(validateHelper.getPageTitle());
        validateHelper.verifyPageTitle(invoiceTitleName);
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
