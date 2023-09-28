package Project2;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class testCase19 {
	static WebDriver driver;
	static SoftAssert softAssertion = new SoftAssert();
	static pageObject obj;

	@BeforeTest
	public static void setUp() {
		pageObject.setup();
		driver = pageObject.getDriver();
		obj = new pageObject(driver);
		driver.manage().window().maximize();
	}

	@AfterTest
	public static void teardown() {
		pageObject.close();
	}

	@Test
	public static void products() {
		obj.getElement(By.xpath("//a[@href='/products']")).click();
		boolean brandDisplayed = obj.getElement(By.xpath("//h2[contains(text(),'Brands')]")).isDisplayed();
		softAssertion.assertEquals(brandDisplayed, true);
		WebElement polo = obj.getElement(By.xpath("//a[contains(@href,'Polo')]"));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", polo);
		String url = driver.getCurrentUrl();
		String expectedUrl = "https://automationexercise.com/brand_products/Polo";
		softAssertion.assertEquals(url, expectedUrl);
		pageObject.explicitSync(By.xpath("//h2[text()='Brand - Polo Products']"));
		boolean poloProdDisplayed = obj.getElement(By.xpath("//h2[text()='Brand - Polo Products']")).isDisplayed();
		softAssertion.assertEquals(poloProdDisplayed, true);
	}
}
