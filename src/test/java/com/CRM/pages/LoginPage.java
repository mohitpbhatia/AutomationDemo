package com.CRM.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
private WebDriver driver;

public LoginPage(WebDriver driver) {
	this.driver = driver;
	
}

private By email = By.id("email-id");
private By pwd = By.id("password");
private By btn = By.name("submit-name");

public String doLogin(String em, String ps) {
	driver.findElement(email).sendKeys(em);
	driver.findElement(pwd).sendKeys(ps);
	driver.findElement(btn).click();
	return driver.getCurrentUrl();
	
}

}
