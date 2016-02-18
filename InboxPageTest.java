import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;


public class InboxPageTest {
	WebDriver aDriver = DriverStarter.StartDriver();
	GmailLogInPage TestLogIn;
	GmailInboxPage aInboxPage;

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		
	}
	
	/* Tests that elements used on Inbox page for testing sending and receiving mail can be
	 * accessed. These consist of the Compose button and the new Message window. 
	 * Also verifies that elements within New Message window can be accessed. 
	 */

	@Before
	public void setUp() throws Exception {
		TestLogIn = new GmailLogInPage(aDriver);
		aInboxPage = new GmailInboxPage(TestLogIn, aDriver,"seleniumlearner1@gmail.com","reinvent");
	}

	@After
	public void tearDown() throws Exception {
		aDriver.close();
	}

	@Test
	public void TestNewInboxPage() {
		Assert.assertEquals(aInboxPage.WindowTitle.contains("Inbox"),true);
		}
	
	@Test
	public void CheckForComposeButton() {
		aInboxPage.getComposeButton();
		Assert.assertTrue(aInboxPage.ComposeButton.isEnabled(), "Compose button is not enabled");
	}

	//  New Message window tests
	
	@Test
	public void CheckForNewMessageWindow() {
		try {
			Thread.sleep(30000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		NewMessageWindow aTestNewMessageWindow  = new NewMessageWindow(aDriver, aInboxPage);
		assertNotNull(aTestNewMessageWindow);
	}

	@Test
	public void TestToFieldInNewMessage() {
		NewMessageWindow aTestNewMessageWindow  = new NewMessageWindow(aDriver, aInboxPage);
		assertTrue(aTestNewMessageWindow.ToField.isDisplayed());

	}
	
	@Test
	public void TestNewMessgeSendButton() {
		NewMessageWindow aTestNewMessageWindow  = new NewMessageWindow(aDriver, aInboxPage);
		assertTrue(aTestNewMessageWindow.SendButton.isEnabled());
	
		
		}
	
	@Test
	public void TestSubjectFieldinNewMessage() {
		NewMessageWindow aTestNewMessageWindow  = new NewMessageWindow(aDriver, aInboxPage);
		assertTrue(aTestNewMessageWindow.SubjectField.isDisplayed());
	}
	
	@Test
	public void TestMessageBodyFieldinNewMessage() {
		NewMessageWindow aTestNewMessageWindow  = new NewMessageWindow(aDriver, aInboxPage);
		assertTrue(aTestNewMessageWindow.MessageBody.isDisplayed());
	}
}
