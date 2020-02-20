package com.wallethub.automation.tests;

import java.io.File;
import java.io.FileNotFoundException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.wallethub.automation.pages.GreatDecisionPage;
import com.wallethub.automation.pages.WHLandingPage;
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
	  
	  /**
	   * This test will signup a new account and validates
	   * if next page with title 'Great Decision' appears
	   * @throws FileNotFoundException
	   */
	  @Test
	  public void testSignup() throws FileNotFoundException
	  {   
		  //Get Test Data
		  String emailid = null;
		  String password = null;
		  emailid = TestDataUtil.readCSVFile("testSignup", "emailid");
		  password = TestDataUtil.readCSVFile("testSignup", "password");
		  
		  //Launch App and validate
		  WHLandingPage signuppage = new WHLandingPage(driver).get();  
		  GreatDecisionPage gdpge  = signuppage.signup(emailid, password);
	  }
}
