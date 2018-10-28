package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PortalCancelledListPage extends PageBase {

	public PortalCancelledListPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//*[@id=\"navMenuBtn\"]/span")
	WebElement sideMenuButton;

	@FindBy(linkText = "Cancelled")
	WebElement openCancelledListButton;

	@FindBy(xpath = "/html/body/app-root/div[3]/request-management/div[2]/app-cancelledrequest/table/tbody/tr[1]/td[9]/button")
	WebElement detailsButton;

	public void openCancelledListPage() throws InterruptedException {

		Thread.sleep(2000);

		clickButton(sideMenuButton);

		Thread.sleep(2000);

		clickButton(openCancelledListButton);
	}
	
	public void openDetailsCancelledRequest () {
		
		clickButton(detailsButton);
	}

}
