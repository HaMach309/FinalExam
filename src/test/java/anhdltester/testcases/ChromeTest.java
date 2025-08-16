package test.java.anhdltester.testcases;

import org.testng.annotations.Test;
import test.java.anhdltester.base.BaseSetupParell;

public class ChromeTest extends BaseSetupParell {

    @Test
    public void ChromeTest() throws InterruptedException {
        System.out.println("The thread ID for Edge is " + Thread.currentThread().getId());
        Thread.sleep(2000);
    }
}
