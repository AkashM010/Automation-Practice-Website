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

	@Test(priority = 1)
	public static void homePage() {
		String pageTitle = driver.getTitle();
		softAssertion.assertEquals(pageTitle, "Automation Exercise");
	}

	@Test
	public static void products() {
		obj.productsButton();
		pageObject.implicitSync();
		WebElement allProduct = obj.allaproductsDisplay();
		boolean allProdDisplayed = allProduct.isDisplayed();
		softAssertion.assertEquals(allProdDisplayed, true);
		obj.searchProduct("shirt");
		obj.searchButton();
		WebElement searchProductText = obj.searchedProductText();
		boolean searchedProdDisplayed = searchProductText.isDisplayed();
		softAssertion.assertEquals(searchedProdDisplayed, true);
		List<WebElement> productList = obj.eachSearchProduct(); // Locates for each product
		// Check if any products are found and visible
		Assert.assertTrue(!productList.isEmpty(), "No products found for the search query: " + searchQuery);
		// Iterate through the list of products to check visibility
		for (WebElement product : productList) {
			Assert.assertTrue(product.isDisplayed(), "Product is not visible: " + product.getText());
		}
	}
}
