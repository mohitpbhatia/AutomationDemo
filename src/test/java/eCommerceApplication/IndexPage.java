package eCommerceApplication;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class IndexPage {

	WebDriver driver;

	// Date d = new Date();
	// String UserName = d.toString().replace(":", "_").replace(" ", "_");

	// constructor
	IndexPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this); // MANDATORY
	}

//Sign UP
	@FindBy(xpath = "//a[@id='signin2']")
	WebElement signUp;

	@FindBy(xpath = "//input[@id='sign-username']")
	WebElement signUpUsername;

	@FindBy(xpath = "//input[@id='sign-password']")
	WebElement signUpPass;

	@FindBy(xpath = "//button[normalize-space()='Sign up']")
	WebElement btnsignUp;

	// LOGIN
	@FindBy(xpath = "//a[@id='login2']")
	WebElement login;

	// UserName
	@FindBy(xpath = "//input[@id='loginusername']")
	WebElement username;
	// Password
	@FindBy(xpath = "//input[@id='loginpassword']")
	WebElement password;
	// LoginBUtton
	// //button[normalize-space()='Log in']
	@FindBy(xpath = "//button[normalize-space()='Log in']")
	WebElement loginBtn;
	// Click on Category Laptop

	@FindBy(xpath = "//a[3]")
	WebElement cateLaptops;

	@FindBy(xpath = "//a[4]")
	WebElement cateMonitors;

	public void clickOnSignUp() {
		signUp.click();
	}

	public void enterSignUpUserName() {

		signUpUsername.sendKeys("userabcq1");
	}

	public void enterSignUpPassowrd() {
		signUpPass.sendKeys("pass@123");
	}

	public void clickSignUp() {
		btnsignUp.click();
	}

	public void clickonLogin() {
		login.click();
	}

	public void enterUsername() {
		// Date d = new Date();
		// String UserName = d.toString().replace(":", "_").replace(" ", "_");
		username.sendKeys("userabcq1");
		// username.sendKeys(UserName);
	}

	public void enterPassword() {
		password.sendKeys("pass@123");
	}

	public void clickonLoginBtn() {
		loginBtn.click();
	}

	public void clickOnCateLaptops() {
		cateLaptops.click();

	}

	public void clickOnCateMonitors() {
		cateMonitors.click();
	}

}
