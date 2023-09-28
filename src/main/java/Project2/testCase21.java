package Project2;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class testCase21 {
	static WebDriver driver;
	static SoftAssert softAssertion = new SoftAssert();
	
	@BeforeTest
	public static void setUp() {
		pageObject.setup();
		driver = pageObject.getDriver();
	}
	
	@AfterTest
	public static void teardown() {
		pageObject.close();
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
		boolean review = driver.findElement(By.xpath("//a[contains(text(),'Write Your Review')]")).isDisplayed();
		softAssertion.assertEquals(review, true);
		WebElement name = driver.findElement(By.id("name"));
		name.sendKeys("Random");
		WebElement email = driver.findElement(By.id("email"));
		email.sendKeys("random@gmail.com");
		WebElement writeReview = driver.findElement(By.id("review"));
		writeReview.sendKeys("Review entered here");
		driver.findElement(By.id("button-review")).click();
		boolean msg = driver.findElement(By.xpath("//*[text()='Thank you for your review.']")).isDisplayed();
		softAssertion.assertEquals(msg, true);
	}
}
