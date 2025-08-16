package test.java.anhdltester.pages;

import lombok.SneakyThrows;
import main.java.dinhtester.helper.ValidateUIHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import java.util.ArrayList;
import java.util.List;

public class TaskListPage {
    private WebDriver driver;
    private ValidateUIHelper validateHelper;
    private final By addTaskBtn = By.xpath("//button[@data-modal-title='Add A New Task']");
    private final By projectBox = By.xpath("//div[@id='commonModalBody']/descendant::span[1]/span[1]/span/span[2]");
    private final By projectTxtBox = By.xpath("//body[@id='main-body']//span[3]/span/span[1]/input");
    private final By title = By.id("task_title");
    private final By submitBtn = By.id("commonModalSubmitButton");
    private final By showListTaskBtn = By.xpath("//*[@id='list-page-actions']/button[5]");
//    private final By showMoreBtn = By.id("load-more-button");
    private final By listElementTaskName = By.xpath("//a[@data-loading-target='main-top-nav-bar']");
    private final By listClientNameOfProject = By.xpath("//td[contains(@class, 'projects_col_project')]/a[text()]");


    public TaskListPage(WebDriver driver) {
        this.driver = driver;
        validateHelper = new ValidateUIHelper(this.driver);
    }


    public void clickAddBtn() {
        validateHelper.clickElement(addTaskBtn);
    }

    @SneakyThrows
    public void registerTask(String projectName, String titleTask) {
        validateHelper.clickElement(projectBox);
        Thread.sleep(3000);
        validateHelper.setText(projectTxtBox, projectName);
        Thread.sleep(5000);
        validateHelper.pressEnter();
        validateHelper.setText(title, titleTask);
        Thread.sleep(2000);
        validateHelper.clickElement(submitBtn);
    }

    public void showListTask() throws InterruptedException {
        validateHelper.clickElement(showListTaskBtn); Thread.sleep(10000);

//        validateHelper.wait.until(ExpectedConditions.elementToBeClickable(showMoreBtn));

//        validateHelper.clickElement(showMoreBtn);
    }

    public void verifyTaskInList(String taskName) {
        List<String> listTaskName = new ArrayList<>();
        driver.findElements(listElementTaskName).forEach(e ->
                listTaskName.add(e.getText()));
        Assert.assertTrue(listTaskName.contains(taskName));
        System.out.println(listTaskName);
    }
    public boolean verifyProjectValid(String projectName) {
        List<String> listClientName = new ArrayList<>();
        driver.findElements(listClientNameOfProject).forEach(e ->
                listClientName.add(e.getText()));
        return (listClientName.contains(projectName));
    }

}
