import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class GmailLogInPage {
	WebDriver selenium;
	WebElement EmailField;
	WebElement PasswordField;
	WebElement StaySignedInCheckBox;
	WebElement SignInButton;
	
	public GmailLogInPage(WebDriver selenium) {
		this.selenium = selenium;
		selenium.get("https://www.google.com/");
		WebElement GmailLogInPage = selenium.findElement(By.linkText("Sign in"));
		GmailLogInPage.click();
		EmailField = selenium.findElement(By.name("Email"));
		PasswordField = selenium.findElement(By.name("Passwd"));
		StaySignedInCheckBox = selenium.findElement(By.id("PersistentCookie"));
		SignInButton = selenium.findElement(By.name("signIn"));
		return;

	}
	
}
