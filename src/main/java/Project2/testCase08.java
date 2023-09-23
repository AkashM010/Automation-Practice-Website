package Project2;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class testCase08 {
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
	
	@Test (priority = 1)
	public static void homePage() {
		String pageTitle = driver.getTitle();
		softAssertion.assertEquals(pageTitle, "Automation Exercise");
	}
	
	@Test
	public static void products() {
		driver.findElement(By.xpath("//a[@href='/products']")).click();
		boolean allProdDisplayed = driver.findElement(By.xpath("//h2[text()='All Products']")).isDisplayed();
		softAssertion.assertEquals(allProdDisplayed, true);
		boolean prodListDisplayed = driver.findElement(By.className("features_items")).isDisplayed();
		softAssertion.assertEquals(prodListDisplayed, true);
		WebElement viewProductLink = driver.findElement(By.xpath("//a[contains(@href,'/product_details/1') and contains(@style,'color: brown;')]"));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", viewProductLink);
		String currentUrl = driver.getCurrentUrl();
		softAssertion.assertEquals(currentUrl, "https://automationexercise.com/product_details/1");
		boolean prodName = driver.findElement(By.xpath("//h2[contains(text(),'Blue Top')]")).isDisplayed();
		softAssertion.assertEquals(prodName, true);
		boolean prodCategory = driver.findElement(By.xpath("//p[contains(text(),'Category: Women > Tops')]")).isDisplayed();
		softAssertion.assertEquals(prodCategory, true);
		boolean prodPrice = driver.findElement(By.xpath("//span[contains(text(),'Rs. 500')]")).isDisplayed();
		softAssertion.assertEquals(prodPrice, true);
		boolean prodAvailability = driver.findElement(By.xpath("//b[contains(text(),'Availability:')]")).isDisplayed();
		softAssertion.assertEquals(prodAvailability, true);
		boolean prodCondition = driver.findElement(By.xpath("//b[contains(text(),'Condition:')]")).isDisplayed();
		softAssertion.assertEquals(prodCondition, true);
		boolean prodBrand = driver.findElement(By.xpath("//p[contains(text(),'Polo')]")).isDisplayed();
		softAssertion.assertEquals(prodBrand, true);
	}
}
