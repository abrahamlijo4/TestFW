package testDriver;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.poi.ss.usermodel.Row;
import org.openqa.selenium.WebDriver;
import utill.ExcelHandler;
import utill.TestExecutor;
import utill.UtilityClass;

public class Driver {

	// -------Initializing class variables
	public static UtilityClass utillClass;
	public static Logger log;
	public static Properties prop;
	public static WebDriver driver;
	public File file;
	public String PropertyFilePath = ("./src/main/java/config/config.properties");
	public String browserName;
	public String url;

	// Calling basic Log functions in static block
	static {
		utillClass = new UtilityClass();
		utillClass.setCurrentDateTime();
		utillClass.deleteOldLogfiles();

		// Config Log4j Property file
		PropertyConfigurator.configure("./src/main/java/config/Log4j.properties");
		log = Logger.getLogger("MyFW");
	}

	// importing configuration files in Constructor
	public Driver() {
		try {
			prop = new Properties();
			FileInputStream fip = new FileInputStream(PropertyFilePath);
			prop.load(fip);
		} catch (Exception e) {
			System.out.println("Config file not found");
			e.printStackTrace();
		}
	}

	// Read all the test cases that has to be executed.
	public void readAndExecuteTestcases() throws Exception {
		// ------Call function to open excel
		ExcelHandler xlHandler = new ExcelHandler();
		xlHandler.closeAllOpenExcel();
		xlHandler.identifyTestcaseFile();

		// ------initializing variables
		Row columnHeading = xlHandler.testcaseSheet.getRow(1);
		int rowCount = xlHandler.testcaseSheet.getLastRowNum() - xlHandler.testcaseSheet.getFirstRowNum();
		int colCount = columnHeading.getLastCellNum();
		int colTCNo = 0, colModule = 0, colTD = 0, colDes = 0, colTR = 0;

		// ------Traversing through rows and columns
		for (int i = 0; i < rowCount + 1; i++) {
			for (int j = 0; j < colCount; j++) {
				String TRStatusHeading = columnHeading.getCell(j).getStringCellValue();
				if (TRStatusHeading.equals("Test Case No")) {
					colTCNo = j;
				}
				if (TRStatusHeading.equals("Module")) {
					colModule = j;
				}
				if (TRStatusHeading.equals("Test Data")) {
					colTD = j;
				}
				if (TRStatusHeading.equals("Test Case Description")) {
					colDes = j;
				}
				if (TRStatusHeading.equals("Test Run")) {
					colTR = j;
					String TRStatus = xlHandler.testcaseSheet.getRow(i).getCell(colTR).getStringCellValue();
					String TCNumber = xlHandler.testcaseSheet.getRow(i).getCell(colTCNo).getStringCellValue();
					String ModuleName = xlHandler.testcaseSheet.getRow(i).getCell(colModule).getStringCellValue();
					String TestData = xlHandler.testcaseSheet.getRow(i).getCell(colTD).getStringCellValue();
					String TCDescription = xlHandler.testcaseSheet.getRow(i).getCell(colDes).getStringCellValue();
					if (TRStatus.equals("Y")) {
						// TestExecutor will drive the test cases individually
						Driver.log.info("Executing " + TCNumber + ":-" + TCDescription);
						TestExecutor te = new TestExecutor(TRStatus, TCNumber, ModuleName, TestData, TCDescription);
						te.Browserinitialization();
						te.OpenWebApplication();
						te.runTestCase();
						te.closeBrowser();
						Driver.log.info(TCNumber + " execution completed");
					}
				}
			}
		}
	}

	// Quit browsers
	public void quitBrowser() {
		driver.quit();
	}
}
