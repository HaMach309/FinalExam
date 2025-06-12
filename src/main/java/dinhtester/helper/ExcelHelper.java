package main.java.dinhtester.helper;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

public class ExcelHelper {
    private FileInputStream fis;
    private FileOutputStream fileOut;
    private Workbook workbook;
    private Sheet sheet;
    private Map<String, Integer> columns = new HashMap<>();

    public ExcelHelper() {
    }

    final String PATH = "path to ";
    //Set Excel File

    public void setExcelFile(String excelPath, String sheetName) {
        Log.info("Set Excel File: " + excelPath);
        Log.info("Sheet Name: " + sheetName);

        try {
            File f = new File(excelPath);

            if (!f.exists()) {
                try {
                    Log.info("File Excel path not found.");
                    throw new Exception("File Excel path not found.");
                } catch (Exception e) {
                    Log.error(e.getMessage());
                }
            }
            if (sheetName.isEmpty()) {
                try {
                    Log.info("The Sheet Name is empty.");
                    throw new Exception("The Sheet Name is empty.");
                } catch (Exception e) {
                    Log.error(e.getMessage());
                }
            }

            fis = new FileInputStream(excelPath);
            workbook = WorkbookFactory.create(fis);
            sheet = workbook.getSheet(sheetName);
            //sh = wb.getSheetAt(0); //0 - index of 1st sheet
            if (sheet == null) {
                //sh = wb.createSheet(sheetName);
                try {
                    Log.info("Sheet name not found.");
                    throw new Exception("Sheet name not found.");
                } catch (Exception e) {
                    Log.error(e.getMessage());
                }
            }

            //adding all the column header names to the map 'columns'
            sheet.getRow(0).forEach(cell -> {
                columns.put(cell.getStringCellValue(), cell.getColumnIndex());
            });

        } catch (Exception e) {
            Log.error(e.getMessage());
        }
    }


    public Object[][] getExcelData(String excelPath, String sheetName) {
        Object[][] data = null;
        Workbook workbook = null;

        Log.info("Set Excel file " + excelPath);
        Log.info("Selected Sheet: " + sheetName);

        try {

            File f = new File(excelPath);

            if (!f.exists()) {
                try {
                    Log.info("File Excel path not found.");
                    throw new Exception("File Excel path not found.");
                } catch (Exception e) {
                    Log.error(e.getMessage());
                }
            }
            if (sheetName.isEmpty()) {
                try {
                    Log.info("The Sheet Name is empty.");
                    throw new Exception("The Sheet Name is empty.");
                } catch (Exception e) {
                    Log.error(e.getMessage());
                }
            }

            // load the file
            FileInputStream fis = new FileInputStream(excelPath);

            // load the workbook
            workbook = new XSSFWorkbook(fis);
            // load the sheet
            Sheet sheet = workbook.getSheet(sheetName);
            // load the row
            Row row = sheet.getRow(0);

            int noOfRows = sheet.getPhysicalNumberOfRows();
            int noOfCols = row.getLastCellNum();

            System.out.println(noOfRows + " - " + noOfCols);

            Cell cell;
            data = new Object[noOfRows - 1][noOfCols];

            //FOR loop runs from 1 to drop header line (headline is 0)
            for (int i = 1; i < noOfRows; i++) {
                for (int j = 0; j < noOfCols; j++) {
                    row = sheet.getRow(i);
                    cell = row.getCell(j);

                    //This is used to determine the data type from cells in Excel and then convert it to String for ease of reading
                    switch (cell.getCellType()) {
                        case STRING:
                            data[i - 1][j] = cell.getStringCellValue();
                            break;
                        case NUMERIC:
                            data[i - 1][j] = String.valueOf(cell.getNumericCellValue());
                            break;
                        case BLANK:
                            data[i - 1][j] = "";
                            break;
                        default:
                            data[i - 1][j] = null;
                            break;
                    }
                }
            }
        } catch (Exception e) {
            e.getMessage();
            throw new RuntimeException(e);
        }
        return data;
    }


}
