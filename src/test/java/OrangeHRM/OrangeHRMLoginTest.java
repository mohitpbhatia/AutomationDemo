package OrangeHRM;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class OrangeHRMLoginTest {
	WebDriver driver;

	@Test
	public void testLogin() throws IOException, InterruptedException {
		String filePath = System.getProperty("user.dir") + "\\testdata\\testdataworkbook.xlsx";
		int rows = ExcelUtils.getRowCount(filePath, "Sheet1");

		for (int i = 1; i <= rows; i++) {
			// Read the data
			String username = ExcelUtils.getCellData(filePath, "Sheet1", i, 0);
			String password = ExcelUtils.getCellData(filePath, "Sheet1", i, 1);

			driver.findElement(By.xpath("//input[@placeholder='Username']")).sendKeys(username);
			driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys(password);
			driver.findElement(By.xpath("//button[normalize-space()='Login']")).click();
			Thread.sleep(3000);
			String currURL = driver.getCurrentUrl();
			// Assert.assertEquals("https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index",
			// currURL);
			Date d = new Date();
			String FileName = d.toString().replace(":", "_").replace(" ", "_") + ".png";
			File screenShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileHandler.copy(screenShot, new File("TestScreenshots\\" + FileName));
			// D:\Mohit_WebDriverDemos\capstoneprojectstaragile\TestScreenshots

			if (currURL.contains("dashboard")) {
				System.out.println("Successfully Logged In");
				Assert.assertTrue(true);
				ExcelUtils.setCellData(filePath, "Sheet1", i, 2, "Passed");
				ExcelUtils.fillGreenColor(filePath, "Sheet1", i, 2);
				driver.findElement(By.xpath("//span[@class='oxd-userdropdown-tab']")).click();
				Thread.sleep(2000);
				driver.findElement(By.xpath("//a[normalize-space()='Logout']")).click();

			} else {
				System.out.println("Invalid Credentials!!!!Please check Username and Passowrd");
				Assert.assertFalse(false);
				ExcelUtils.setCellData(filePath, "Sheet1", i, 2, "Failed");
				ExcelUtils.fillRedColor(filePath, "Sheet1", i, 2);

			}

		}
	}

	@BeforeMethod
	public void beforeMethod() {
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		driver.manage().window().maximize();
	}

	@AfterMethod
	public void afterMethod() {
		driver.quit();

	}
}
