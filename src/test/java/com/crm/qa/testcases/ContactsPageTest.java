package com.crm.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.ContactsPage;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.util.TestUtil;

public class ContactsPageTest extends TestBase
{	
	LoginPage loginPage;
	HomePage homePage;
	ContactsPage contactsPage;
	TestUtil testUtil;
	String sheetName = "Contacts" ;
	
	public ContactsPageTest() 
	{
		super();
	}
	@BeforeMethod
	public void setUp() throws InterruptedException
	{
		initialization();
		contactsPage = new ContactsPage();
		loginPage = new LoginPage();
		homePage = new HomePage();
		testUtil = new TestUtil();
		homePage=loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
		testUtil.switchToFrame();
		homePage.clickOnContactsLink();
	}

	@Test(priority=1)
	public void verifyContactsLabelTest()
	{
		Assert.assertTrue(contactsPage.verifyContactsLabel(),"contacts Label is missing on the page");
	}
	@Test(priority=2)
	public void selectSingleContactsByNameTest() throws InterruptedException
	{
		contactsPage.selectContactsByName("Raghava Krishna");
		Thread.sleep(2000);
	}
	@Test(priority=3)
	public void selectMultipleContactsByNameTest() throws InterruptedException
	{
		contactsPage.selectContactsByName("Raghava Krishna");
		contactsPage.selectContactsByName("Palla Sridevi");
		Thread.sleep(2000);
	}
	@DataProvider
	public Object[][] getCRMTestData()
	{
		Object data[][] =TestUtil.getTestData(sheetName);
		return data;
	}
	@Test(priority = 4, dataProvider="getCRMTestData")
	public void validateCreateNewContact(String title, String firstname,String lastname,String company)
	{
		homePage.clickOnNewContactLink();
//		contactsPage.createNewContact("Mr.", "Tom", "Peter", "Google");
		contactsPage.createNewContact(title, firstname, lastname, company);
	}


	@AfterMethod
	public void tearDown()
	{
		driver.quit();
	}
}
