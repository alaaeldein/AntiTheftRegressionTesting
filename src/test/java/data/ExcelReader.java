package data;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {

	static FileInputStream fis = null;

	public FileInputStream getFileInputStream() {

		String filePath = System.getProperty("user.dir") + "/src/test/java/"
				+ "data/testData.xlsx";
		File srcFile = new File(filePath);

		try {
			fis = new FileInputStream(srcFile);
		} catch (FileNotFoundException e) {
			System.out.println("Test data file not found. terminating process "
					+ "!! Check file path of TestData");
			System.exit(0);
		}
		return fis;

	}

	public Object [][] getExcelData(int sheetNum, int TotalNumOfRows,
			int TotalNumOfCol) throws IOException{

		fis = getFileInputStream();
		XSSFWorkbook wb = new XSSFWorkbook (fis);
		XSSFSheet sheet = wb.getSheetAt(sheetNum);

		String [][] arrayOfExcelData = new String [TotalNumOfRows][TotalNumOfCol];

		for (int i=0; i < TotalNumOfRows; i++) {
			for (int j=0; j < TotalNumOfCol; j ++) {
				XSSFRow row = sheet.getRow(i);
				arrayOfExcelData[i][j] = row.getCell(j).toString();
			}
		}

		wb.close();
		return arrayOfExcelData;

		
	}

}
