package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PortalSearchField extends PageBase{

	public PortalSearchField(WebDriver driver) {
		super(driver);
	}
	
	@FindBy (name = "searchParam")
	WebElement searchFieldTextBox;
	
	@FindBy (id = "submitSearch")
	WebElement submitSearchButton;
	
	public void searchRequest (String NationalID) throws InterruptedException {
		
		Thread.sleep(2000);

		setTextValue(searchFieldTextBox, NationalID);
		clickButton(submitSearchButton);
		
		Thread.sleep(2000);

	}

}
