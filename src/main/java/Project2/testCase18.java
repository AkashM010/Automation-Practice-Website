package Project2;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class testCase18 {
	static WebDriver driver;
	static SoftAssert softAssertion = new SoftAssert();

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
	public static void leftSidebar() throws Exception  {
		boolean catDisplayed = driver.findElement(By.xpath("//h2[text()='Category']")).isDisplayed();
		softAssertion.assertEquals(catDisplayed, true);
		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[@href='#Women']")).click();
		Thread.sleep(2000);
		data.explicitSync(By.xpath("//a[contains(@href,'/category_products/1')]"));
		driver.findElement(By.xpath("//a[contains(@href,'/category_products/1')]")).click();
		Thread.sleep(2000);
		String url = driver.getCurrentUrl();
		String expectedUrl = "https://automationexercise.com/category_products/1";
		softAssertion.assertEquals(url, expectedUrl);
		boolean carDisplayed2 = driver.findElement(By.xpath("//h2[text()='Women - Dress Products']")).isDisplayed();
		softAssertion.assertEquals(carDisplayed2, true);
		driver.findElement(By.xpath("//a[@href='#Men']")).click();
		WebElement tshirt = driver.findElement(By.xpath("//a[contains(text(),'Tshirts')]"));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", tshirt);
//		tshirt.click();
		String url2 = driver.getCurrentUrl();
		String expectedUrl2 = "https://automationexercise.com/category_products/3";
		softAssertion.assertEquals(url2, expectedUrl2);
		
	}
}
