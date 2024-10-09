package extentReportsProjects;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ExtentReportExample extends BaseClass {

	
	@BeforeClass
	public void LaunchApp() throws InterruptedException {
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.get("https://tutorialsninja.com/demo/");
		driver.manage().window().maximize();
		Thread.sleep(2000);
		
	}
	
	@Test(priority=1)
	public void testLogo() {
		boolean status= driver.findElement(By.xpath("//div[@id='logo']/h1/a")).isDisplayed();
		Assert.assertTrue(status);
	}
	
	@Test
	public void testAppUrl() {
		String actualUrl="https://tutorialsninja.com/demo/";
		String currentUrl= driver.getCurrentUrl();
		Assert.assertEquals(actualUrl, currentUrl);
	}
	
	@Test(dependsOnMethods= {"testAppUrl"})
	public void getPageTitle() {
		String actualUrl="Your Stor";
		String currentUrl= driver.getTitle();
		Assert.assertEquals(actualUrl, currentUrl);
	}
	
	@AfterClass
	public void tearDown() {
		driver.close();
	}
}
