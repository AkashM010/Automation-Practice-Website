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
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class testCase12 {
	static WebDriver driver;
	static SoftAssert softAssertion = new SoftAssert();
	static pageObject obj;
	static boolean size;

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
//		WebElement first = obj.overlayElements.get(0);
		List<WebElement> list = obj.producthover();
		
		WebElement first = list.get(0);
		Actions actions = new Actions(driver);
		actions.moveToElement(first).perform();
		WebElement addToCartBtn = obj.addtoCart();
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", addToCartBtn);
		addToCartBtn.click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement cont = wait
				.until(ExpectedConditions.elementToBeClickable(obj.continueShoppng()));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", cont);
		cont.click();
		
//		WebElement second = obj.overlayElements.get(1);
		WebElement second = list.get(1);
		actions.moveToElement(second).perform();
		WebElement addToCartBtn2 = obj.addtoCart();
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", addToCartBtn2);
		addToCartBtn2.click();
		WebElement cont2 = wait
				.until(ExpectedConditions.elementToBeClickable(obj.continueShoppng()));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", cont2);
		cont2.click();
		obj.cartButton();
		if (obj.tableSize() == 2) {
			size = true;
		}
		softAssertion.assertEquals(size, true);
		WebElement prodRow1 = obj.getProductRowId("product-1");
		WebElement prod1Price = obj.getProductPrice(prodRow1);
		WebElement prod1Quantity = obj.getProductQty(prodRow1);
		
		WebElement prodRow2 = obj.getProductRowId("product-2");
		WebElement prod2Price = obj.getProductPrice(prodRow2);
		WebElement prod2Quantity = obj.getProductQty(prodRow2);
		
		softAssertion.assertEquals(prod1Quantity.getText(), "1", "Qty check Failed");
		softAssertion.assertEquals(prod1Price.getText(), "Rs. 500", "Price check Failed");
		softAssertion.assertEquals(prod2Quantity.getText(), "1", "Qty check Failed");
		softAssertion.assertEquals(prod2Price.getText(), "Rs. 400", "Price check Failed");

	}
}
