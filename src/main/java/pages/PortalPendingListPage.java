package pages;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PortalPendingListPage extends PageBase {

	public PortalPendingListPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//*[@id=\"navMenuBtn\"]/span")
	WebElement sideMenuButton;

	@FindBy(linkText = "Pending")
	WebElement openPendingListButton;

	@FindBy(xpath = "/html/body/app-root/div[2]/request-management/div[2]/pending-request/table/tbody/tr[1]/td[9]/button")
	WebElement editButton;
	
	@FindBy (id = "CloseRequestPageButton")
	WebElement closeRequestPageButton;

	@FindBy(xpath = "/html/body/app-root/div[2]/request-management/div[2]/pending-request/table/tbody/tr[1]/td[10]/button")
	WebElement cancelButton;

	public void openPendingListPage() throws InterruptedException {

		Thread.sleep(3000);

		clickButton(sideMenuButton);

		Thread.sleep(2000);

		clickButton(openPendingListButton);
	}

	public void openEditPendingRequest() throws InterruptedException {

		clickButton(editButton);

		Thread.sleep(2000);

	}

	public void closeEditRequestPage() throws InterruptedException {
		
		Thread.sleep(2000);
		clickButton(closeRequestPageButton);
		Thread.sleep(2000);
	}
	
	public void clickCancelRequest() throws InterruptedException, AWTException {

		clickButton(cancelButton);
		Thread.sleep(2000);

		Robot rb =new Robot();

		// pressing keys with the help of keyPress and keyRelease events
		rb.keyPress(KeyEvent.VK_ENTER);
		rb.keyRelease(KeyEvent.VK_ENTER);
		Thread.sleep(2000);

	}

}
