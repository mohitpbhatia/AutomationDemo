package eCommerceApplication;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartPage {

	WebDriver driver;

	// constructor
	CartPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this); // MANDATORY
	}

	// Go to Cart
	// //a[@id='cartur']
	@FindBy(xpath = "//a[@id='cartur']")
	WebElement clickonCart;

	// Place order Button
	// //button[normalize-space()='Place Order']
	@FindBy(xpath = "//button[normalize-space()='Place Order']")
	WebElement clickonbtnplaceorder;

	// Form for placing order

	// customer name
	@FindBy(xpath = "//input[@id='name']")
	WebElement customername;

	// customer country
	@FindBy(xpath = "//input[@id='country']")
	WebElement customercountry;

	// city
	@FindBy(xpath = "//input[@id='city']")
	WebElement customercity;

	// credit card
	@FindBy(xpath = "//input[@id='card']")
	WebElement customercreditcard;

	// credit card month
	@FindBy(xpath = "//input[@id='month']")
	WebElement customercreditcardmonth;

	// credit card month
	@FindBy(xpath = "//input[@id='year']")
	WebElement customercreditcardyear;

	//
	// Click on purchase
	@FindBy(xpath = "//button[normalize-space()='Purchase']")
	WebElement finalpurchase;

	// //button[normalize-space()='OK']
	// Click on OK - Thank you for your purchase
	@FindBy(xpath = "//button[normalize-space()='OK']")
	WebElement ok;

	public void clickOnCart() {
		clickonCart.click();
	}

	public void clickOnButtonPlaceOrder() {
		clickonbtnplaceorder.click();
	}

	public void enterCustomerName(String CustomerName) {
		customername.sendKeys(CustomerName);

	}

	public void enterCustomerCountry(String CustomerCountry) {
		customercountry.sendKeys(CustomerCountry);

	}

	public void enterCustomerCity(String CustomerCity) {
		customercity.sendKeys(CustomerCity);

	}

	public void enterCustomerCard(String CustomerCard) {
		customercreditcard.sendKeys(CustomerCard);

	}

	public void enterCustomerCardMonth(String CustomerCardMonth) {
		customercreditcardmonth.sendKeys(CustomerCardMonth);

	}

	public void enterCustomerCardYear(String CustomerCardYear) {
		customercreditcardyear.sendKeys(CustomerCardYear);

	}

	public void clickOnButtonPurchase() {
		finalpurchase.click();
	}

	public void clickOnOK() {
		ok.click();
	}

}
