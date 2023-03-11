package com.crm.qa.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.crm.qa.Basetest.TestBase;

public class TestUtil extends TestBase{
	
	public static final long PAGE_LOAD_TIMEOUT = 20;
	
	public static final long IMPLICIT_WAIT=10;
	

	public void switchToFrame(){
	driver.switchTo().frame("mainpanel");
}
	
//	public static String TESTDATA_SHEET_PATH = "/Users/Ronak/Desktop/java"
	//	+ "/com-POM/src/test/java/com/crm/qa/testdata/TestData.xlsx";

		static Workbook book;
		static Sheet sheet;



		public static Object[][] getTestData(String sheetName, String excelpath) {
			FileInputStream file=null;
			
			
					try {
						file = new FileInputStream(excelpath);
					} catch (Exception e) {
						
					}
					
					try {
						book = WorkbookFactory.create(file);
					} catch (Exception e) {
						
					}

				
					
			
			sheet= book.getSheet(sheetName);
			Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
			//System.out.println(sheet.getLastRowNum() + "--------" +
			//sheet.getRow(0).getLastCellNum());
			for (int i = 0; i < sheet.getLastRowNum(); i++) {
				for (int k = 0; k < sheet.getRow(0).getLastCellNum(); k++) {
					data[i][k] = sheet.getRow(i + 1).getCell(k).toString();
					//System.out.println(data[i][k]);
				}
			}
			return data;
		}


		public static void takeScreenshotAtEndOfTest() throws IOException {
			File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			String currentDir = System.getProperty("user.dir");
			FileUtils.copyFile(scrFile, new File(currentDir + "/failedtestscreenshot/"+"failed.jpg"));

			}
}

	

