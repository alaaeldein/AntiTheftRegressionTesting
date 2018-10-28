package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PortalAccountsPage extends PageBase{

	public PortalAccountsPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy (id = "addNewAccount")
	public WebElement addNewAccountButton;
	
	public void openAddNewAccountPage ()  {

		clickButton(addNewAccountButton);
	}

}
