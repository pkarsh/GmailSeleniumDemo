import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;


// Verifies that elements on Login page are located and visible

public class GmailLogInPageTest {
	WebDriver aDriver = DriverStarter.StartDriver();
	GmailLogInPage TestLogIn;

	@Before
	public void setUp() throws Exception {
	TestLogIn = new GmailLogInPage(aDriver);
		
	}

	@After
	public void tearDown() throws Exception {
	aDriver.close();
	}

	@Test
	public void TestForEmailField() {
		Assert.assertEquals(TestLogIn.EmailField.isDisplayed(), true);
	}
	
	@Test
	public void TestForPasswordField() {
		Assert.assertEquals(TestLogIn.PasswordField.isDisplayed(), true);
	}
	
	@Test
	public void TestForCheckBox() {
		Assert.assertEquals(TestLogIn.StaySignedInCheckBox.isDisplayed(), true);
	}
	
	@Test
	public void TestForSignInButton() {
		Assert.assertEquals(TestLogIn.SignInButton.isDisplayed(), true);
	}
	
	@Test
	public void ClickLogin() {
		TestLogIn.EmailField.sendKeys("seleniumlearner1@gmail.com");
		TestLogIn.PasswordField.sendKeys("reinvent");
		TestLogIn.SignInButton.click();

		Assert.assertEquals(aDriver.getTitle(), "Google");
	}
	

		
}
