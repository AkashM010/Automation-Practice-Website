package Project2;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class testCase11 {
	static WebDriver driver;
	static SoftAssert softAssertion = new SoftAssert();
	static pageObject obj;

	@BeforeTest
	public static void setUp() {
		pageObject.setup();
		driver = pageObject.getDriver();
		obj = new pageObject(driver);
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

	@Test
	public static void footer() {
		obj.cartButton();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", obj.footer());
		boolean subscriptionIsDisplayed = obj.subscription().isDisplayed();
		softAssertion.assertEquals(subscriptionIsDisplayed, true);
		obj.subscriptionEmail("abc@gmail.com");
		obj.subscribeButton();
		boolean msgDisplayed = obj.subscriptionSuccessMessage().isDisplayed();
		softAssertion.assertEquals(msgDisplayed, true);
	}
}
