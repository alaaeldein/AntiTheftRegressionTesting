package pages;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PortalAddNewAccountPage extends PageBase {

	public PortalAddNewAccountPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(id = "consumerFirstName")
	public WebElement consumerFirstNameTextBox;

	@FindBy(id = "consumerLastName")
	WebElement consumerLastNameTextBox;

	@FindBy(id = "consumerPrimMobileNum")
	WebElement consumerPrimMobileNumTextBox;

	@FindBy(id = "consumerSecondMobileNum")
	WebElement consumerSecondMobileNumTextBox;

	@FindBy(id = "consumerEmail")
	WebElement consumerEmailTextBox;

	@FindBy(id = "consumerNationalID")
	WebElement consumerNationalIDTextBox;

	@FindBy(xpath = "//*[@id=\"consumerUploadNationalIDButton\"]/label")
	WebElement consumerUploadNationalIDButton;

	@FindBy(id = "deviceIMEI")
	WebElement deviceIMEITextBox;

	@FindBy(id = "SIMCardDialNumber")
	WebElement deviceSIMNumTextBox;

	@FindBy(id = "SIMCardSerialNumber")
	WebElement deviceSIMserialTextBox;

	@FindBy(id = "SIMStatusSelection")
	WebElement deviceSIMstatusDropdownList;

	@FindBy(id = "addDeviceButton")
	WebElement deviceAddButton;

	@FindBy(id = "clearDeviceDataButton")
	WebElement deviceClearButton;

	@FindBy(id = "extraUserFirstName")
	WebElement extraUserFirstNameTextBox;

	@FindBy(id = "extraUserLastName")
	WebElement extraUserLastNameTextBox;

	@FindBy(id = "extraUserPrimMobileNum")
	WebElement extraUserPrimaryMobileTextBox;

	@FindBy(id = "extraUserSecMobileNum")
	WebElement extraUserSecondMobileTextBox;

	@FindBy(id = "extraUserEmail")
	WebElement extraUserEmailTextBox;

	@FindBy(id = "extraUserNationalID")
	WebElement extraUserNationalIDTextBox;

	@FindBy(xpath = "//*[@id=\"extraUserUploadNationalIDButton\"]/label")
	WebElement extraUserUploadNationalIDButton;

	@FindBy(id = "extraUserAddButton")
	WebElement extraUserAddButton;

	@FindBy(id = "extraUserClearDataButton")
	WebElement extraUserClearButton;

	@FindBy(id = "additionalDocUploadButton")
	WebElement additionalDocUploadButton;

	@FindBy(id = "SubmitRequestButton")
	WebElement submitRequestButton;

	@FindBy(id = "CloseRequestPageButton")
	WebElement closeRequestPageButton;

	String imagePath1 = System.getProperty("user.dir") + "\\upload\\1.jpg";
	String imagePath2 = System.getProperty("user.dir") + "\\upload\\2.jpg";
	String imagePath3 = System.getProperty("user.dir") + "\\upload\\3.jpg";
	String imagePath4 = System.getProperty("user.dir") + "\\upload\\4.jpg";

	public void insertRequestData(String consumerNationalID, String consumerFirstName, String consumerLastName,
			String consumerPrimMobileNum, String consumerSecondMobileNum, String consumerEmail, String deviceIMEI1,
			String deviceSIMNum1, String deviceSIMSN1, String deviceSIMStatus1, String deviceIMEI2,
			String deviceSIMNum2, String deviceSIMSN2, String deviceSIMStatus2, String extraUserFirstName,
			String extraUserLastName, String extraUserPrimMobNum, String extraUserSecMobNum, String extraUserEmail,
			String extraUserNationalID) throws AWTException, InterruptedException {

		// insert consumer data
		setTextValue(consumerFirstNameTextBox, consumerFirstName);
		setTextValue(consumerLastNameTextBox, consumerLastName);
		setTextValue(consumerPrimMobileNumTextBox, consumerPrimMobileNum);
		setTextValue(consumerSecondMobileNumTextBox, consumerSecondMobileNum);
		setTextValue(consumerEmailTextBox, consumerEmail);
		setTextValue(consumerNationalIDTextBox, consumerNationalID);

		Robot robot = new Robot();
		// ctrl + C
		StringSelection selection = new StringSelection(imagePath1);
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		clipboard.setContents(selection, null);

		clickButton(consumerUploadNationalIDButton);
		robot.delay(3000);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		robot.delay(3000);

		// ctrl + V
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyRelease(KeyEvent.VK_V);
		robot.delay(3000);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		robot.delay(3000);

		// ctrl + C
		StringSelection selection1 = new StringSelection(imagePath2);
		Clipboard clipboard1 = Toolkit.getDefaultToolkit().getSystemClipboard();
		clipboard1.setContents(selection1, null);

		clickButton(consumerUploadNationalIDButton);
		robot.delay(3000);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		robot.delay(3000);

		// ctrl + V
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyRelease(KeyEvent.VK_V);
		robot.delay(3000);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		robot.delay(3000);

		/*********************************************************************************/
		// insert device data
		setTextValue(deviceIMEITextBox, deviceIMEI1);
		setTextValue(deviceSIMNumTextBox, deviceSIMNum1);
		setTextValue(deviceSIMserialTextBox, deviceSIMSN1);
		setTextValue(deviceSIMstatusDropdownList, deviceSIMStatus1);
		Thread.sleep(1000);
		clickButton(deviceAddButton);

		Thread.sleep(3000);

		setTextValue(deviceIMEITextBox, deviceIMEI2);
		setTextValue(deviceSIMNumTextBox, deviceSIMNum2);
		setTextValue(deviceSIMserialTextBox, deviceSIMSN2);
		setTextValue(deviceSIMstatusDropdownList, deviceSIMStatus2);
		Thread.sleep(1000);
		clickButton(deviceAddButton);

		/************************************************************************************/
		// insert extra user data
		setTextValue(extraUserFirstNameTextBox, extraUserFirstName);
		setTextValue(extraUserLastNameTextBox, extraUserLastName);
		setTextValue(extraUserPrimaryMobileTextBox, extraUserPrimMobNum);
		setTextValue(extraUserSecondMobileTextBox, extraUserSecMobNum);
		setTextValue(extraUserEmailTextBox, extraUserEmail);
		setTextValue(extraUserNationalIDTextBox, extraUserNationalID);

		// ctrl + C
		StringSelection selection3 = new StringSelection(imagePath3);
		Clipboard clipboard3 = Toolkit.getDefaultToolkit().getSystemClipboard();
		clipboard3.setContents(selection3, null);

		clickButton(extraUserUploadNationalIDButton);
		robot.delay(3000);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		robot.delay(3000);

		// ctrl + V
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyRelease(KeyEvent.VK_V);
		robot.delay(3000);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		robot.delay(3000);

		// ctrl + C
		StringSelection selection4 = new StringSelection(imagePath4);
		Clipboard clipboard4 = Toolkit.getDefaultToolkit().getSystemClipboard();
		clipboard4.setContents(selection4, null);

		clickButton(extraUserUploadNationalIDButton);
		robot.delay(3000);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		robot.delay(3000);

		// ctrl + V
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyRelease(KeyEvent.VK_V);
		robot.delay(3000);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		robot.delay(3000);

		clickButton(extraUserAddButton);

		/*********************************************************************************************************/
		// submit request
		clickButton(submitRequestButton);

	}

}
