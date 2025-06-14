package com.CRM.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {
	private WebDriver driver;
	
	public HomePage(WebDriver driver) {
		this.driver  = driver;
	}
	private By link =  By.linkText("Sign In");
	
	public String getAppUrl() {
		return driver.getCurrentUrl();
	}
	public String getAppTitle()
	{
		return driver.getTitle();
	}
	public String getStatusOflink() {
		driver.findElement(link).click();
		return driver.getCurrentUrl();
	}
}
