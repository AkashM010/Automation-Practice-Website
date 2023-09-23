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
	static boolean size = false;

	@BeforeTest
	public static void setUp() {
		data.setup();
		driver = data.getDriver();
		driver.manage().window().maximize();
	}

	@AfterTest
	public static void teardown() {
		data.close();
	}

	@Test
	public static void products() {
		driver.findElement(By.xpath("//a[@href='/products']")).click();
		boolean brandDisplayed = driver.findElement(By.xpath("//h2[contains(text(),'Brands')]")).isDisplayed();
		softAssertion.assertEquals(brandDisplayed, true);
		WebElement polo = driver.findElement(By.xpath("//a[contains(@href,'Polo')]"));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", polo);
		String url = driver.getCurrentUrl();
		String expectedUrl = "https://automationexercise.com/brand_products/Polo";
		softAssertion.assertEquals(url, expectedUrl);
		data.explicitSync(By.xpath("//h2[text()='Brand - Polo Products']"));
		boolean poloProdDisplayed = driver.findElement(By.xpath("//h2[text()='Brand - Polo Products']")).isDisplayed();
		softAssertion.assertEquals(poloProdDisplayed, true);
	}
}
