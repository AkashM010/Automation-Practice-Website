package Project2;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class testCase20 {
	static WebDriver driver;
	static SoftAssert softAssertion = new SoftAssert();
	static String searchQuery = "shirt";
	static boolean flag;

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

	@Test (priority = 1)
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
		// Verify all the products related to search are visible
		Assert.assertTrue(!productList.isEmpty(), "No products found for the search query: " + searchQuery);
		// Iterate through the list of products to check visibility
		for (WebElement product : productList) {
			Assert.assertTrue(product.isDisplayed(), "Product is not visible: " + product.getText());
		}
	}
	
	@Test (priority = 2)
	public static void addingAllProducts() {
		Actions actions = new Actions(driver);
		List <WebElement> overlayElements = driver.findElements(By.cssSelector("div.productinfo.text-center"));
		int size = overlayElements.size();
		for(int i=0; i<size; i++) {
			WebElement first = overlayElements.get(i);
			actions.moveToElement(first).perform();
			WebElement addToCartBtn = first.findElement(By.cssSelector("a.btn.btn-default.add-to-cart"));
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", addToCartBtn);
			addToCartBtn.click();
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			WebElement cont = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Continue Shopping']")));
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", cont);
			cont.click();
		}
	}
	
	@Test (priority = 3)
	public static void cart() {
		driver.findElement(By.xpath("//a[contains(text(),' Cart')]")).click();
		data.implicitSync();
		WebElement table1 = driver.findElement(By.tagName("tbody"));
		List <WebElement> rows1 = table1.findElements(By.tagName("tr"));
		int size1 = rows1.size();
		if(size1>1) {
			flag = true;
		}
		softAssertion.assertEquals(flag, true);
		driver.findElement(By.xpath("//a[@href='/login']")).click();
		driver.findElement(By.xpath("//input[@data-qa='login-email']")).sendKeys("arjenrodriguez10@gmail.com");
		driver.findElement(By.name("password")).sendKeys("Qwerty@123");
		driver.findElement(By.xpath("//button[text()='Login']")).click();
		driver.findElement(By.xpath("//a[contains(text(),' Cart')]")).click();
		data.implicitSync();
		WebElement table2 = driver.findElement(By.tagName("tbody"));
		List <WebElement> rows2 = table2.findElements(By.tagName("tr"));
		int size2 = rows2.size();
		if(size1==size2) {
			flag = false;
		}
		softAssertion.assertEquals(flag, false);
		
	}
}
