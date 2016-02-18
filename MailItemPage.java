import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/* Has constructors to select a mail item from the Inbox page by sender name
 * and to open a mail item by element ID. For some reason opening a mail item that
 * has been selected by Sender name doesn't seem to work in the context of testing this
 * page. I don't know why but I suspect some timing issue in the interaction between Selenium
 * and Gmail. Opening a mail item that has been selected by specifying its element ID
 * seems to work. The tests where I send a mail item and then verify that is received
 * seem to allow opening a mail item that has been specified by sender name. For this reason
 * the constructor that instantiates a mail item element that has been selected by Sender name
 * contains a Boolean parameter specifying whether or not to open it by clicking on it.  
 */


public class MailItemPage {
	WebDriver selenium;
	GmailInboxPage aInboxPage;
	WebElement senderName;
	WebElement senderEmail;
	WebElement messageText;
	WebElement messageSubjectText;
	String windowTitle;
	
/* This constructor specifies the mail item to be selected by its element ID. This must be found
 * outside of this test by logging in to Gmail as seleniumlearner1@gmail.com in Firefox and using
 * Firebug to find the ID value for the mail item of interest. Unfortunately the ID value could change
 * but for some reason opening the mail item by clicking on it always works with this constructor.
 */
	
	public MailItemPage(String emailElementId, GmailInboxPage aInboxPage, WebDriver aDriver) throws InterruptedException  {
		this.selenium = aDriver;
		WebElement aMailItem = aInboxPage.GetIndexEntryByID(emailElementId);
		aMailItem.click();
		windowTitle = aDriver.getTitle();
		return;
	 
	}


	/* This constructor selects the mail item using a CSS selector based on the Sender name. It has the advantage that the
	 * Sender name is not likely to change so long as the mail item is present in the recipient's Inbox. It has the
	 * disadvantage that in some cases attempting to open the mail item by clicking on it fails in Selenium. See the comment at the
	 * for this page object. A Boolean parameter allows specifying whether or not to try to open the mail item when
	 * the mail item object is instantiated.
	 */
	public MailItemPage(GmailInboxPage aInboxPage, WebDriver aDriver, String IndexSelector, boolean openitem)  throws InterruptedException  {
		this.selenium = aDriver;
		WebElement aMailItem = aInboxPage.GetIndexEntryBySelector(IndexSelector);
		if (openitem) {
			aMailItem.click();
		}
		return;
	 
	}

	// This constructor is retained for backward compatibility purposes
	
	public MailItemPage (GmailInboxPage aInboxPage, WebDriver aDriver, String IndexSelector) throws InterruptedException {
		this.selenium = aDriver;
		WebElement aMailItem = aInboxPage.GetIndexEntryBySelector(IndexSelector);

		return;
	 
	}

	
	public void FindSenderNameElement(String senderNameXPath) {
		try {
			senderName = selenium.findElement(By.xpath(senderNameXPath));
		}
		catch (Exception e) {
			System.out.println("senderName element could not be retrieved");
		}
		return;
		
	}
	public String GetSenderName() {
		if (senderName != null)
			return senderName.getAttribute("name");
		else return "";
	}
	
	public void FindSenderEmailElement(String senderEmailXPath) {
		try {
			senderEmail = selenium.findElement(By.xpath(senderEmailXPath));
		}
		catch (Exception e) {
			System.out.println("The sender email elament could not be retrieved");
		}
		return;
	}
	
	public String GetSenderEmail() {
		if (senderEmail != null)
			return senderEmail.getAttribute("email");
		else
			return "";
	}
	
	
	public void FindMessageSubjectTextElement(String messageSubjectTextXPath) {
		try {
			messageSubjectText = selenium.findElement(By.xpath(messageSubjectTextXPath));
		}
		catch (Exception e) {
			System.out.println("The message subject text element could not be found");
		}
		
	}
	
	public void FindMessageTextElement(String messageTextXPath) {
		try {
			messageText = this.selenium.findElement(By.xpath(messageTextXPath));
		}
		catch (Exception e) {
			System.out.println("Message text element could not be retrieved");
		}
		
	}

}
