package Project2;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class testcase25 {

	static WebDriver driver;
	static SoftAssert softAssertion = new SoftAssert();

	@BeforeTest
	public static void setUp() {
		pageObject.setup();
		driver = pageObject.getDriver();
		driver.manage().window().maximize();
	}

	@AfterTest
	public static void teardown() {
		pageObject.close();
	}

	@Test(priority = 1)
	public static void homePage() {
		String pageTitle = driver.getTitle();
		softAssertion.assertEquals(pageTitle, "Automation Exercise");
	}

	@Test (priority = 2)
	public static void scroll() throws Exception {
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,10000);");
		boolean subscriptionIsDisplayed = driver.findElement(By.xpath("//h2[text()='Subscription']")).isDisplayed();
		softAssertion.assertEquals(subscriptionIsDisplayed, true);
		Thread.sleep(2000);
		driver.findElement(By.id("scrollUp")).click();
		Thread.sleep(2000);
		boolean actual = driver.findElement(By.xpath("//*[text()='Full-Fledged practice website for Automation Engineers']")).isDisplayed();
		softAssertion.assertEquals(actual, true);
	}
}
