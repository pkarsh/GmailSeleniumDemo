import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.chrome.*;

import java.util.concurrent.TimeUnit;

public class DriverStarter {

	WebDriver driver;
	public DriverStarter() {
		// TODO Auto-generated constructor stub
	}
	public static WebDriver StartDriver() {

// if you want to run the same driver across all tests, use this method and change selected driver as desired
		return StartDriver(DriverType.FIREFOX);
		
		
	}
	public static WebDriver StartDriver(DriverType selectedDriver) {
		switch (selectedDriver) {
		case FIREFOX:
			return new FirefoxDriver();
			
		case CHROME:
			ChromeOptions options = new ChromeOptions();
			options.setBinary("C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe");
			return new ChromeDriver(options); 

		case IE:
			WebDriver thisDriver = new InternetExplorerDriver();
			thisDriver.manage().deleteAllCookies();
			thisDriver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

			return thisDriver;
			
		default:
			return new FirefoxDriver();
			
				
		}
		
			
	}
	
	public enum DriverType {
		FIREFOX, CHROME, IE
	}
}
