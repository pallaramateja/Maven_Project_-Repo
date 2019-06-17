package com.crm.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.crm.qa.util.TestUtil;
import com.crm.qa.util.WebEventListener;

public class TestBase {
	
	public static EventFiringWebDriver e_driver;
	public static Properties prop;
	 public static WebDriver driver;
	public WebEventListener eventListener;
	public TestBase()
	{
		try
		{
			prop= new Properties();
			FileInputStream ip = new FileInputStream("F:\\NaveenAlabs\\FreeCRMTest\\src\\main\\java\\com\\crm\\qa\\config\\config.properties");		
			prop.load(ip);
		}catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}catch(IOException e)
		{
			e.printStackTrace();
		}
	}

	public void initialization()
	{
		String browserName = prop.getProperty("browser");
		if(browserName.equalsIgnoreCase("chrome"))
		{
			 System.setProperty("webdriver.chrome.driver", "E:\\Softwares\\SELENIUM SOFTWARES\\64bitAutomationSW\\chromedriver_win64\\chromedriver.exe");
			 	driver = new ChromeDriver();
		}
		else if  (browserName.equalsIgnoreCase("firefox"))
		{
			System.setProperty("webdriver.gecko.driver", "E:\\Softwares\\SELENIUM SOFTWARES\\64bitAutomationSW\\geckodriver-v0.22.0-win64\\geckodriver.exe");
			 driver = new FirefoxDriver();
		}
		
		e_driver = new EventFiringWebDriver(driver);
		//Now create an Object of Event Listener Handler to register it with Event Firing WebDriver
		eventListener = new WebEventListener();
		e_driver.register(eventListener);
		driver=e_driver;
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);
		driver.get(prop.getProperty("url"));
		WebDriverWait wait = new WebDriverWait(driver, TestUtil.EXPLICIT_WAIT);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(prop.getProperty("locator"))));
		
	}
}
