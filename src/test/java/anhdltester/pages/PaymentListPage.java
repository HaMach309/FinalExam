package test.java.anhdltester.pages;

import lombok.SneakyThrows;
import main.java.dinhtester.helper.ValidateUIHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class PaymentListPage {
    private WebDriver driver;
    private ValidateUIHelper validateHelper;
    private final By addPaymentBtn = By.xpath("//button[@data-modal-title='Add A New Payment']");
    private final By invoiceIdBox = By.id("payment_invoiceid");
    private final By amountBox = By.id("payment_amount");
    private final By submitBtn = By.id("commonModalSubmitButton");
    private final By listElementPaymentName = By.xpath("//td[@class='payments_col_client']/a[text()]");
    private final By listInvoiceCompanyName = By.xpath("//td[starts-with(@id, 'invoices_col_company_')]/a");
    private By listInvoiceId = By.xpath("//td[starts-with(@id, 'invoices_col_id_')]/a");
    private final By listCompanyNameOfClinet = By.xpath("//table//tr//td[@class='clients_col_company displayed tableconfig_column_2']//a");
    private final By listClientNameOfProject = By.xpath("//td[contains(@class, 'projects_col_project')]/a[text()]");



    public PaymentListPage(WebDriver driver) {
        this.driver = driver;
        validateHelper = new ValidateUIHelper(this.driver);

    }

    public int countPaymentInList(String clientName) {
        List<String> listPaymentNameOld = new ArrayList<>();
        driver.findElements(listElementPaymentName).forEach(e -> {
            if (e.getText().equals(clientName)) {
                listPaymentNameOld.add(e.getText());
            }
        });
        return (listPaymentNameOld.size());
    }

    public void clickAddBtn() {
        validateHelper.clickElement(addPaymentBtn);
    }

    @SneakyThrows
    public void registerPayment(String invoiceId, String amount) {
        validateHelper.wait.until(ExpectedConditions.elementToBeClickable(invoiceIdBox));
        validateHelper.clickElement(invoiceIdBox);
        validateHelper.setText(invoiceIdBox, invoiceId);
        Thread.sleep(3000);
        validateHelper.setText(amountBox, amount);
        Thread.sleep(2000);
        validateHelper.clickElement(submitBtn);
    }

    public String getIvoiceId(String clientName) {
        List<String> listInvoiceCompany = new ArrayList<>();

        driver.findElements(listInvoiceCompanyName).forEach(e ->
                listInvoiceCompany.add(e.getText()));

        var i = 0;
        for (String e : listInvoiceCompany) {
            i += 1;
            if (e.equals(clientName)) {
                break;
            }
        }
        System.out.println("gia tri i = " + i);
        listInvoiceId = By.xpath(String.format("(//td[starts-with(@id, 'invoices_col_id_')]/a)[%d]", i));
        String ivoiceId = driver.findElement(listInvoiceId).getText().substring(4);
        return ivoiceId;

    }

    public void verifyPaymentInList(String clientName, int count) {
        List<String> listPaymentName = new ArrayList<>();

        driver.findElements(listElementPaymentName).forEach(e -> {
            if (e.getText().equals(clientName)) {
                listPaymentName.add(e.getText());
            }
        });

        Assert.assertTrue((listPaymentName.size() - count == 1));
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
