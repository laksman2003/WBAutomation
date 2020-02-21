package com.wallethub.automation.tests;

import java.io.File;
import java.io.FileNotFoundException;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.wallethub.automation.pages.FBHomePage;
import com.wallethub.automation.pages.FBLandingPage;
import com.wallethub.automation.pages.WHLandingPage;
import com.wallethub.automation.pages.WHReviewPage;
import com.wallethub.automation.util.TestDataUtil;

public class WHTests {
	  
	  public WebDriver driver;
	  
	  @BeforeMethod
	  public void driverSetup()
	  {  
		 String path = System.getProperty("user.dir")+File.separator+"chromedriver.exe";
		 System.setProperty("webdriver.chrome.driver", path);
		 ChromeOptions opt = new ChromeOptions();
		 opt.addArguments("disable-infobars");
		 driver = new ChromeDriver(opt);
		 driver.manage().window().maximize();		 
	  }
	
	  @Test
	  public void testSubmitReview() throws FileNotFoundException
	  {
		  //Get test data
		  String comments = null;
		  String product = null;
		  String email = null;
		  String password = null;
		  comments = TestDataUtil.readCSVFile("testSubmitReview", "reviewcomments");
		  product = TestDataUtil.readCSVFile("testSubmitReview", "productname");
		  email = TestDataUtil.readCSVFile("testSubmitReview", "emailid");
		  password = TestDataUtil.readCSVFile("testSubmitReview", "password");
		  
		  //Login
		  WHLandingPage landinpage = new WHLandingPage(driver).get();
		  landinpage.login(email, password);
		  
		  //Navigate to test company page
		  WHReviewPage reviewpage = new WHReviewPage(driver).get();
          reviewpage.hoverRatingBoxAndClick();
                 
          //Submit Comments and Validate
          reviewpage.submitComments(product, comments);
          Assert.assertTrue(reviewpage.validateComments(comments));
	  }
	  
	  @Test
	  public void testFBStatusMessage() throws FileNotFoundException
	  {
		 //Get Test data
		 String emailid = null;
		 String password = null;
		 String statusmessage = "Test";
		 emailid = TestDataUtil.readCSVFile("testFBStatusMessage", "emailid");
		 password = TestDataUtil.readCSVFile("testFBStatusMessage", "password");
		 
		 //Login FB and create a post
		 FBLandingPage loginpage = new FBLandingPage(this.driver).get();
		 FBHomePage homepage = loginpage.loginFB(emailid, password);
		 homepage.createStatusMessage(statusmessage);
	  }
	  
	  @AfterMethod
	  public void cleanup()
	  {
		  this.driver.quit();
		  this.driver = null;
	  }
}
