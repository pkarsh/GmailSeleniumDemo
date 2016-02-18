import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/* This object is instantiated from the Inbox page and only exists until a new mail
 * message is sent.
 */
public class NewMessageWindow {
	GmailInboxPage aInboxPage;
	WebDriver selenium;
	WebElement aNewMessageWindow;
	WebElement ToField;
	WebElement SubjectField;
	WebElement MessageBody;
	WebElement SendButton;
	

	public NewMessageWindow(WebDriver aDriver, GmailInboxPage inBoxPage) {
		this.selenium = aDriver;
		aInboxPage = inBoxPage;
		aInboxPage.getComposeButton(); // see comment in this method
		aInboxPage.ComposeButton.click();
		aNewMessageWindow = aInboxPage.selenium.findElement(By.xpath("//span['New Message']"));
		ToField = aDriver.findElement(By.tagName("textarea"));
		 SubjectField = aDriver.findElement(By.name("subjectbox"));
		 MessageBody = aDriver.findElement(By.cssSelector("div[aria-label='Message Body']"));
		 SendButton = aDriver.findElement(By.cssSelector("div[aria-label='Send ‪(Ctrl-Enter)‬']"));

		return;
		
		
		// TODO Auto-generated constructor stub
	}

}
