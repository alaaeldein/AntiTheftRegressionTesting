package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class PageBase{

	protected WebDriver driver;
	
	public PageBase (WebDriver driver) {
		
		PageFactory.initElements(driver, this);
		
	}
	
	public static void setTextValue (WebElement textBoxElement, String value) {
		
		textBoxElement.sendKeys(value);
	}
	
	public static void clickButton (WebElement button) {
		
		button.click();
	}
	
	public static void selectFromDropdown (WebElement dropdownList, String value) {
		
		Select select = new Select (dropdownList);
		select.selectByVisibleText(value);
	}
}
