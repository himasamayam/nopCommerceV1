package com.nopcommerce.testcases;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.junit.AfterClass;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.nopcommerce.utilities.ReadConfig;

public class Base {

	ReadConfig config = new ReadConfig();
	public static WebDriver driver;
	public String baseUrl = config.getApplicationUrl();
	public String chromepath=config.getChromepath();
	public String firefoxpath=config.getFirefoxpath();
	public String edgepath=config.getEdgepath();
	//public String username=readconfig.getUseremail();
	//public String password=readconfig.getPassword();

	public static Logger logger;

	@BeforeClass
	@Parameters("browser")
	public void setUp(String br) {
		logger = Logger.getLogger("nopcommerce");// specify testcase name ,if not project name
		PropertyConfigurator.configure("log4j.properties");


		// one way- System.setProperty("webdriver.dchrome.driver","C:\\Work\\chromedriver_win32
		// (1)\\chromedriver.exe");
		//2nd way 
		//System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "//Drivers/chromedriver.exe"); // dynamically getting  path
		//3rd useful way
		
		if(br.equals("chrome")) //br stands for browser
		{
		System.setProperty("webdriver.chrome.driver",config.getChromepath());
		driver = new ChromeDriver();
		}
		else if(br.equals("firefox")) 
		{
		System.setProperty("webdriver.gecko.driver",config.getFirefoxpath());
		driver = new FirefoxDriver();
		}
		else if(br.equals("edge")) 
		{
		System.setProperty("webdriver.edge.driver",config.getEdgepath());
		driver = new EdgeDriver();
		}
	}

	@AfterClass
	public void teardown() {
		driver.quit();
	}
	
	public void captureScreen(WebDriver driver,String tname) throws IOException {
		TakesScreenshot ts=(TakesScreenshot)driver;
		File source=ts.getScreenshotAs(OutputType.FILE);
		File target=new File(System.getProperty("user.dir")+"/Screenshots/"+tname+".png");
		FileUtils.copyFile(source, target);
		System.out.println("Screenshot taken");
	}
	public static String randomestring() {
		String generatedString1=RandomStringUtils.randomAlphabetic(5);
		return (generatedString1);
	}
	public static String randomNum() {
		String generatedString2=RandomStringUtils.randomNumeric(5);
		return (generatedString2);
	}

}
