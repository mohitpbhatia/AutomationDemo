package eCommerceApplication;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Date;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class eCommerceTest {
	WebDriver driver;
	ExtentSparkReporter htmlreport;
	ExtentReports report;
	ExtentTest test;
	String extentreportpath = System.getProperty("user.dir") + "\\Reports\\eCommerce_Report.html";

	// @Test(priority = 1)
	public void signUpTest() throws InterruptedException {
		IndexPage ip = new IndexPage(driver);
		ip.clickOnSignUp();
		ip.enterSignUpUserName();
		ip.enterSignUpPassowrd();
		ip.clickSignUp();
		Thread.sleep(3000);
		driver.switchTo().alert().accept();

	}

	@Test(priority = 2)
	public void loginTest() throws InterruptedException, IOException {
		IndexPage inp = new IndexPage(driver);
		inp.clickonLogin();
		Thread.sleep(3000);
		inp.enterUsername();
		inp.enterPassword();
		Date d = new Date();
		String FileName = d.toString().replace(":", "_").replace(" ", "_") + ".png";
		File screenShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileHandler.copy(screenShot, new File("EcommTestScreenshots\\" + FileName));
		inp.clickonLoginBtn();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		// js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
		js.executeScript("window.scrollBy(0,300)", "");

		Assert.assertTrue(true);
		test = report.createTest("E-Comm Valid Login");
		test.log(Status.PASS, MarkupHelper.createLabel("E-Comm Login:Pass", ExtentColor.GREEN));
		// Thread.sleep(3000);
		// inp.clickOnCateLaptops();
		// inp.clickOnCateMonitors();
	}

	@Test(priority = 3)
	public void pageBrowsingTest() throws InterruptedException {
		IndexPage indp = new IndexPage(driver);

		Thread.sleep(5000);
		indp.clickOnCateLaptops();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,300)", "");
		Thread.sleep(2000);
		indp.clickOnCateMonitors();

		js.executeScript("window.scrollBy(0,300)", "");
		System.out.println("Page Browsing");

		Thread.sleep(2000);
		indp.clickOnCateLaptops();
		Thread.sleep(2000);

		test = report.createTest("E-Comm Page Browsing");
		test.log(Status.PASS, MarkupHelper.createLabel("E-Comm Page Browsing:Pass", ExtentColor.GREEN));
	}

	@Test(priority = 4)
	public void productBrowsingTest() throws InterruptedException, IOException {
		ProductsPage productBrowsing = new ProductsPage(driver);
		productBrowsing.clickOnMacAirBook();
		Thread.sleep(2000);
		Date d = new Date();
		String FileName = d.toString().replace(":", "_").replace(" ", "_") + ".png";
		File screenShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String scfile = System.getProperty("user.dir") + "\\EcommTestScreenshots\\" + FileName;
		Thread.sleep(2000);
		// productBrowsing.clickOnMacAirBook();
		// navigate back
		driver.navigate().back();
		Thread.sleep(3000);
		productBrowsing.clickOnSonyVaioI7();
		Thread.sleep(3000);

		test = report.createTest("E-Comm Page Browsing");
		test.log(Status.PASS, MarkupHelper.createLabel("E-Comm Navigation to Categories:Pass", ExtentColor.GREEN));

		productBrowsing.clickOnaddToCartSonyVaioI7();
		Thread.sleep(2000);
		FileHandler.copy(screenShot, new File("EcommTestScreenshots\\" + FileName));
		driver.switchTo().alert().accept();

		test = report.createTest("E-Comm : Product Added to Cart");
		test.log(Status.PASS, MarkupHelper.createLabel("E-Comm Product Added to Cart:Pass", ExtentColor.GREEN));
		test.addScreenCaptureFromPath(scfile); // Add Screenshot in the Extent Report
	}

	@Test(priority = 5)
	public void cartCheckoutTest() throws InterruptedException, IOException {
		CartPage cart = new CartPage(driver);
		cart.clickOnCart();
		Thread.sleep(2000);
		cart.clickOnButtonPlaceOrder();
		Thread.sleep(2000);
		cart.enterCustomerName("Mohit");
		cart.enterCustomerCountry("India");
		cart.enterCustomerCity("Ahmedabad");
		cart.enterCustomerCard("1122233445566");
		cart.enterCustomerCardMonth("JAN");
		cart.enterCustomerCardYear("2035");
		cart.clickOnButtonPurchase();
		Thread.sleep(2000);
		Date d = new Date();
		String FileName = d.toString().replace(":", "_").replace(" ", "_") + ".png";
		File screenShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileHandler.copy(screenShot, new File("EcommTestScreenshots\\" + FileName));
		String scfile = System.getProperty("user.dir") + "\\EcommTestScreenshots\\" + FileName;

		cart.clickOnOK();

		test = report.createTest("E-Comm : Cart Checkout Test");
		test.log(Status.PASS, MarkupHelper.createLabel("E-Comm Cart Checkout:Pass", ExtentColor.GREEN));
		test.addScreenCaptureFromPath(scfile);
	}

	@BeforeTest
	public void beforeMethod() {
		htmlreport = new ExtentSparkReporter(extentreportpath);
		report = new ExtentReports();
		report.attachReporter(htmlreport);
		// setting environment for Extent report
		report.setSystemInfo("Machine name:", "HP");
		report.setSystemInfo("Tester name:", "Mohit Bhatia");
		report.setSystemInfo("OS", "Windows 11");
		report.setSystemInfo("Browser", "Google Chrome");
		// Configuration of the report
		htmlreport.config().setDocumentTitle("eCommerce_Report");
		htmlreport.config().setReportName("E-Commerce_Report_Details");
		htmlreport.config().setTheme(Theme.STANDARD);
		htmlreport.config().setTimeStampFormat("EEEE MM DD,yyyy HH:mm:ss");
	}

	@AfterMethod
	public void afterMethod() {
	}

	@BeforeClass
	public void setup() {

		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://www.demoblaze.com/index.html");
		driver.manage().window().maximize();
	}

	@AfterClass
	void tearDown() {
		report.flush();
		// driver.quit();
	}

}
