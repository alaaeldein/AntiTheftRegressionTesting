package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PortalLoginPage extends PageBase{

	public PortalLoginPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy (id = "username")
	WebElement userNameTextBox;
	
	@FindBy (id = "password")
	WebElement passwordTextBox;
	
	@FindBy (css = "button.btn.btn-white-rounded.login-btn")
	WebElement loginButton;
	
	public void loginIntoPortal (String userName, String password) {
		
		setTextValue(userNameTextBox, userName);
		setTextValue(passwordTextBox, password);
		clickButton(loginButton);
	}
	

}
