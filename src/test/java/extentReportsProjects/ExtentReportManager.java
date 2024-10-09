package extentReportsProjects;

import java.io.File;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportManager extends BaseClass implements ITestListener {
	
	public ExtentSparkReporter spark;
	public ExtentReports extent;
	public ExtentTest test;
	
	
	public void onStart(ITestContext context) {
		
		spark = new ExtentSparkReporter(System.getProperty("user.dir")+"/target/Spark.html");
		extent = new ExtentReports();
		
		spark.config().setDocumentTitle("Tutorial Ninja Report");
		spark.config().setReportName("Functional Testing Report");
		spark.config().setTheme(Theme.STANDARD);
		
		extent.attachReporter(spark);
		
		extent.setSystemInfo("Host name", "Ninja Tutorial");
		extent.setSystemInfo("Environemnt", "QA");
		extent.setSystemInfo("Tester Name", "Preetham");
		//System.out.println("On Started");
		
	}
	
	
	
	public void onTestStart(ITestResult result) {
		
	    System.out.println("Test Case Started");
	  }

	 
	  public void onTestSuccess(ITestResult result) {
		  test=extent.createTest(result.getName());
		  test.log(Status.PASS,"Test Case is Passed  "+result.getName());
	  }

	  public void onTestFailure(ITestResult result) {
		  TakesScreenshot ts = (TakesScreenshot)driver;
		  File sourceFile = ts.getScreenshotAs(OutputType.FILE);
		  File targetfile = new File(System.getProperty("user.dir")+"/target/failurescreenshot.png");
		  sourceFile.renameTo(targetfile);
		  test=extent.createTest(result.getName()).addScreenCaptureFromPath(System.getProperty("user.dir")+"/target/failurescreenshot.png");
		  test.log(Status.FAIL,"Test Case is Failed  "+result.getName());
		  test.log(Status.FAIL,"Test Case is Failed  "+result.getThrowable());
	  }

	
	  public void onTestSkipped(ITestResult result) {
		  test=extent.createTest(result.getName());
		  test.log(Status.SKIP,"Test Case is Skipped  "+result.getName());
	  }
	  
	

	  public void onFinish(ITestContext context) {
	    extent.flush();
	  }

}
