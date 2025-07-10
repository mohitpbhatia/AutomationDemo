package OrangeHRM;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class LoginTest {

	WebDriver driver;

	@BeforeClass
	void setup() {
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		driver.manage().window().maximize();
		// Extent Reports

	}

	@Test(priority = 1)
	void testLogin() throws IOException, InterruptedException {

		LoginPage lp = new LoginPage(driver);
		lp.setUserName("Admin");
		lp.setPassword("admin123");
		lp.clickLogin();

		String currURL = driver.getCurrentUrl();

		System.out.println(currURL);
		// String dashBoardURL =
		// "https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index";
		if (currURL.contains("dashboard")) {
			System.out.println("Successfully Logged In!!!!");

		}

	}

	@Test(priority = 2)
	public void countLeftPaneListItems() {
		DashBoardPage dp = new DashBoardPage(driver);
		int totalitems = dp.listofleftpaneitems();
		System.out.println("Total itmes in the left pane are :" + totalitems);
	}

	@Test(priority = 3)
	public void adminPanel() throws InterruptedException {
		AdminPage ap = new AdminPage(driver);
		ap.clickonAdmin();
		Thread.sleep(3000);

		// ap.searchByUserName("Admin");
		// ap.clickonSearchButton();
	}

	@Test(priority = 3)
	public void searchUserTest() throws InterruptedException {
		AdminPage adp = new AdminPage(driver);
		adp.searchByUserName("Admin");
		adp.clickonSearchButton();
		Thread.sleep(3000);
		System.out.println("Total number of Admin : " + adp.totalnoOfUsers());
		driver.navigate().refresh();

		// clickonUserRoleDrpDown

	}

	@Test(priority = 4)
	public void searchUserByRoleTest() throws InterruptedException {
		AdminPage admp = new AdminPage(driver);
		admp.clickonUserRoleDrpDown();
		Thread.sleep(3000);
		admp.selectUserRoleAdmin();
		// clickonUserRoleDrpDown
		Thread.sleep(3000);
		admp.clickonSearchButton();
		Thread.sleep(3000);
		System.out.println("Total number of Admin Role Users : " + admp.totalnoOfAdminUsers());
		driver.navigate().refresh();

	}

	@Test(priority = 5)
	public void searchUserByStatus() throws InterruptedException {
		AdminPage adminp = new AdminPage(driver);
		adminp.clickonUserStatusDrpDown();

		Thread.sleep(3000);
		adminp.selectStatusEnabled();
		// clickonUserRoleDrpDown
		Thread.sleep(3000);
		adminp.clickonSearchButton();
		Thread.sleep(3000);
		System.out.println("Total number of Users By Status Enabled : " + adminp.totalnoOfAdminUsers());
		driver.navigate().refresh();

	}

	@AfterClass
	void tearDown() {
		driver.quit();
	}

}
