package com.wallethub.automation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class GreatDecisionPage extends BasePage<GreatDecisionPage> {

	protected GreatDecisionPage(WebDriver driver) {
	    super(driver);
		PageFactory.initElements(new AjaxElementLocatorFactory(driver, TIMEOUT), this);
	}
	
	@FindBy(css="input[type='text'][placeholder='First Name']")
    WebElement firstnme;
	
	@FindBy(css="input[type='text'][placeholder='Last Name']")
    WebElement lastname;
	
	@FindBy(css="a[class*='btn'][class*='blue']")
    WebElement continuebtn;
	
	@Override
	protected void isLoaded() throws Error {
	  this.wait.until(ExpectedConditions.visibilityOf(this.continuebtn));
	}

	@Override
	protected void load() {
	}
}
