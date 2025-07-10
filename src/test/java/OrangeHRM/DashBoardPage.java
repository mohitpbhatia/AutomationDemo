package OrangeHRM;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DashBoardPage {
	WebDriver driver;

	// constructor
	DashBoardPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this); // MANDATORY
	}

	// Locators
	/*
	 * LOGOUT CODE
	 * 
	 * @FindBy(xpath = "//span[@class='oxd-userdropdown-tab']") WebElement
	 * profile_drpdown;
	 * 
	 * 
	 * @FindBy(xpath = "//a[normalize-space()='Logout']") WebElement link_logout;
	 * 
	 * public void clickProfileDrpdown() { profile_drpdown.click();
	 * 
	 * }
	 * 
	 * public void clickLogoutBtn() { link_logout.click();
	 * 
	 * }
	 */
// //aside[@class='oxd-sidepanel']//li
	@FindBy(xpath = "//aside[@class='oxd-sidepanel']//li")
	List<WebElement> leftpaneList;

	public int listofleftpaneitems() {

		return leftpaneList.size();
	}

}
