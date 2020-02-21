package com.wallethub.automation.pages;

import java.util.List;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import com.wallethub.automation.util.PropertyUtil;

public class WHReviewPage extends BasePage<WHReviewPage> {
	

	@FindBy(css="h1[class*='profile']")
	WebElement pagetitle;
	
	@FindBy(css="div[class*='review-action'] div[class*='rating-box']")
	WebElement ratingbox;
	
	@FindBy(css="textarea[class*='user-input']")
	WebElement comments;
	
	@FindBy(css="div[class^='sbn-action']")
	WebElement submitbtn;
	
	@FindBy(css="div[class*='user-input'] div[class^='dropdown']")
	WebElement dropdowndiv;
	
	@FindBy(css="section[class*='content'] article div[class*='rvtab-ci'][class*='content']")
	List<WebElement> articles;
	
	String selectorcontinuebtn = "div[class$='continue-btn']";
	
	public WHReviewPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(new AjaxElementLocatorFactory(driver, TIMEOUT), this);
	}
	
	@Override
	protected void isLoaded() throws Error {
	  try {
	      Assert.assertTrue(pagetitle.getAttribute("innerHTML").toLowerCase().contains("test insurance"));
	  }
	  catch(Exception e)
	  {
		  throw new Error();
	  }
	}

	@Override
	protected void load() {
		driver.get(PropertyUtil.getProperty("reviewpageurl"));	
	}
	
	
	/**
	 * This method is to hover over the Rating Box
	 * And validates that css property changes.
	 */
	public void hoverRatingBoxAndClick()
	{ 
	  ((JavascriptExecutor)this.driver).executeScript("arguments[0].scrollIntoView()", this.ratingbox);
	  WebElement fifthstar = this.ratingbox.findElements(By.cssSelector("svg")).get(4);
	  new Actions(this.driver).moveToElement(fifthstar).build().perform();
	  Assert.assertTrue(fifthstar.findElements(By.cssSelector("path")).get(1).getAttribute("stroke-linejoin").
	  toLowerCase().contains("miter"));
	  
	  fifthstar.click();
	}
	
	/**
	 * This method will enter and submit the comments
	 * @param reviewcomments
	 */
	public void submitComments(String product, String reviewcomments)
	{
	   //Select product
	   this.dropdowndiv.click();	
	   List<WebElement> items = this.dropdowndiv.findElements(By.cssSelector("li"));
	   for (WebElement item : items)
	   {
		   if(item.getAttribute("innerHTML").toLowerCase().contains(product.toLowerCase()))
		   {
			  item.click();
			  break;
		   }
	   }
		
	   //Enter Comments and submit	
	   this.comments.sendKeys(reviewcomments);
	   this.submitbtn.click();
	   wait.until(ExpectedConditions.
			   presenceOfElementLocated(By.
					   cssSelector(selectorcontinuebtn))).click();
	}
	
	/**
	 * This method will validate if given comment is present in the page
	 * @param comments
	 * @return
	 */
	public boolean validateComments(String comments)
	{
		//Validate
        for (WebElement article : articles)
        {
      	  if (article.getAttribute("innerHTML").toLowerCase().equals(comments.toLowerCase()))
      	  {
      		  return true;
      	  }
      	  else
      	  {
      	     continue;
      	  }
        }
        
        return false;
	}
	
}
