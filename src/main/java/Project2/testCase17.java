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

public class testCase17 {
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

	@Test(priority = 1)
	public static void homePage() {
		String pageTitle = driver.getTitle();
		softAssertion.assertEquals(pageTitle, "Automation Exercise");
	}

	@Test
	public static void deletionOfprod() {
		driver.findElement(By.xpath("//a[@href='/products']")).click();
		List<WebElement> overlayElements = driver.findElements(By.cssSelector("div.productinfo.text-center"));
		WebElement first = overlayElements.get(0);
		Actions actions = new Actions(driver);
		actions.moveToElement(first).perform();
		WebElement addToCartBtn = first.findElement(By.cssSelector("a.btn.btn-default.add-to-cart"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", addToCartBtn);
		addToCartBtn.click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement cont = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Continue Shopping']")));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", cont);
		cont.click();
		driver.findElement(By.xpath("//a[contains(text(),' Cart')]")).click();
		data.implicitSync();
		String url = driver.getCurrentUrl();
		softAssertion.assertEquals(url, "https://automationexercise.com/view_cart");
		driver.findElement(By.cssSelector("a.cart_quantity_delete")).click();
		boolean displayed = driver.findElement(By.xpath("//b[contains(text(),'Cart is empty!')]")).isDisplayed();
		softAssertion.assertEquals(displayed, true);
	}
}
