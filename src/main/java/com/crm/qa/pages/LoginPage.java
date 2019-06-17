package com.crm.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.qa.base.TestBase;

public class LoginPage extends TestBase
{
	
	@FindBy(xpath = "//input[@placeholder='Username']")
	WebElement username;
	
	@FindBy(xpath="//input[@placeholder='Password']")
	WebElement password;
	
	@FindBy(xpath ="//input[@class='btn btn-small']")
	WebElement loginBtn;
	
	@FindBy(xpath= "//a[contains(.,'Sign Up')]")
	WebElement  signUpBtn;
	
	@FindBy(xpath ="//a[@class='navbar-brand'] /img[@class='img-responsive']")
	WebElement crmLogo;
	
	//Initializing the page Objects:
	public LoginPage()
	{
		PageFactory.initElements(driver, this);
	}
	
	//Actions
	public String validateLoginPageTitle()
	{
		return driver.getTitle();
	}
	
	public boolean validateCRMImage()
	{
		return crmLogo.isDisplayed();
	}
	
	public HomePage login(String un,String pwd) throws InterruptedException
	{
		
		username.sendKeys(un);
		
		password.sendKeys(pwd);
		Thread.sleep(2000);
		
		loginBtn.click();
		
		return new HomePage();
	}
	
}
