package testDriver;

public class MainClass {

	public static void main(String[] args) {

		// Initiating log
		Driver.log.info("Start Execution");

		// initializing screenshot folder.
		Driver.utillClass.initializeScreenshotFolder();

		// ------Calling Driver Class
		Driver driver = new Driver();
		Driver.log.info("Initiating Drivers");

		// ------Read Test cases to be executed
		try {
			driver.readAndExecuteTestcases();
		} catch (Exception e) {
			e.printStackTrace();
		}

		// ------Closing the Automation session
		driver.quitBrowser();
		Driver.log.info("End Execution");
	}

}
