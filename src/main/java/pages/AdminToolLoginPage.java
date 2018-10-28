package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AdminToolLoginPage extends PageBase{

	public AdminToolLoginPage(WebDriver driver) {
		super(driver);
	}

	@FindBy (id = "lg_AltairSubscriberManagerLogin_UserName")
	WebElement userNameTextBox;

	@FindBy (id = "lg_AltairSubscriberManagerLogin_Password")
	WebElement passwordTextBox;
	
	@FindBy (id = "lg_AltairSubscriberManagerLogin_LoginButton")
	WebElement loginButton;
	
	public void loginIntoAdminTool (String userName, String password) {
		
		setTextValue(userNameTextBox, userName);
		setTextValue(passwordTextBox, password);
		clickButton(loginButton);
		
	}

}
