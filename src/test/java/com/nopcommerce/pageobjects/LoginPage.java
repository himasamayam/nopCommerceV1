package com.nopcommerce.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;


public class LoginPage {

	WebDriver ldriver;

	public LoginPage(WebDriver rdriver) {

		ldriver = rdriver;
		PageFactory.initElements(rdriver, this);

	}

	@FindBy(xpath = "//input[@type='email']")
	@CacheLookup
	WebElement email;

	@FindBy(id = "Password")
	@CacheLookup
	WebElement password;

	@FindBy(xpath = "//button[text()='Log in']")
    @CacheLookup
	WebElement loginbtn;
	
	@FindBy(xpath = "//a[@href='/logout']")
	@CacheLookup
	WebElement logout;

	public void setEmail() {
		String emailID=ldriver.findElement(By.xpath("//input[@type='email']")).getAttribute("value");
		Assert.assertEquals(emailID,"admin@yourstore.com" );
		
	}

	public void setpassword() {
		String passcode=ldriver.findElement(By.id("Password")).getAttribute("value");
		Assert.assertEquals(passcode,"admin");
	}

	public void clickLogin() {
		loginbtn.click();
		
	}
	public void clickLogout() {
		logout.click();
	}
}
