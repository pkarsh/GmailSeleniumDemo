import static org.junit.Assert.*;

import org.openqa.selenium.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/* Invokes New Message window, sends a mail item, and verifies that it has been sent. 
 * The mail item has a subject string that contains a date and time to make it unique.
 */

public class SendMailTest {
	GmailLogInPage seleniumLoginPage;
	GmailInboxPage seleniumInbox;
	MailItemPage aTestMailItem;
	WebDriver aDriver = DriverStarter.StartDriver();
	String aSubjectTestString;

	@Before
	public void setUp() throws Exception {
//		seleniumLoginPage = new GmailLogInPage

	}

	@After
	public void tearDown() throws Exception {
		aDriver.quit();

	}


	
	@Test
	public void sendMailTst() throws Exception {
		seleniumLoginPage = new GmailLogInPage(aDriver);
		seleniumInbox = new GmailInboxPage(seleniumLoginPage, aDriver, "seleniumlearner1@gmail.com", "reinvent");
		NewMessageWindow aNewMessageWindow = new NewMessageWindow(aDriver, seleniumInbox);
		aNewMessageWindow.ToField.sendKeys("seleniumlearner2@gmail.com");
		aNewMessageWindow.SubjectField.sendKeys(aUniqueSubjectString("test string"));
		aNewMessageWindow.MessageBody.click();
		aNewMessageWindow.MessageBody.sendKeys("This is the text of a mail message");
		aNewMessageWindow.SendButton.click();
		WebElement messageSentMsg = aDriver.findElement(By.xpath("//span['Your message has been sent']"));
		Assert.assertNotNull(messageSentMsg);
		aDriver.close();
		
	}

	
	public String aUniqueSubjectString(String aBaseString) {
		Calendar aDate = Calendar.getInstance();
		aDate.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("MM:dd.hh.mm");
		String currentDate = sdf.format(aDate.getTime());
		return aBaseString + " " + currentDate;
			
		}
	

}
