package main.java.dinhtester.dataprovider;

import main.java.dinhtester.helper.ConstUtil;
import main.java.dinhtester.helper.ExcelHelper;
import org.testng.ITestContext;
import org.testng.annotations.DataProvider;

public class DataProviderManager {

    @DataProvider(name = "loginDataAdmin")
    public Object[][] getDataAdmin(ITestContext context) {
        String pathData = ConstUtil.ROOTFOLDER + context.getCurrentXmlTest().getParameter("pathData");
        ExcelHelper excelLogin = new ExcelHelper();
        excelLogin.setExcelFile(pathData, "admin");
        return excelLogin.getExcelData(pathData, "admin");
    }

    @DataProvider(name = "loginDataUser")
    public Object[][] getDataUser(ITestContext context) {
        String pathData = ConstUtil.ROOTFOLDER + context.getCurrentXmlTest().getParameter("pathData");
        ExcelHelper excelLogin = new ExcelHelper();
        excelLogin.setExcelFile(pathData, "user");
        return excelLogin.getExcelData(pathData, "user");
    }

    @DataProvider(name = "loginData")
    public Object[][] getData(ITestContext context) {
        String pathData = ConstUtil.ROOTFOLDER + context.getCurrentXmlTest().getParameter("pathData");
        String roleName = context.getCurrentXmlTest().getParameter("roleName");
        ExcelHelper excelLogin = new ExcelHelper();
        excelLogin.setExcelFile(pathData, roleName);
        return excelLogin.getExcelData(pathData, roleName);
    }
}
