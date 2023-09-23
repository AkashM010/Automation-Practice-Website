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

public class testCase13 {
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
	public static void viewProduct() throws Exception {
		driver.findElement(By.xpath("//a[@href='/product_details/1']")).click();
		driver.findElement(By.cssSelector("div.product-information")).isDisplayed();
		WebElement quantityInput = driver.findElement(By.id("quantity"));
		int currentQty = Integer.parseInt(quantityInput.getAttribute("value"));
		if (currentQty < 4) {
			quantityInput.clear();
			quantityInput.sendKeys(String.valueOf(4));
		}
		driver.findElement(By.xpath("//button[contains(@class,'cart')]")).click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement cont = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Continue Shopping']")));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", cont);
		cont.click();
		driver.findElement(By.xpath("//a[contains(text(),' Cart')]")).click();
		data.implicitSync();
		WebElement body = driver.findElement(By.tagName("tbody"));
		WebElement row = body.findElement(By.tagName("tr"));
		String qty = row.findElement(By.cssSelector("td.cart_quantity button.disabled")).getText();
		int totalQty = Integer.parseInt(qty);
		softAssertion.assertEquals(totalQty, 4, "Qty check fail");
	}
}
