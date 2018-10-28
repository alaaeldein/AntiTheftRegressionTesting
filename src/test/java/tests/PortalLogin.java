package tests;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import data.ExcelReader;
import pages.AdminToolAddPortalUserPage;
import pages.AdminToolHomePage;
import pages.AdminToolLoginPage;
import pages.AdminToolPortalUserPage;
import pages.PortalLoginPage;

public class PortalLogin extends TestBase {

	AdminToolLoginPage adminToolLoginObj;
	AdminToolHomePage adminToolHomeObj;
	AdminToolPortalUserPage adminToolPortalUsersObj;
	AdminToolAddPortalUserPage adminToolAddPortalObj;
	PortalLoginPage portalLoginObj;

	// login into admin tool

	@DataProvider(name = "login into admin tool")
	public Object[][] loginIntoAdminToolData() throws IOException {

		ExcelReader excelReaderObj = new ExcelReader();

		return excelReaderObj.getExcelData(0, 1, 3);

	}

	@Test(priority = 0, dataProvider = "login into admin tool")
	public void loginIntoAdminTool(String adminURL, String adminUserName, String adminPassword) {

		driver.get(adminURL);
		adminToolLoginObj = new AdminToolLoginPage(driver);
		adminToolLoginObj.loginIntoAdminTool(adminUserName, adminPassword);
		adminToolHomeObj = new AdminToolHomePage(driver);
		Assert.assertTrue(adminToolHomeObj.loginedUserName.getText().contains("admin"));
	}

	// add new portal user

	@DataProvider(name = "add portal user")
	public Object[][] addPortalUserData() throws IOException {

		ExcelReader excelReaderObj = new ExcelReader();

		return excelReaderObj.getExcelData(1, 1, 6);

	}

	@Test(priority = 1, dataProvider = "add portal user")
	public void addNewPortalUser(String fullName, String email, String userName, String password,
			String confirmPassword, String operator) {

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		adminToolHomeObj = new AdminToolHomePage(driver);
		adminToolHomeObj.openRegistrationPortalUsersPage();

		adminToolPortalUsersObj = new AdminToolPortalUserPage(driver);
		adminToolPortalUsersObj.openAddNewPortalUserPage();

		adminToolAddPortalObj = new AdminToolAddPortalUserPage(driver);
		adminToolAddPortalObj.addNewPortalUser(fullName, email, userName, password, confirmPassword, operator);

		String currentURL = "http://41.33.122.55:9090/WebPages/AntiTheft/AntiTheftUsers/UsersList.aspx";
		String actualURL = driver.getCurrentUrl();

		Assert.assertEquals(actualURL, currentURL);
	}

	// login into portal
	@DataProvider(name = "login into portal")
	public Object[][] loginIntoPortalData() throws IOException {

		ExcelReader excelReaderObj = new ExcelReader();

		return excelReaderObj.getExcelData(2, 1, 3);

	}

	@Test(priority = 2, dataProvider = "login into portal")
	public void loginIntoPortal(String portalURL, String portalUserName, String portalPassword) 
			throws AWTException, InterruptedException {

		// open new tab
		Robot rob = new Robot();
		rob.keyPress(KeyEvent.VK_CONTROL);
		rob.keyPress(KeyEvent.VK_T);
		rob.keyRelease(KeyEvent.VK_CONTROL);
		rob.keyRelease(KeyEvent.VK_T);

		// wait to winHandles value increases. Without it will result in random
		// nullPointerException at this point.
		Thread.sleep(100);

		// get fresh winHandles to ArrayList
		ArrayList<String> winHandles = new ArrayList<String>(driver.getWindowHandles());

		// select tab by index
		driver.switchTo().window(winHandles.get(1));

		driver.get(portalURL);

		portalLoginObj = new PortalLoginPage(driver);
		portalLoginObj.loginIntoPortal(portalUserName, portalPassword);

		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.or(ExpectedConditions.visibilityOfElementLocated(By.id("addNewAccount"))));

		Assert.assertTrue(driver.findElement(By.id("addNewAccount")).isDisplayed());

	}

}
