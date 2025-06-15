package com.CRM.SeleniumGrid;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class ParallelTest {
	public WebDriver driver;
	@Parameters({"bname"})
  @Test
  public void crossBrowserTest(String bname) throws MalformedURLException, InterruptedException {
	  if(bname.equals("Chrome")) {
		  ChromeOptions options = new ChromeOptions();
		  driver = new RemoteWebDriver(new URL("http://localhost:4444"),options);
		  System.out.println("Session Created on Chrome");
		  
	  }
	  else if (bname.equals("Firefox")) {
		  FirefoxOptions options = new FirefoxOptions();
		  driver = new RemoteWebDriver(new URL("http://localhost:4444"),options);
		  System.out.println("Session Created on Firefox");
		  
	  }
	  else if (bname.equals("Edge")) {
		  EdgeOptions options = new EdgeOptions();
		  driver = new RemoteWebDriver(new URL("http://localhost:4444"),options);
		  System.out.println("Session Created on Edge");
		  
	  }
	  System.out.println("Remote driver connecivity is completed....");
	  Thread.sleep(10000);
	  driver.get("https://www.amazon.in");
	  System.out.println(driver.getTitle());
	  //driver.quit();
	  
  }
}
