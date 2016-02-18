import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

/* Logs into Gmail as seleniumlearner1@gmail.com and sends an email to seleniumlearner2@gmail.com.
 * The email has a subject line that includes a date and time to make it unique. The
 * text of the subject line is "remembered" by the instantiation of this test. Test then logs out 
 * seleniumlearner1. Receive test logs in as seleniumlearner2@gmail.com and gets first mail item sent by seleniumlearner1 
 * that it finds. It compares the subject line in this email to verify that the 
 * received mail item is the one that was just sent by seleniumlearner1.
 */



//@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SendReceiveScenarioTest {
	GmailLogInPage seleniumSenderLoginPage, seleniumReceiverLoginPage;
	GmailInboxPage senderInbox, receiverInbox;
	MailItemPage aTestMailItem;
	WebDriver aDriver;/*  = DriverStarter.StartDriver(); */
	static String aSubjectTestString = aUniqueSubjectString("test string");

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	
	@Test
	public void SendMailItemTest() throws Exception{
		aDriver = DriverStarter.StartDriver();
		seleniumSenderLoginPage = new GmailLogInPage(aDriver);
		senderInbox = new GmailInboxPage(seleniumSenderLoginPage, aDriver, "seleniumlearner1@gmail.com", "reinvent");
		NewMessageWindow aNewMessageWindow = new NewMessageWindow(aDriver, senderInbox);
		aNewMessageWindow.ToField.sendKeys("seleniumlearner2@gmail.com");
		aSubjectTestString = aUniqueSubjectString("test string");
		aNewMessageWindow.SubjectField.sendKeys(aSubjectTestString);
		aNewMessageWindow.MessageBody.click();
		aNewMessageWindow.MessageBody.sendKeys("This is the text of a mail message");
		aNewMessageWindow.SendButton.click();
		WebElement messageSentMsg = aDriver.findElement(By.xpath("//span['Your message has been sent']"));
		Thread.sleep(30000);
	
		aDriver.close();
	}
		
		@Test
		public void ReceiveTest() throws Exception {
		aDriver = DriverStarter.StartDriver();
		

		seleniumReceiverLoginPage = new GmailLogInPage(aDriver);
		receiverInbox = new GmailInboxPage(seleniumReceiverLoginPage, aDriver, "seleniumlearner2@gmail.com", "reinvent");
		aTestMailItem = new MailItemPage(receiverInbox, aDriver, "span[name='Selenium Learnerone']",true);
		aTestMailItem.windowTitle = aDriver.getTitle();
		Assert.assertTrue(aTestMailItem.windowTitle.contains(aSubjectTestString));
		aDriver.quit();
	}
	
	public static String aUniqueSubjectString(String aBaseString) {
		Calendar aDate = Calendar.getInstance();
		aDate.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("MM:dd.hh.mm");
		String currentDate = sdf.format(aDate.getTime());
		return aBaseString + " " + currentDate;
			
		}
	
}
