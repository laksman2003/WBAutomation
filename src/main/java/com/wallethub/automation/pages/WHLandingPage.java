package com.wallethub.automation.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import com.wallethub.automation.util.PropertyUtil;

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
	
	@FindBy(css="nav[class*='join'] >ul>li:nth-child(2)>a")
	WebElement login;
	
	@Override
	protected void isLoaded() throws Error {
		try {
				Assert.assertTrue(driver.getCurrentUrl().contains("light"));
				this.wait.until(ExpectedConditions.visibilityOf(this.join));
		}
		catch(Exception e)
		{
			throw new Error();
		}
	}

	@Override
	protected void load() {
		
		driver.get(PropertyUtil.getProperty("produrl"));			
	}
	
	public void login(String email, String password)
	{
	  this.login.click();
	  this.emailid.sendKeys(email);
	  this.passwd.sendKeys(password);
	  this.join.click();
	  
	  ExpectedCondition<Boolean> pageLoadCondition = new
	            ExpectedCondition<Boolean>() {
	                public Boolean apply(WebDriver driver) {                	
	                    return ((JavascriptExecutor)driver).executeScript("return document.readyState").equals("complete");
	                }
	            };
	   wait.until(pageLoadCondition);
	}

}
