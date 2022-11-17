package com.nopcommerce.testcases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.nopcommerce.pageobjects.LoginPage;
import com.nopcommerce.utilities.XLUtils;

public class TC_LoginDDT_002 extends Base {

	@Test(dataProvider = "logindata")
	public void logintest(String user,String pwd) {
		
		driver.get(baseUrl);
		logger.info("URL is opened...");
		LoginPage lp=new LoginPage(driver);
		lp.setEmail();//lp.setEmail(user);
		logger.info("email been provided");
		lp.setpassword();//lp.setpassword(pwd);
		logger.info("password been provided");
	    lp.clickLogin();
	    logger.info("login button got clicked");
	 	if(driver.getTitle().equals("Dashboard / nopCommerce administration"))
	  		{ Assert.assertTrue(true);
	  			lp.clickLogout();
	  		}
	 	else {
	 		
	 		//captureScreen(driver,"logintest");
	 		Assert.assertTrue(false);
	 		logger.info("Login Failed");
	 	}
	}
	
	@DataProvider(name="logindata")
	public String[][] getData() throws IOException{
		
		String path=System.getProperty("user.dir")+"/src/test/java/nop/commerce/testdata/Logindata.xlsx";
	    
		int rownum=XLUtils.getRowCount(path, "Sheet1");
		int colcount=XLUtils.getCellCount(path, "Sheet1",1);
		
		String logindata[][]=new String[rownum][colcount];
		
		for(int i=1;i<=rownum;i++) {
			for(int j=0;j<=colcount;j++) {
				logindata[i-1][j]=XLUtils.getCellData(path,"Sheet1",i, j);
			}
		}
		return logindata;
	}
	
}
