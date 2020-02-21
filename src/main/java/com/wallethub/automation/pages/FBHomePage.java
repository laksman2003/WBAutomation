package com.wallethub.automation.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class FBHomePage extends BasePage<FBHomePage> {

	protected FBHomePage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(new AjaxElementLocatorFactory(driver, TIMEOUT), this);

	}
	
	@FindBy(css="div>textarea")
	WebElement textarea;
	
	@FindBy(css="div[role='button'][tabindex='0'][aria-label='Dismiss']")
	WebElement dismisspost;
	
	@FindBy(css="div[role*='presentation'][class*='navigation']>div")
	WebElement postmessagetextarea;
	
	@FindBy(css="div[id='pagelet_composer'] button[type='submit']")
    WebElement postmeesagebtn;
	
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
		}
		catch(Exception e)
		{
			throw new Error();
		}
	}
	
	@Override
	protected void load() {
	}
	
	/**
	 * This method is create a Facebook Post
	 */
	public void createStatusMessage(String post)
	{
	  	new Actions(this.driver).click(this.textarea).build().perform();
	  	this.wait.until(ExpectedConditions.visibilityOf(dismisspost));
	  	this.postmessagetextarea.sendKeys(post);
	  	this.postmeesagebtn.click();
	}
}
