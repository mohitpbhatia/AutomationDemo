package OrangeHRM;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AdminPage {

	WebDriver driver;

	// constructor
	AdminPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this); // MANDATORY
	}

	@FindBy(xpath = "//aside[@class='oxd-sidepanel']//li[1]")
	WebElement admin;

	@FindBy(xpath = "//input[@class='oxd-input oxd-input--focus']")
	WebElement username;

	@FindBy(css = "div[class='oxd-input-group oxd-input-field-bottom-space'] div input[class='oxd-input oxd-input--active']")
	WebElement usern;

	@FindBy(xpath = "//button[normalize-space()='Search']")
	WebElement searchBtn;

	// div[class='oxd-table-card'] div[role='row']
	@FindBy(xpath = "//body[1]/div[1]/div[1]/div[2]/div[2]/div[1]/div[2]/div[3]/div[1]/div[2]/div[1]/div[1]")
	List<WebElement> noOfUsers;

	// //body[1]/div[1]/div[1]/div[2]/div[2]/div[1]/div[2]/div[3]/div[1]/div[2]/div[1]/div[1]/div[2]
	@FindBy(xpath = "//body[1]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/div[2]/form[1]/div[1]/div[1]/div[2]/div[1]/div[2]/div[1]/div[1]/div[2]/i[1]")
	WebElement userRole;

	// //div[@role='listbox']//div[1]
	// //i[@class='oxd-icon bi-caret-up-fill oxd-select-text--arrow']

	@FindBy(xpath = "//div[@role='listbox']//div[2]")
	WebElement userRoleAdmin;

	// //div[@class='oxd-table-body']/div

	@FindBy(xpath = "//div[@class='oxd-table-body']/div")
	List<WebElement> noOfAdminRoleUsers;

	// //div[4]//div[1]//div[2]//div[1]//div[1]//div[1]
	@FindBy(xpath = "//div[4]//div[1]//div[2]//div[1]//div[1]//div[1]")
	WebElement userStatusDrpDown;

	// //div[@role='listbox']//div[1]
	@FindBy(xpath = "//div[@role='listbox']//div[2]")
	WebElement selectEnabled;

	public void clickonAdmin() {
		admin.click();
	}

	public void searchByUserName(String userName) {
		// username.sendKeys(Keys.ENTER);
		usern.sendKeys(userName);
	}

	public void clickonSearchButton() {
		searchBtn.click();
	}

	public int totalnoOfUsers() {
		return noOfUsers.size();
	}

	public void clickonUserRoleDrpDown() {
		userRole.click();
	}

	public void selectUserRoleAdmin() {
		userRoleAdmin.click();

	}

	public int totalnoOfAdminUsers() {
		return noOfAdminRoleUsers.size();
	}

	public void clickonUserStatusDrpDown() {
		userStatusDrpDown.click();
	}

	public void selectStatusEnabled() {
		selectEnabled.click();

	}

}
