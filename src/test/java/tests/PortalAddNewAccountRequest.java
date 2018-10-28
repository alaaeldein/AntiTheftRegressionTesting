package tests;

import java.awt.AWTException;
import java.io.IOException;
import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import data.ExcelReader;
import pages.AdminToolHomePage;
import pages.AdminToolLoginPage;
import pages.AdminToolRequestsPage;
import pages.PortalAccountsPage;
import pages.PortalAddNewAccountPage;
import pages.PortalLoginPage;
import pages.PortalPendingListPage;
import pages.PortalSearchField;

public class PortalAddNewAccountRequest extends TestBase {

	PortalLoginPage portalLoginObj;
	PortalAccountsPage portalAccountsObj;
	PortalAddNewAccountPage portalAddNewAccountObj;
	PortalPendingListPage portalPendingListObj;
	PortalSearchField portalSearchFieldObj;
	AdminToolRequestsPage adminToolRequestsObj;
	AdminToolLoginPage adminToolLoginObj;
	AdminToolHomePage adminToolHomeObj;

	/*
	 * // login into portal
	 * 
	 * @DataProvider(name = "login into portal") public Object[][]
	 * loginIntoPortalData() throws IOException {
	 * 
	 * ExcelReader excelReaderObj = new ExcelReader();
	 * 
	 * return excelReaderObj.getExcelData(2, 1, 1);
	 * 
	 * }
	 * 
	 * @Test(priority = 0, dataProvider = "login into portal") public void
	 * loginIntoPortal(String portalURL) throws InterruptedException {
	 * 
	 * driver.get(portalURL);
	 * 
	 * portalLoginObj = new PortalLoginPage(driver);
	 * portalLoginObj.loginIntoPortal(portalUserName, portalPassword);
	 * 
	 * WebDriverWait wait = new WebDriverWait(driver, 30);
	 * wait.until(ExpectedConditions.or(ExpectedConditions.
	 * visibilityOfElementLocated(By.id("addNewAccount"))));
	 * 
	 * Thread.sleep(2000);
	 * Assert.assertTrue(driver.findElement(By.id("addNewAccount")).isDisplayed());
	 * 
	 * }
	 */

	// openAddNewAccountPopup
	@Test(priority = 0)
	public void openAddNewAccountPopup() {

		portalAccountsObj = new PortalAccountsPage(driver);
		portalAccountsObj.openAddNewAccountPage();

		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("consumerFirstName")));

		Assert.assertTrue(driver.findElement(By.id("consumerFirstName")).isDisplayed());

	}

	// addNewAccountRequest

	@DataProvider(name = "Add New Account")
	public Object[][] addNewAccountData() throws IOException {

		ExcelReader excelReaderObj = new ExcelReader();

		return excelReaderObj.getExcelData(3, 1, 20);
	}

	@Test(priority = 1, dependsOnMethods = "openAddNewAccountPopup", dataProvider = "Add New Account")
	public void addNewAccountRequest(String consumerNationalID, String consumerFirstName, String consumerLastName,
			String consumerPrimMobileNum, String consumerSecondMobileNum, String consumerEmail, String deviceIMEI1,
			String deviceSIMNum1, String deviceSIMSN1, String deviceSIMStatus1, String deviceIMEI2,
			String deviceSIMNum2, String deviceSIMSN2, String deviceSIMStatus2, String extraUserFirstName,
			String extraUserLastName, String extraUserPrimMobNum, String extraUserSecMobNum, String extraUserEmail,
			String extraUserNationalID) throws AWTException, InterruptedException {

		portalAddNewAccountObj = new PortalAddNewAccountPage(driver);
		portalAddNewAccountObj.insertRequestData(consumerNationalID, consumerFirstName, consumerLastName,
				consumerPrimMobileNum, consumerSecondMobileNum, consumerEmail, deviceIMEI1, deviceSIMNum1, deviceSIMSN1,
				deviceSIMStatus1, deviceIMEI2, deviceSIMNum2, deviceSIMSN2, deviceSIMStatus2, extraUserFirstName,
				extraUserLastName, extraUserPrimMobNum, extraUserSecMobNum, extraUserEmail, extraUserNationalID);

		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.or(ExpectedConditions.visibilityOfElementLocated(By.id("addNewAccount"))));

		Assert.assertTrue(driver.findElement(By.id("addNewAccount")).isDisplayed());

	}

	// check New Request in Pending List
	@DataProvider(name = "Consumer National ID")
	public Object[][] searchWithConsumerNationalID() throws IOException {

		ExcelReader excelReaderObj = new ExcelReader();

		return excelReaderObj.getExcelData(3, 1, 1);
	}

	@Test(priority = 2, dataProvider = "Consumer National ID", dependsOnMethods = "addNewAccountRequest")
	public void checkNewRequestinPendingList(String NationalID) throws InterruptedException {

		Thread.sleep(2000);

		portalPendingListObj = new PortalPendingListPage(driver);
		portalPendingListObj.openPendingListPage();

		Thread.sleep(2000);

		portalSearchFieldObj = new PortalSearchField(driver);
		portalSearchFieldObj.searchRequest(NationalID);
		Thread.sleep(2000);

		Thread.sleep(2000);
		Assert.assertTrue(driver.findElement(By.xpath("/html/body/app-root/div[2]/request-management/div[2]/"
				+ "pending-request/table/tbody/tr[1]/td[9]/button")).isDisplayed());	
	}

	/*
	 * // make sure the request is added into admin tool
	 * 
	 * @DataProvider(name = "login into admin tool") public Object[][]
	 * loginIntoAdminToolData() throws IOException {
	 * 
	 * ExcelReader excelReaderObj = new ExcelReader();
	 * 
	 * return excelReaderObj.getExcelData(0, 1, 3);
	 * 
	 * }
	 */

	@Test(priority = 3, dependsOnMethods = "checkNewRequestinPendingList")
	public void openRequestsPageInAdminTool() throws InterruptedException, AWTException {

		// get fresh winHandles to ArrayList
		ArrayList<String> winHandles = new ArrayList<String>(driver.getWindowHandles());

		// select tab by index
		driver.switchTo().window(winHandles.get(0));

		adminToolHomeObj = new AdminToolHomePage(driver);
		adminToolHomeObj.openRequestsPage();

		Assert.assertTrue(driver.findElement(By.id("ctl00_ContentPlaceHolder1_btn_Search_input")).isDisplayed());

	}

	@Test(priority = 4, dataProvider = "Consumer National ID", dependsOnMethods = "openRequestsPageInAdminTool")
	public void checkNewRequestInAdminTool(String NationalID) {

		adminToolRequestsObj = new AdminToolRequestsPage(driver);
		adminToolRequestsObj.searchWithNationalID(NationalID);

		adminToolRequestsObj.openEditRequestPage();

		String currentURL = driver.getCurrentUrl();

		Assert.assertTrue(currentURL.contains("RequestID"));
		
		adminToolRequestsObj.closeEditRequestPage();
		
	}

}
