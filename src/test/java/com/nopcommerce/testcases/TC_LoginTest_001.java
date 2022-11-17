package com.nopcommerce.testcases;
import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.nopcommerce.pageobjects.LoginPage;

public class TC_LoginTest_001 extends Base {

	
	
	@Test
	public void logintest() throws IOException {
		driver.get(baseUrl);
		logger.info("URL is opened...");
		LoginPage lp=new LoginPage(driver);
		lp.setEmail();
		logger.info("email been provided");
		lp.setpassword();
		logger.info("password been provided");
	    lp.clickLogin();
	    logger.info("login button got clicked");
	 	if(driver.getTitle().equals("Dashboard / nopCommerce administration"))
	  		{ Assert.assertTrue(true);
	  			lp.clickLogout();
	  		}
	 	else {
	 		
	 		captureScreen(driver,"logintest");
	 		Assert.assertTrue(false);
	 		logger.info("Login Failed");
	 	}
	}
	
}
