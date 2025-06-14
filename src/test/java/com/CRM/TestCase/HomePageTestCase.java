package com.CRM.TestCase;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.CRM.pages.HomePage;

public class HomePageTestCase extends BaseClass {
  
	
@Test(priority = 1)
  public void verifyUrl() {
	  
	  String actUrl = hp.getAppUrl(); 
	  Assert.assertTrue(actUrl.contains("crm"), "Test Failed: URL not matched");
	  System.out.println("Test Passed : Url Matched");
  }
  
  
  @Test(priority = 2)
  public void verifyTitle() {
	  String actTitle = hp.getAppTitle();
	  Assert.assertTrue(actTitle.contains("Customer"), "Test Failed: Title not matched");
	  System.out.println("Test Passed!Title Matched");
	  }
  
  
  
  @Test(priority = 3)
  public void validateSignInLink() {
	  String nextPageUrl = hp.getStatusOflink();
	  Assert.assertTrue(nextPageUrl.contains("login"), "Test Fail: LoginPage Not Open");
	  System.out.println("Test Pass: Applicaion naviagted to login page");
  }
}
