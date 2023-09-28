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
	public static void products() {
		obj.productsButton();
		pageObject.implicitSync();
		WebElement allProduct = obj.allaproductsDisplay();
		boolean allProdDisplayed = allProduct.isDisplayed();
		softAssertion.assertEquals(allProdDisplayed, true);

		WebElement allProdList = obj.prodListDisplay();
		boolean prodListDisplayed = allProdList.isDisplayed();
		softAssertion.assertEquals(prodListDisplayed, true);
		WebElement viewProductLink = obj.viewProduct();
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", viewProductLink);
		String currentUrl = driver.getCurrentUrl();
		softAssertion.assertEquals(currentUrl, "https://automationexercise.com/product_details/1");
		WebElement firstProduct = obj.prodDetail();
		boolean prodName = firstProduct.isDisplayed();
		softAssertion.assertEquals(prodName, true);
		WebElement productCategory = obj.ProductCategory();
		boolean prodcategory = productCategory.isDisplayed();
		softAssertion.assertEquals(prodcategory, true);
		WebElement productPrice = obj.ProductPrice();
		boolean prodprice = productPrice.isDisplayed();
		softAssertion.assertEquals(prodprice, true);
		WebElement productAvail = obj.ProductAvailability();
		boolean prodavailability = productAvail.isDisplayed();
		softAssertion.assertEquals(prodavailability, true);
		WebElement productCondition = obj.ProductCondition();
		boolean prodcondition = productCondition.isDisplayed();
		softAssertion.assertEquals(prodcondition, true);
		WebElement productBrand = obj.ProductBrand();
		boolean prodbrand = productBrand.isDisplayed();
		softAssertion.assertEquals(prodbrand, true);
	}
}
