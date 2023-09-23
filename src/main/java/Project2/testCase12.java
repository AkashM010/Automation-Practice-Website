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
	public static void products() {
		driver.findElement(By.xpath("//a[@href='/products']")).click();
		List <WebElement> overlayElements = driver.findElements(By.cssSelector("div.productinfo.text-center"));
		WebElement first = overlayElements.get(0);
		Actions actions = new Actions(driver);
		actions.moveToElement(first).perform();
		WebElement addToCartBtn = first.findElement(By.cssSelector("a.btn.btn-default.add-to-cart"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", addToCartBtn);
		addToCartBtn.click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement cont = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Continue Shopping']")));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", cont);
		cont.click();
		WebElement second = overlayElements.get(1);
		actions.moveToElement(second).perform();
		WebElement addToCartBtn2 = second.findElement(By.cssSelector("a.btn.btn-default.add-to-cart"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", addToCartBtn2);
		addToCartBtn2.click();
		WebElement cont2 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Continue Shopping']")));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", cont2);
		cont2.click();
		driver.findElement(By.xpath("//a[contains(text(),' Cart')]")).click();
		WebElement table = driver.findElement(By.tagName("tbody"));
		List <WebElement> rows = table.findElements(By.tagName("tr"));
		if(rows.size()==2) {
			size = true;
		}
		softAssertion.assertEquals(size, true);
		WebElement prodRow1 = driver.findElement(By.id("product-1"));
		WebElement prod1Price = prodRow1.findElement(By.cssSelector("td.cart_price p"));
		WebElement prod1Quantity = prodRow1.findElement(By.cssSelector("td.cart_quantity button"));
		WebElement prodRow2 = driver.findElement(By.id("product-2"));
		WebElement prod2Price = prodRow2.findElement(By.cssSelector("td.cart_price p"));
		WebElement prod2Quantity = prodRow2.findElement(By.cssSelector("td.cart_quantity button"));
		softAssertion.assertEquals(prod1Quantity.getText(), "1", "Qty check Failed");
		softAssertion.assertEquals(prod1Price.getText(), "Rs. 500", "Price check Failed");
		softAssertion.assertEquals(prod2Quantity.getText(),"1", "Qty check Failed");
		softAssertion.assertEquals(prod2Price.getText(), "Rs. 400", "Price check Failed");
		
	}
}				


