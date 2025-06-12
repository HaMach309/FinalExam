package test.java.dinhtester.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import test.java.dinhtester.base.BaseSetupParell;


public class EdgeTest extends BaseSetupParell {
    @Test
    public void EdgeTest() throws InterruptedException {
        System.out.println("The thread ID for Edge is " + Thread.currentThread().getId());
        Thread.sleep(2000);
        driver.get().findElement(By.xpath("ssss"));
    }

}
