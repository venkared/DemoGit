package com.petstore.api.utils;

import java.io.IOException;
import java.lang.reflect.Method;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.xmlbeans.XmlException;
import org.testng.annotations.DataProvider;

public class XLUtility {

	@DataProvider(name = "userdata")
	public String[][] getData(Method m) throws EncryptedDocumentException, IOException {

		String ExcelSheetname = m.getName();
		String filepath = "./TestData/TestData.xlsx";    //change the folder name accordingly

		XSSFWorkbook workbook = new XSSFWorkbook(filepath);
		XSSFSheet sheet = workbook.getSheet(ExcelSheetname);
		XSSFRow row = sheet.getRow(0);
		int rowcount = sheet.getPhysicalNumberOfRows();
		int colCount = row.getLastCellNum();

		DataFormatter format = new DataFormatter();
		String testData[][] = new String[rowcount - 1][colCount];

		for (int i = 1; i < rowcount; i++) {
			for (int j = 0; j < colCount; j++) {
				testData[i - 1][j] = format.formatCellValue(sheet.getRow(i).getCell(j));
				System.out.println(testData[i - 1][j]);

			}
			workbook.close();

		}
		return testData;

	}

}
