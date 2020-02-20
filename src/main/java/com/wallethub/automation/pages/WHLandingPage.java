package com.wallethub.automation.pages;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

public class WHLandingPage extends BasePage<WHLandingPage> {
	
	public WHLandingPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(new AjaxElementLocatorFactory(driver, TIMEOUT), this);
	}
	
	@FindBy(css="input[placeholder='Email Address']")
	WebElement emailid;
	
	@FindBy(css="input[placeholder='Password']")
	WebElement passwd;
	
	@FindBy(css="input[placeholder='Confirm Password']")
	WebElement confirmpsswd;	
	
	@FindBy(css="input[type='CheckBox']")
	WebElement getfreereport;
	
	@FindBy(css="button[type='button']")
	WebElement join;
	
	/**
	 * This method is to sign up a new user account
	 * @param emailid
	 * @param password
	 * @return
	 */
	public GreatDecisionPage signup(String emailid, String password)
	{
	    this.emailid.sendKeys(emailid);
	    this.passwd.sendKeys(password);
	    this.confirmpsswd.sendKeys(password);
	    ((JavascriptExecutor )driver).executeScript("arguments[0].click()", this.getfreereport);
	    this.join.click();
		return new GreatDecisionPage(this.driver);
	}
	
	@Override
	protected void isLoaded() throws Error {
		
	Assert.assertTrue(driver.getCurrentUrl().contains("light"));
	this.wait.until(ExpectedConditions.visibilityOf(this.join));
	
	}

	@Override
	protected void load() {
		 
		Properties prop = null; 
		try
		{
		    prop = new Properties();
			prop.load(new FileInputStream(System.getProperty("user.dir")+File.separator+"config.properties"));
			
		}
		catch(Exception e) {}
		driver.get(prop.getProperty("produrl"));			
	}
}
