package Project2;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class testCase09 {
	static WebDriver driver;
	static SoftAssert softAssertion = new SoftAssert();
	static String searchQuery = "shirt";

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
	public static void products() {
		driver.findElement(By.xpath("//a[@href='/products']")).click();
		boolean allProdDisplayed = driver.findElement(By.xpath("//h2[text()='All Products']")).isDisplayed();
		softAssertion.assertEquals(allProdDisplayed, true);
		driver.findElement(By.name("search")).sendKeys("shirt");
		driver.findElement(By.xpath("//button[@id='submit_search']")).click();
		boolean searchedProdDisplayed = driver.findElement(By.xpath("//h2[text()='Searched Products']")).isDisplayed();
		softAssertion.assertEquals(searchedProdDisplayed, true);
		By productLocator = By.className("single-products"); // Locator for each product
		List<WebElement> productList = driver.findElements(productLocator);
		// Check if any products are found and visible
		Assert.assertTrue(!productList.isEmpty(), "No products found for the search query: " + searchQuery);
		// Iterate through the list of products to check visibility
		for (WebElement product : productList) {
			Assert.assertTrue(product.isDisplayed(), "Product is not visible: " + product.getText());
		}
	}
}
