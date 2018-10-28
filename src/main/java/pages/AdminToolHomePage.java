package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class AdminToolHomePage extends PageBase{

	public AdminToolHomePage(WebDriver driver) {
		super(driver);
	}

	@FindBy (css = "span#LoginName1.nav")
	public WebElement loginedUserName;

	@FindBy (xpath = "//*[@id=\"qm0\"]/li[2]/a")
	WebElement usersListTab;
	
	@FindBy (linkText = "Registration Portal Users")
	WebElement registrationPortalUsersLink;
	
	@FindBy (xpath = "//*[@id=\"qm0\"]/li[6]/a")
	WebElement registrationTab;
	
	@FindBy (linkText = "Requests")
	WebElement requestsPage;
	
	
	public void openRegistrationPortalUsersPage () {
		
		clickButton(usersListTab);
		clickButton(registrationPortalUsersLink);
	}
	
	public void openRequestsPage () {
		
		clickButton(registrationTab);
		clickButton(requestsPage);
	}
	
	
	
}
