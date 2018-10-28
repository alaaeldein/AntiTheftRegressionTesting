package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AdminToolPortalUserPage extends PageBase{

	public AdminToolPortalUserPage(WebDriver driver) {
		super(driver);
	}

	@FindBy (css = "img.img_new")
	WebElement addNewPortalUserButton;
	
	public void openAddNewPortalUserPage () {
		
		clickButton(addNewPortalUserButton);
	}
}
