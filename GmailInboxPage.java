import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

// defines elements on the Gmail Inbox page that are needed to send and receive a mail item, as opposed
// to defining all elements on this page

public class GmailInboxPage {
	
	WebDriver selenium;
	GmailLogInPage TestLoginPage;
	String WindowTitle;
	WebElement IndexEntry;
	WebElement ComposeButton;
	

	public GmailInboxPage(GmailLogInPage TestLoginPage, WebDriver selenium, String userName, String password) throws InterruptedException {
		this.selenium = selenium;
		TestLoginPage.EmailField.sendKeys(userName);
		TestLoginPage.PasswordField.sendKeys(password);
		TestLoginPage.SignInButton.click();
		WebElement GmailLink = selenium.findElement(By.linkText("Gmail"));
		GmailLink.click();
		Thread.sleep(60000);
		WindowTitle = selenium.getTitle();
		return ;
		
	}
	public void getComposeButton() {
		/* The Compose Button is instantiated outside the GmailInboxPage constructor so that if a change in the Inbox page breaks this
		 * locator the GmailInboxPage constructor will not be broken as well. In addition a user-friendly diagnostic message 
		 * is posted. Tests that require this button should instantiate it with this
		 * method.  */
	
		try {
			ComposeButton = selenium.findElement(By.xpath("html/body/div[7]/div[3]/div/div[2]/div[1]/div[1]/div[1]/div[2]/div/div/div[1]/div/div"));
		}
		catch (Exception e) {
			System.out.println("Compose button could not be located");
		}
	}
			
	public WebElement GetIndexEntryByXPath(String XPath) {
		WebElement aElement = this.selenium.findElement(By.xpath(XPath));
		return aElement;
	}
	
	public WebElement GetIndexEntryByID(String ID) {
		return(selenium.findElement(By.id(ID)));
	}
	
	public WebElement GetIndexEntryBySelector(String cssselector) {
		WebElement aElement = this.selenium.findElement(By.cssSelector(cssselector));
		return aElement;
	}
	public void ClickOnComposeButton() {
		this.ComposeButton.click();
	}
	
		
		
	

}
