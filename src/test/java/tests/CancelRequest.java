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
import pages.PortalCancelledListPage;
import pages.PortalLoginPage;
import pages.PortalPendingListPage;
import pages.PortalSearchField;

public class CancelRequest extends TestBase {

	PortalLoginPage portalLoginObj;
	PortalAccountsPage portalAccountsObj;
	PortalAddNewAccountPage portalAddNewAccountObj;
	PortalPendingListPage portalPendingListObj;
	PortalSearchField portalSearchFieldObj;
	AdminToolRequestsPage adminToolRequestsObj;
	AdminToolLoginPage adminToolLoginObj;
	AdminToolHomePage adminToolHomeObj;
	PortalCancelledListPage portalCancelledListObj;

	/*// login into portal
	@DataProvider(name = "login into portal")
	public Object[][] loginIntoPortalData() throws IOException {

		ExcelReader excelReaderObj = new ExcelReader();

		return excelReaderObj.getExcelData(2, 1, 1);

	}

	@Test(priority = 0, dataProvider = "login into portal")
	public void loginIntoPortal(String portalURL) {

		driver.get(portalURL);

		portalLoginObj = new PortalLoginPage(driver);
		portalLoginObj.loginIntoPortal(portalUserName, portalPassword);

		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.or(ExpectedConditions.visibilityOfElementLocated(By.id("addNewAccount"))));

		Assert.assertTrue(driver.findElement(By.id("addNewAccount")).isDisplayed());

	}
*/
	
	// openAddNewAccountPopup
	@Test(priority = 0)
	public void openAddNewAccountPopup1() throws InterruptedException {

		// get fresh winHandles to ArrayList
		ArrayList<String> winHandles = new ArrayList<String>(driver.getWindowHandles());

		// select tab by index
		driver.switchTo().window(winHandles.get(1));
		
		driver.navigate().refresh();
		
		portalAccountsObj = new PortalAccountsPage(driver);
		portalAccountsObj.openAddNewAccountPage();

		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("consumerFirstName")));

		Assert.assertTrue(driver.findElement(By.id("consumerFirstName")).isDisplayed());

	}

	// addNewAccountRequest
	@DataProvider(name = "Cancel Request")
	public Object[][] addNewAccountData1() throws IOException {

		ExcelReader excelReaderObj = new ExcelReader();

		return excelReaderObj.getExcelData(4, 1, 20);
	}

	@Test(priority = 1, dependsOnMethods = "openAddNewAccountPopup1", dataProvider = "Cancel Request")
	public void addNewAccountRequest1(String consumerNationalID, String consumerFirstName, String consumerLastName,
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

		Thread.sleep(2000);
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.or(ExpectedConditions.visibilityOfElementLocated(By.id("addNewAccount"))));

		Assert.assertTrue(driver.findElement(By.id("addNewAccount")).isDisplayed());

	}

	// cancel the request from Pending List
	@DataProvider(name = "Consumer National ID")
	public Object[][] searchWithConsumerNationalID1() throws IOException {

		ExcelReader excelReaderObj = new ExcelReader();

		return excelReaderObj.getExcelData(4, 1, 1);
	}

	@Test(priority = 2, dataProvider = "Consumer National ID", dependsOnMethods = "addNewAccountRequest1")
	public void cancelThisRequest(String NationalID) throws InterruptedException, AWTException {
	
		Thread.sleep(2000);
		portalSearchFieldObj = new PortalSearchField(driver);
		portalSearchFieldObj.searchRequest(NationalID);
		Thread.sleep(2000);
		portalPendingListObj = new PortalPendingListPage(driver);
		portalPendingListObj.clickCancelRequest();
		String expectedMessage = "No records to display";
		String actualMessage = driver.findElement(By.cssSelector("p.text-center")).getText();
		Assert.assertEquals(actualMessage, expectedMessage);
	}

	@Test(priority = 3, dataProvider = "Consumer National ID", dependsOnMethods = "cancelThisRequest")
	public void checkCancelledRequestInCancelledPage(String NationalID) throws InterruptedException {

		portalCancelledListObj = new PortalCancelledListPage(driver);
		portalCancelledListObj.openCancelledListPage();

		portalSearchFieldObj = new PortalSearchField(driver);
		portalSearchFieldObj.searchRequest(NationalID);

		Assert.assertTrue(driver.findElement(By.xpath(
				"/html/body/app-root/div[2]/request-management/div[2]/app-cancelledrequest/table/tbody/tr[1]/td[9]/button"))
				.isDisplayed());

	}

/*
	// check cancel request in admin page
	@DataProvider(name = "login into admin tool")
	public Object[][] loginIntoAdminToolData() throws IOException {

		ExcelReader excelReaderObj = new ExcelReader();

		return excelReaderObj.getExcelData(0, 1, 3);

	}

	@Test(priority = 5, dataProvider = "login into admin tool", dependsOnMethods = "cancelThisRequest")
	public void openRequestsPageInAdminTool(String adminURL, String adminUserName, String adminPassword)
			throws InterruptedException, AWTException {

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

		driver.get(adminURL);

		adminToolLoginObj = new AdminToolLoginPage(driver);
		adminToolLoginObj.loginIntoAdminTool(adminUserName, adminPassword);

		adminToolHomeObj = new AdminToolHomePage(driver);
		adminToolHomeObj.openRequestsPage();

		Assert.assertTrue(driver.findElement(By.id("ctl00_ContentPlaceHolder1_btn_Search_input")).isDisplayed());

	}
*/
	
	@Test(priority = 4, dataProvider = "Consumer National ID", dependsOnMethods = "checkCancelledRequestInCancelledPage")
	public void checkCancelledRequestInAdminTool(String NationalID) {

		// get fresh winHandles to ArrayList
		ArrayList<String> winHandles = new ArrayList<String>(driver.getWindowHandles());

		// select tab by index
		driver.switchTo().window(winHandles.get(0));
				
		adminToolRequestsObj = new AdminToolRequestsPage(driver);
		adminToolRequestsObj.searchWithNationalID(NationalID);

		String expectedStatus = "Cancelled";
		String actualStatus = driver
				.findElement(By.xpath("//*[@id=\"ctl00_ContentPlaceHolder1_radGrid_RequestList_ctl00__0\"]/td[8]/span"))
				.getText();

		Assert.assertEquals(actualStatus, expectedStatus);
	}

}
