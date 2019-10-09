package utill;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import testDriver.Driver;

public class TestExecutor extends Driver {

	String TRStatus;
	String TCNumber;
	String ModuleName;
	String TestData;
	String TCDescription;

	public TestExecutor(String TRStatus, String TCNumber, String ModuleName, String TestData, String TCDescription) {
		this.TRStatus = TRStatus;
		this.TCNumber = TCNumber;
		this.ModuleName = ModuleName;
		this.TestData = TestData;
		this.TCDescription = TCDescription;
	}

	// Open the browser and initialize its default properties
	public void Browserinitialization() {

		// -------Getting BrowserName and URL from Property File
		browserName = prop.getProperty("browser");
		url = prop.getProperty("url");

		// -------Checking the Browser to be used and opening it
		if (browserName.equals("Chrome")) {
			System.setProperty("webdriver.chrome.driver",
					"E:\\Work\\eclipse-workspace\\Drivers\\chromedriver75.exe");
			driver = new ChromeDriver();
			Driver.log.info("Chrome Browser Initialized");
		} else if (browserName.equals("Firefox")) {
			System.setProperty("webdriver.gecko.driver",
					"E:\\Work\\eclipse-workspace\\Drivers\\geckodriver.exe");
			driver = new FirefoxDriver();
			Driver.log.info("Firefox Browser Intialized");
		}

		// -------Setting default Browser Settings
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(UtilityClass.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(UtilityClass.IMPLICIT_WAIT, TimeUnit.SECONDS);
	}

	// Load application url
	public void OpenWebApplication() {
		// -------Calling Application URL
		driver.get(url);
	}

	// Run the test case
	public void runTestCase() {
		Driver.log.info("Executing................");
		
		
		// Write test case here.
		
		
		Driver.utillClass.takeScreenshots();
	}

	// Close all browser
	public void closeBrowser() {
		driver.close();
		Driver.log.info("Closing browser");
	}
}
