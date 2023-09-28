package Project2;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class testCase22 {
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
	public static void recommendedItem() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement recommendedElement = driver.findElement(By.xpath("//h2[text()='recommended items']"));
		js.executeScript("arguments[0].scrollIntoView(true);", recommendedElement);
		WebElement product = driver.findElement(By.cssSelector("a[data-product-id='1']"));
//		WebElement product = driver.findElement(By.xpath("//p[text()='Blue Top' and preceding-sibling::h2[text()='Rs. 500']]"));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@data-product-id='1' and contains(text(), 'Add to cart')]")));
        WebElement addToCartBtn = product.findElement(By.xpath("//a[@data-product-id='1' and contains(text(), 'Add to cart')]"));
        addToCartBtn.click();
		WebElement viewCart = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()='View Cart']")));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", viewCart);
		viewCart.click();
		WebElement addedProduct = driver.findElement(By.xpath("//*[contains(text(), 'Blue Top')]"));
		softAssertion.assertEquals(addedProduct.isDisplayed(), true);
	}
}
