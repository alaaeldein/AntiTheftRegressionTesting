package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AdminToolAddPortalUserPage extends PageBase{

	public AdminToolAddPortalUserPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy (id = "ctl00_ContentPlaceHolder1_Txt_FullName")
	WebElement fullNameTextBox;
	
	@FindBy (id = "ctl00_ContentPlaceHolder1_txt_Email")
	WebElement emailTextBox;
	
	@FindBy (id = "ctl00_ContentPlaceHolder1_txt_UserName")
	WebElement userNameTextBox;
	
	@FindBy (id = "ctl00_ContentPlaceHolder1_txt_Password")
	WebElement passwordTextBox;
	
	@FindBy (id = "ctl00_ContentPlaceHolder1_txt_ConfirmPwd")
	WebElement confirmationPasswordTextBox;
	
	@FindBy (id = "ctl00_ContentPlaceHolder1_rcmb_operators_Input")
	WebElement operatorDropDownList;
	
	@FindBy (id = "ContentPlaceHolder1_btnSave")
	WebElement saveButton;
	
	public void addNewPortalUser (String fullName, String email, String userName,
			String password,String confirmPassword, String operator) {
		
		setTextValue(fullNameTextBox, fullName);
		setTextValue(emailTextBox, email);
		setTextValue(userNameTextBox, userName);
		setTextValue(passwordTextBox, password);
		setTextValue(confirmationPasswordTextBox, confirmPassword);
		setTextValue(operatorDropDownList, operator);
		clickButton(saveButton);
		
	}
	
	
	
	

}
