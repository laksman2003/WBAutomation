package com.wallethub.automation.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.wallethub.automation.util.PropertyUtil;

public class FBLandingPage extends BasePage<FBLandingPage> {

	public FBLandingPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(new AjaxElementLocatorFactory(driver, TIMEOUT), this);
	}
	 
	@FindBy(css="input[type='email']")
	WebElement email;
	
	@FindBy(css="input[type='password'][class*='login']")
	WebElement passwordtext;
	
	@FindBy(css="input[type='submit']")
	WebElement submit;
	
	@Override
	protected void isLoaded() throws Error {
		try {   
			
			ExpectedCondition<Boolean> pageLoadCondition = new
		            ExpectedCondition<Boolean>() {
		                public Boolean apply(WebDriver driver) {                	
		                    return ((JavascriptExecutor)driver).executeScript("return document.readyState").equals("complete");
		                }
		            };
		   wait.until(pageLoadCondition);
				this.wait.until(ExpectedConditions.visibilityOf(this.submit));
		}
		catch(Exception e)
		{
			throw new Error();
		}
	}
	
	@Override
	protected void load() {
		
		driver.get(PropertyUtil.getProperty("fburl"));			
	}
	
	/**
	 * This method is to login into the facebook
	 * @param emailid
	 * @param password
	 * @return
	 */
	public FBHomePage loginFB(String emailid, String password)
	{
	   email.sendKeys(emailid);
	   passwordtext.sendKeys(password);
	   submit.click();
	   return new FBHomePage(this.driver).get();
	}
}
