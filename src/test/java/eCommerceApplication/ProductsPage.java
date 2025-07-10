package eCommerceApplication;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductsPage {
	WebDriver driver;

// constructor
	ProductsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this); // MANDATORY
	}

	// MacBook air
	@FindBy(xpath = "//a[normalize-space()='MacBook air']")
	WebElement macbookair;
	// Sony vaio i7
	@FindBy(xpath = "//a[normalize-space()='Sony vaio i7']")
	WebElement sonyvaioi7;
	// Add to cart Sony vaio i7
	@FindBy(xpath = "//a[normalize-space()='Add to cart']")
	WebElement addToCartsonyvaioi7;

	public void clickOnMacAirBook() {
		macbookair.click();
	}

	public void clickOnSonyVaioI7() {
		sonyvaioi7.click();
	}

	public void clickOnaddToCartSonyVaioI7() {
		addToCartsonyvaioi7.click();
	}

}
