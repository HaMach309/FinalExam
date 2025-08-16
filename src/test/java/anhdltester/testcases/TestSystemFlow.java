package test.java.anhdltester.testcases;

import main.java.dinhtester.dataprovider.DataProviderManager;
import main.java.dinhtester.helper.Log;
import main.java.dinhtester.helper.ValidateUIHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import test.java.anhdltester.base.BaseSetup;
import test.java.anhdltester.pages.ClientListPage;
import test.java.anhdltester.pages.DashboardPage;
import test.java.anhdltester.pages.SignPage;
import test.java.anhdltester.pages.UserListPage;
import test.java.anhdltester.pages.ProjectListPage;
import test.java.anhdltester.pages.TaskListPage;
import test.java.anhdltester.pages.InvoiceListPage;
import test.java.anhdltester.pages.PaymentListPage;
import test.java.anhdltester.pages.EstimateListPage;
import test.java.anhdltester.pages.ContractListPage;

public class TestSystemFlow extends BaseSetup {
    public WebDriver driver = null;
    private ValidateUIHelper validateHelper;
    private SignPage signPage;
    DashboardPage dashboardPage;
    ClientListPage clientListPage;
    UserListPage userListPage;
    ProjectListPage projectListPage;
    TaskListPage taskListPage;
    InvoiceListPage invoiceListPage;
    PaymentListPage paymentListPage;
    EstimateListPage estimateListPage;
    ContractListPage contractListPage;
    String clientName ="AnhDLClient";
    String projectName ="AnhDLProject";
    String taskName ="AnhDLTask";
    String contactName ="AnhDLContact";


    @Test(priority = 1, dataProvider = "loginDataUser", dataProviderClass = DataProviderManager.class)
    public void login(String username, String password, String expect) throws InterruptedException {
        Log.info("Start testcase with user account");
        driver = getDriver();
        Thread.sleep(1000);
        validateHelper = new ValidateUIHelper(driver);
        signPage = new SignPage(driver);
        dashboardPage = signPage.Signin(username, password, expect);
        Thread.sleep(1000);
    }


    @Test(priority = 2)
    public void createClient() throws InterruptedException {

        driver.get("https://demo.growcrm.io/clients");
        clientListPage = new ClientListPage(driver);
        clientListPage.clickAddBtn();
        Thread.sleep(5000);
        clientListPage.registerClient(clientName, "Name", "Name", "anhdltest@test.com", "Web Development");

        //Verify
        Thread.sleep(3000);
        clientListPage.verifyClientInList(clientName);

    }

    @Test(priority = 3)
    public void createUser() throws InterruptedException {
        userListPage = new UserListPage(driver);

        //Verify client valid
        driver.get("https://demo.growcrm.io/clients");
        boolean resultClient = userListPage.verifyClientValid(clientName);
        if (resultClient == false){
            System.out.println("Client not found");
            return;
        }

        //Create User
        driver.get("https://demo.growcrm.io/users");
        userListPage.clickAddBtn();
        Thread.sleep(3000);
        userListPage.registerUser(clientName, "AnhDL", "User", "anhdltest1@test.com");

        //Verify
        Thread.sleep(3000);
        userListPage.verifyUserInList("AnhDL User");

    }

    @Test(priority = 4)
    public void createProject() throws InterruptedException {
        projectListPage = new ProjectListPage(driver);

        //Verify client valid
        driver.get("https://demo.growcrm.io/clients");
        boolean resultClient = projectListPage.verifyClientValid(clientName);
        if (resultClient == false){
            System.out.println("Client not found");
            return;
        }

      //Create Project
        driver.get("https://demo.growcrm.io/projects");
        projectListPage.clickAddBtn();
        Thread.sleep(5000);
        projectListPage.registerProject(clientName, projectName, "08-16-2025");

        //Verify
        Thread.sleep(3000);
        projectListPage.verifyProject(projectName);

    }

    @Test(priority = 5)
    public void createTask() throws InterruptedException {


        taskListPage = new TaskListPage(driver);

        //Verify project valid
        driver.get("https://demo.growcrm.io/projects");
        boolean resultProject = taskListPage.verifyProjectValid(projectName);
        if (resultProject == false){
            System.out.println("Project not found");
            return;
        }

        //Create task
        driver.get("https://demo.growcrm.io/tasks");
        taskListPage.clickAddBtn();
        Thread.sleep(5000);
        taskListPage.registerTask(projectName,taskName);


        //Verify
        Thread.sleep(3000);
//        taskListPage.showListTask();
        taskListPage.verifyTaskInList(taskName);

    }

    @Test(priority = 6)
    public void createInvoice() throws InterruptedException {

        invoiceListPage = new InvoiceListPage(driver);

        //Verify client valid
        driver.get("https://demo.growcrm.io/clients");
        boolean resultClient = invoiceListPage.verifyClientValid(clientName);
        if (resultClient == false){
            System.out.println("Client not found");
            return;
        }

        //Verify project valid
        driver.get("https://demo.growcrm.io/projects");
        boolean resultProject = invoiceListPage.verifyProjectValid(projectName);
        if (resultProject == false){
            System.out.println("Project not found");
            return;
        }

        //Create Payment
        driver.get("https://demo.growcrm.io/invoices");
        invoiceListPage.clickAddBtn();
        Thread.sleep(3000);
        invoiceListPage.registerInvoice(clientName, "08-16-2025", "08-16-2025");

        //Verify
        Thread.sleep(3000);
        invoiceListPage.verifyInvoice("Project - " + projectName);

    }

    @Test(priority = 7)
    public void createPayment() throws InterruptedException {

        paymentListPage = new PaymentListPage(driver);

        //Verify client valid
        driver.get("https://demo.growcrm.io/clients");
        boolean resultClient = paymentListPage.verifyClientValid(clientName);
        if (resultClient == false){
            System.out.println("Client not found");
            return;
        }

        //Verify project valid
        driver.get("https://demo.growcrm.io/projects");
        boolean resultProject = paymentListPage.verifyProjectValid(projectName);
        if (resultProject == false){
            System.out.println("Project not found");
            return;
        }

        //Get IvoiceId
        driver.get("https://demo.growcrm.io/invoices");
        String ivoiceId = paymentListPage.getIvoiceId(clientName);

        driver.get("https://demo.growcrm.io/payments");

        //Count Payment
        int count = paymentListPage.countPaymentInList(clientName);

        //Create Payment
        paymentListPage.clickAddBtn();
        Thread.sleep(5000);
        paymentListPage.registerPayment(ivoiceId, "500");
        Thread.sleep(5000);

        //Verify
        paymentListPage.verifyPaymentInList(clientName, count);
    }

    @Test(priority = 8)
    public void createEstimate() throws InterruptedException {

        estimateListPage = new EstimateListPage(driver);

        //Verify client valid
        driver.get("https://demo.growcrm.io/clients");
        boolean resultClient = estimateListPage.verifyClientValid(clientName);
        if (resultClient == false){
            System.out.println("Client not found");
            return;
        }

        //Verify project valid
        driver.get("https://demo.growcrm.io/projects");
        boolean resultProject = estimateListPage.verifyProjectValid(projectName);
        if (resultProject == false){
            System.out.println("Project not found");
            return;
        }

        //Create Estimate
        driver.get("https://demo.growcrm.io/estimates");
        estimateListPage.clickAddBtn();
        Thread.sleep(5000);
        estimateListPage.registerPayment(clientName, "08-16-2025");
        Thread.sleep(5000);

        //Verify
        estimateListPage.verifyEstimate("Project - " + projectName);
    }

    @Test(priority = 9)
    public void createContract() throws InterruptedException {

        contractListPage = new ContractListPage(driver);


        //Verify client valid
        driver.get("https://demo.growcrm.io/clients");
        boolean resultClient = paymentListPage.verifyClientValid(clientName);
        if (resultClient == false){
            System.out.println("Client not found");
            return;
        }

        //Verify project valid
        driver.get("https://demo.growcrm.io/projects");
        boolean resultProject = paymentListPage.verifyProjectValid(projectName);
        if (resultProject == false){
            System.out.println("Project not found");
            return;
        }

        //Create Contract
        driver.get("https://demo.growcrm.io/contracts");
        contractListPage.clickAddBtn();
        Thread.sleep(5000);
        contractListPage.registeContract(clientName, contactName, "08-16-2025");
        Thread.sleep(5000);

        //Verify
        driver.get("https://demo.growcrm.io/contracts");
        contractListPage.verifyContractInList(contactName);
    }
}
