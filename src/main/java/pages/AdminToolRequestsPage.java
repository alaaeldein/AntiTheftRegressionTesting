package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AdminToolRequestsPage extends PageBase{

	public AdminToolRequestsPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy (id = "ctl00_ContentPlaceHolder1_txt_NationaId")
	WebElement searchNationalIDFieldTextBox;
	
	@FindBy (id = "ctl00_ContentPlaceHolder1_btn_Search_input")
	WebElement searchButton;
	
	@FindBy (id= "ctl00_ContentPlaceHolder1_btn_Reset_input")
	WebElement resetButton;
	
	@FindBy (xpath = "//*[@id=\"ctl00_ContentPlaceHolder1_radGrid_RequestList_ctl00__0\"]/td[11]/a")
	WebElement editRequestButton;
	
	@FindBy (id= "ContentPlaceHolder1_btn_Cancel")
	WebElement closeEditRequestPageButton;
	
	public void searchWithNationalID (String NationalID) {
		
		setTextValue(searchNationalIDFieldTextBox, NationalID);
		
		clickButton(searchButton);
	}
	
	public void clickResetSearchButton () {
		
		clickButton(resetButton);
	}
	
	public void openEditRequestPage () {
		
		clickButton(editRequestButton);
	}
	
	public void closeEditRequestPage () {
		
		clickButton(closeEditRequestPageButton);
	}

}
