package OrangeHRM;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	WebDriver driver;

	// constructor
	LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this); // MANDATORY
	}

	// Locators

	@FindBy(xpath = "//input[@placeholder='Username']")
	WebElement txt_username;

	@FindBy(xpath = "//input[@placeholder='Password']")
	WebElement txt_password;

	@FindBy(xpath = "//button[normalize-space()='Login']")
	WebElement btn_login;

	@FindBy(tagName = "a")
	List<WebElement> links;

	// Action methods

	public void setUserName(String user) {
		txt_username.sendKeys(user);
	}

	public void setPassword(String pwd) {
		txt_password.sendKeys(pwd);
	}

	public void clickLogin() {
		btn_login.click();
	}

}
