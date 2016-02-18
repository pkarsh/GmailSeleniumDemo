import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

/* Runs tests on a mail item that has been selected from the Inbox 
 * according to the Sender Name but not opened.  
 *  See comments on MailItemPage object class for more info.
 */

public class MailItemPageBySenderNameTest {
	WebDriver aDriver = DriverStarter.StartDriver();
	GmailLogInPage TestLogIn;
	GmailInboxPage myInbox;
	MailItemPage aTestMailItem;



	@Before
	public void setUp() throws Exception {
		TestLogIn = new GmailLogInPage(aDriver);

		myInbox = new GmailInboxPage(TestLogIn, aDriver,"seleniumlearner1@gmail.com", "reinvent");
		aTestMailItem = new MailItemPage(myInbox, aDriver, "span[name='Paul Karsh']",false);

	}

	
	@After
	public void tearDown() throws Exception {
		aDriver.close();
		
	}
			
//	@Ignore
	@Test
	public void OpenMailItemTest() {
		Assert.assertNotNull(aTestMailItem);
	}
	
	
//	@Ignore
	@Test
	public void testSenderName() {
		aTestMailItem.FindSenderNameElement(".//*[@name='Paul Karsh']");
		Assert.assertEquals(aTestMailItem.GetSenderName(), "Paul Karsh");
	}
	
//	@Ignore
	@Test
	public void testSenderEmail() {
		aTestMailItem.FindSenderEmailElement(".//*[@email='paul.karsh1@gmail.com']");
		Assert.assertEquals(aTestMailItem.GetSenderEmail(), "paul.karsh1@gmail.com");
	}
//	@Ignore
	@Test
	public void testMessageText() {
		aTestMailItem.FindMessageTextElement("//span['This is text within the mail message.']");
		assertNotNull(aTestMailItem.messageText);
	}
//	@Ignore
	@Test
	public void testSubjectText() {
		aTestMailItem.FindMessageSubjectTextElement("//span['This is a test']");
		assertNotNull(aTestMailItem.messageSubjectText);
	}

}
