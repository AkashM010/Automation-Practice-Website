package Project2;

import java.awt.Window;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class testCase10 {
	static WebDriver driver;
	static SoftAssert softAssertion = new SoftAssert();

	@BeforeTest
	public static void setUp() {
		data.setup();
		driver = data.getDriver();
	}

	@AfterTest
	public static void teardown() {
		data.close();
	}

	@Test(priority = 1)
	public static void homePage() {
		String pageTitle = driver.getTitle();
		softAssertion.assertEquals(pageTitle, "Automation Exercise");
	}

	@Test
	public static void footer() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement footerElement = driver.findElement(By.tagName("footer"));
		js.executeScript("arguments[0].scrollIntoView(true);", footerElement);
		boolean subscriptionIsDisplayed = driver.findElement(By.xpath("//h2[text()='Subscription']")).isDisplayed();
		softAssertion.assertEquals(subscriptionIsDisplayed, true);
		driver.findElement(By.id("susbscribe_email")).sendKeys("abc@gmail.com");
		driver.findElement(By.xpath("//button[@id='subscribe']")).click();
		boolean msgDisplayed = driver.findElement(By.xpath("//div[text()='You have been successfully subscribed!']")).isDisplayed();
		softAssertion.assertEquals(msgDisplayed, true);
	}
}
