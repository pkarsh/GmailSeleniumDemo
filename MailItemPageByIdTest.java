import static org.junit.Assert.*;
import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

/* Runs tests on a mail item that has been "opened". Invokes constructor that selects 
 * a mail item from the Inbox by its element ID. See comments on MailItemPage object class
 * for more info. 
 */


public class MailItemPageByIdTest {
	WebDriver aDriver = DriverStarter.StartDriver();
	GmailLogInPage TestLogIn;
	GmailInboxPage myInbox;
	MailItemPage aTestMailItem;
	


	@Before
	public void setUp() throws Exception {
		TestLogIn = new GmailLogInPage(aDriver);
		myInbox = new GmailInboxPage(TestLogIn, aDriver,"seleniumlearner1@gmail.com", "reinvent");
		aTestMailItem = new MailItemPage(":4e",myInbox,aDriver);
	}

	@After
	public void tearDown() throws Exception {
		aDriver.close();
	}
	@Ignore
	@Test
	public void test() {
		fail("Not yet implemented");
	}

	@Ignore
	@Test
	public void CheckforInbox() throws Exception {
		Assert.assertNotNull(myInbox);
	}
	
	
	@Test
	public void CheckforMailItem() {
		Assert.assertNotNull(aTestMailItem);
	}
	
	@Test
	public void testSenderName() {
	aTestMailItem.FindSenderNameElement(".//*[@name='Paul Karsh']");
	String aSenderName = aTestMailItem.GetSenderName();
	Assert.assertEquals("Paul Karsh", aSenderName);
	}
	
	@Test
	public void testSenderEmail() {
		aTestMailItem.FindSenderEmailElement(".//*[@email='paul.karsh1@gmail.com']");
		String aSenderEmail = aTestMailItem.GetSenderEmail();
		Assert.assertEquals("paul.karsh1@gmail.com", aSenderEmail);
	}
	
	@Test
	public void testWindowTitle() {
		String aWindowTitle = aTestMailItem.windowTitle;
		Assert.assertTrue("Window title is not what was expected", aWindowTitle.contains("Test"));
	}
	
	@Test
	public void testForMessageTextElement () {
		aTestMailItem.FindMessageTextElement("//span['This is text within the mail message.']");
		Assert.assertNotNull(aTestMailItem.messageText);
	}



}
