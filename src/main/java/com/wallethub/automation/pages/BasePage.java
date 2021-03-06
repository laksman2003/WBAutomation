package com.wallethub.automation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Base Page class -  Super class of all Page classes.
 * @author lakshman.shiva
 * @param <T>
 */
public class BasePage <T extends LoadableComponent<T>> extends LoadableComponent<T>{

protected static final int TIMEOUT = 15;
protected static final int POLLING = 15;
protected WebDriver driver;
protected WebDriverWait wait;

protected BasePage(WebDriver driver){	
	this.driver = driver;	
	wait = new WebDriverWait(driver, TIMEOUT, POLLING);		
}

@Override
protected void isLoaded() throws Error {

}

@Override
protected void load() {
		
}


	
}
