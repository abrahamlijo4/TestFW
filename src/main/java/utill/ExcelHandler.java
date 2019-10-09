package utill;

import java.io.File;
import java.io.FileInputStream;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import testDriver.Driver;

public class ExcelHandler extends Driver {

	// initialize variables
	public String testCaseFilePath = "E:\\Work\\eclipse-workspace\\TestFrameWork\\src\\main\\java\\testData\\TestCases.xlsx";
	public Workbook testCaseWorkbook;
	public Sheet testcaseSheet;

	// Open excel file
	public void identifyTestcaseFile() {
		try {
			File file = new File(testCaseFilePath);
			FileInputStream fip = new FileInputStream(file);
			testCaseWorkbook = null;
			testCaseWorkbook = new XSSFWorkbook(fip);
			testcaseSheet = testCaseWorkbook.getSheet(prop.getProperty("TestCaseSheet"));
			Driver.log.info("Reading test case sheet");
		} catch (Exception e) {
			Driver.log.info("Test case sheet opening failed - " + e.getMessage());
			e.printStackTrace();
		}
	}

	// Close all excel files
	public void closeAllOpenExcel() {
		try {
			Runtime.getRuntime().exec("taskkill /f /t /IM EXCEL.EXE");
			Thread.sleep(1000);
		} catch (Exception e) {
			// Logger.getLogger(ClassName.class.getName()).log(Level.SEVERE, null, e);
			e.printStackTrace();
		}
	}
}
