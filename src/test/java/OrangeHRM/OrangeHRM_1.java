package OrangeHRM;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class OrangeHRM_1 {

	public static void main(String[] args) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		driver.manage().window().maximize();

		// LOGIN
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
		driver.quit();
	}

}
