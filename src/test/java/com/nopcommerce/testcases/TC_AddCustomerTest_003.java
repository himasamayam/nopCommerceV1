package com.nopcommerce.testcases;

import java.io.IOException;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.nopcommerce.pageobjects.AddcustomerPage;
import com.nopcommerce.pageobjects.LoginPage;

public class TC_AddCustomerTest_003 extends Base{

	@Test
	public void addNewCustomer() throws IOException {
		//first we need to login to application and go to customers,so we are calling loginapage testcase
		driver.get(baseUrl);
		logger.info("URL is opened...");
		LoginPage lp=new LoginPage(driver);
		lp.setEmail();
		logger.info("email been provided");
		lp.setpassword();
		logger.info("password been provided");
	    lp.clickLogin();
	    logger.info("login button got clicked");
	    
	    //calling methods from page_003
	    logger.info("providing customer details....");
	    
	    AddcustomerPage addcust=new AddcustomerPage(driver);
	     addcust.clickOnCustomersMenu();
	     addcust.clickOnCustomersMenuItem();
	     
	     addcust.clickOnAddnew();
	     
	     String email=randomestring() + "@gmail.com";
	     addcust.setEmail(email);
	     
	     addcust.setPassword("test123");
	     
	     //Registered -default
	     //The customer cannot be in both 'Guests' and 'Registered' customer
	     //Add the customer to 'Guests' or 'registered' customer role
	     addcust.setCustomerRoles("Guest");
	     addcust.setManagerOfVendor("Vendor 2");
	     addcust.setGender("Male");
	     addcust.setFirstName("Pavan");
	     addcust.setLastName("kumar");
	     addcust.setDob("7/05/1985"); //format D/MM/YYYY
	     addcust.setCompanyName("busyQA");
	     addcust.setAdminContent("This is for testing......");
	     addcust.clickOnSave();
	     logger.info("validation started...");
	     
	     //String msg=driver.findElement(By.//div[@class='alert alert-success alert-dismissable']"
	     
	     String msg=driver.findElement(By.tagName("body")).getText();
	     if(msg.contains("The new customer has been added successfully")) {
	    	 Assert.assertTrue(true);
	    	 logger.info("test case passed...");
	     }
	     else {
	    	 captureScreen(driver,"addNewCustomer");
	    	 Assert.assertTrue(false);
	    	 
	     }
	}
	
}
