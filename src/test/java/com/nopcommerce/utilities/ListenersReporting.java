package com.nopcommerce.utilities;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ListenersReporting extends TestListenerAdapter {

	
	public ExtentSparkReporter htmlreporter; //acts as template
	public ExtentReports extent; // send the user information
	public ExtentTest logger; // send pass/fail status

	public void onStart(ITestContext testContext) {
        String timeStamp=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());//timeStamp
	    String repName= "Test-Report-"+timeStamp + ".html"; //.html bcoz it is web based
	    htmlreporter=new ExtentSparkReporter(System.getProperty("user.dir")+"/test-output/"+repName);//Specifies new name along with timestamp for easy understanding
	    
			try {
				htmlreporter.loadXMLConfig(System.getProperty("user.dir")+"/extent-config.xml");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			extent=new ExtentReports();
			
			extent.attachReporter(htmlreporter);
			extent.setSystemInfo("Host Name","localHost");
			extent.setSystemInfo("Environment","QA");
			extent.setSystemInfo("user","Hima");
			extent.setSystemInfo("os","windows");
			
			htmlreporter.config().setDocumentTitle("nopCommerce Test Project");//title of report
			htmlreporter.config().setReportName("Functional Test report");//name of the report
			htmlreporter.config().setTheme(Theme.DARK);
			
		   
	}

	@Override
	public void onTestSuccess(ITestResult tr) {
         logger=extent.createTest(tr.getName()); //create new entry in the report
         logger.log(Status.PASS,MarkupHelper.createLabel(tr.getName(),ExtentColor.GREEN));//send passed info in Green font
	}

	@Override
	public void onTestFailure(ITestResult tr) {
		logger=extent.createTest(tr.getName()); //create new entry in the report
        logger.log(Status.PASS,MarkupHelper.createLabel(tr.getName(),ExtentColor.RED));
	    String screenshotPath=System.getProperty("user.dir")+"\\Screenshots\\"+tr.getName()+".png";
	    
	    File f= new File(screenshotPath);
	    if(f.exists()) {
	    	
	    	logger.fail("Screenshot is below: "+ logger.addScreenCaptureFromPath(screenshotPath));
	    	
	    }
	}

	@Override
	public void onTestSkipped(ITestResult tr) {
		 logger=extent.createTest(tr.getName()); //create new entry in the report
         logger.log(Status.SKIP,MarkupHelper.createLabel(tr.getName(),ExtentColor.ORANGE));//send passed info in Orange font
	
	}

	@Override
	public void onFinish(ITestContext testContext) {
             extent.flush();
	}

}
