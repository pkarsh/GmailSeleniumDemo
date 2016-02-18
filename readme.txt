GmailTestDemo
Set of source files that use Selenium and JUnit to send and receive a mail item in Gmail.
- Paul Karsh 6/19/2014


This project demonstrates my use of Selenium WebDriver to send and receive an email item in Google Gmail.
It consists of page object class files to define the pages used for this task in Selenium, as well as JUnit  
tests to verify that the objects defined in these page objects can be found. Finally, there is a file that executes
Selenium commands to bring up the Gmail pages needed to log in as a particular Gmail user, create and send a mail item, 
log out, then log in as the intended recipient of the mail item and verify that it was received.

This project does not exercise all the functionality of Gmail. Its scope is restricted to using Selenium
to carry out the task described above.

Page object class files and JUnit class files to test them are as follows:
GmailLoginPage.java - Brings up Gmail login page and defines username and password fields, as well as "Stay signed in" check box
and Login button.

GmailLogInPageTest.java - Verifies that Selenium elements that define objects on Login page can be found and that user
seleniumlearner1@gmail.com can log in.

GmailInboxPage.java - Defines the following elements and objects on the Gmail inbox page needed for subsequent tests:
	WindowTitle - used to verify that the Inbox page has been brought up
	IndexEntry - An individual mail item in the list of mail items on the Inbox page.
	Compose button - This button brings up a New Message Window for sending a mail item 
	when it is clicked.
	
This page contains methods for selecting an individual mail item using xpath, Id, or
CSS Selector locators. In addition, there is a method for locating the Compose button 
outside of the page constructor. The reason for this is that a hard-coded xpath is
needed to locate this element. If a change is made to this page that renders this
xpath invalid, the Inbox page can still be instantiated and the only operation that 
will fail is locating this button. If this happens, a more friendly error message is
posted describing the failure. When the Compose button is needed, it is located by
invoking the GetComposeButton method. This is detailed in the comment for this method.
	
 InboxPageTest.java - Verifies that the Inbox page is brought up and that the Compose button can be found
 and used. In addition, the New Message Window and its elements are verified in this test class. The reason
 for this is that the New Message window is invoked from the Inbox page by clicking on the Compose button
 and exists only until a mail item is composed and sent.   
 
 NewMessageWindow.java - Defines the field elements in the New Message window. A New Message window
 is invoked by clicking on the Compose button on the Inbox page. It consists of the following
 elements:
 	ToField - Field where recipients' email addresses are entered
 	SubjectField - Field where a subject line for the email may be entered
 	MessageBody - Field where the message content of the email is entered.
 	SendButton - Button that is clicked to send the email item
 	
 	NOTE: Entering text into the MessageBody field doesn't seem to work with
 	the Selenium Firefox WebDriver but it does with the Selenium Chrome WebDriver.
 	
 MailItemPage.java - Defines the elements in an individual mail item selected from the Inbox page. 
 These are as follows:
 	senderName - The "human" name of the sender as entered in their profile somewhere
 	senderEmail - Just what it says, the sender's email address
 	messageText - Area where the main body of an email message is entered. For some 
 	reason, entering text in this field doesn't work with the Firefox driver but
 	it does with the Chrome driver.
 	messageSubjectText - Field element containing the Subject string for the email message
 	WindowTitle - When the mail item is opened, the WindowTitle contains the content of the Subject string. 
 	The Window Title should be retrievable using the Selenium call GetWindowTitle().
 	
 The MailItemPage class has three constructors. Two of them select a mail item from the 
 Inbox index by a specified value for SenderName. One of these two offers the choice of whether
 or not to open the mail item. The third constructor selects a mail item by its element ID,
 which has to be determined outside of this project using an elment inspector such as
 Firebug. It appears that in some contexts I cannot get Selenium to open a mail item that
 has been selected by SenderName but Selenium can usually open mail items selected by ID.
 The SenderName is not likely to change but the ID is categorically subject to change.
 There are therefore two test pages for the MailItemPage. These are described below. 
 
 There are methods in the MailItemPage class to instantiate the elements
 listed above for an individual mail item. In addition, because senderName and senderEmail 
 are listed as attributes within the html <span> tag that is part of the definition of
 the mail item, it is possible to retrieve string values for these properties. There are methods
 in this class to do that. Unfortunately I have been unable to retrieve string values
 for the Subject text and the message text. 
 
 MailitemPageByIDTest.java - Verifies that an individual mail item can be selected from the
 Inbox page and opened. The item to be opened is specified by an ID obtained by
 inspecting the item of interest using an element inspector. At present, this ID is hard-coded
 as a parameter passed to the constructor which opens the mail item by ID. The presence
 and content of the elements are verified by calling methods within the MailItemPage 
 object class. Because the mail item is opened, the WindowTitle can be retrieved as
 a string.
 
 MailItemPageBySenderNameTest.java - Verifies that an individual mail item can be selected
 by SenderName. The presence and content of the elements of the mail item are verified
 by calling methods within the MailItemPage object class. This test class does not actually
 open the mail item, however, so the WindowTitle value cannot be instantiated.
 
 In addition to the Page class and Page class test files described above, there are two additional test
 files. SendMailTest.java logs in as Seleniumlearner1@gmail.com and sends a mail item to 
 Seleniumlearner2@gmail.com. The mail item contains a subject string that contains date and time text
 to make it unique. This test verifies that the mail item has been sent. 
 
 SendReceiveScenarioTest.java logs in as seleniumlearner1@gmail.com, sends a mail item with a 
 unique subject string to seleniumlearner2@gmail.com, then closes its browser window. 
 The subject string is "remembered" by the test class instantiation. A receive test then logs in as 
 seleniumlearner2@gmail.com, retrieves the mail item, and opens it. The WindowTitle property value 
 of the opened window is retrieved and compared to the remembered Subject string. If the unique 
 subject string content is contained in the WindowTitle string, this means that the opened mail item
 is the one that was sent from this test class instantiation.
 
 There is one additional class file to manage WebDriver instantiation. DriverStarter.java contains the code
 to start Chrome, Firefox, and IE drivers.  This class contains driver enumerations of CHROME, FIREFOX, 
 and IE. The driver of interest can be hard-coded into the StartDriver() method so that all tests will
 be run against this driver. Alternatively, StartDriver(enum DriverType) could be invoked so that a single
 test class could be run against a non-"default" driver.  NB: the IE driver currently does not work right
 as xpath locators that work with the Chrome and Firefox drivers do not work with the IE driver. 
 
 
 
   
  