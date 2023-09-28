package Project2;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class testCase16 {
	static WebDriver driver;
	static SoftAssert softAssertion = new SoftAssert();
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

	@Test(priority = 2)
	public static void signIn() {
		obj.signLogButton();
		obj.loginEmailID("arjenrodriguez10@gmail.com");
		obj.findElement("name", "password").sendKeys("Qwerty@123");
		obj.logInbutton();
		WebElement linkElement = obj.usernameVisible;
		String textInElement = linkElement.getText();
		softAssertion.assertEquals(textInElement, "Logged in as Kapil Kunale");
	}

	@Test(priority = 3)
	public static void prodPurchase() {
		WebElement product = obj.getElement(By.xpath("//a[@href='/products']"));
		product.click();
		List<WebElement> list = obj.producthover();
		WebElement first = list.get(0);
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
		obj.cartButton();
		pageObject.implicitSync();
		String url = driver.getCurrentUrl();
		softAssertion.assertEquals(url, "https://automationexercise.com/view_cart");
		WebElement proceedToCheckout = obj.getElement(By.xpath("//a[text()='Proceed To Checkout']"));
		proceedToCheckout.click();
		obj.getElement(By.id("address_delivery")).isDisplayed();
		obj.getElement(By.xpath("//h2[text()='Review Your Order']")).isDisplayed();
		obj.getElement(By.xpath("//textarea[@name='message']")).sendKeys("First Purchase");
		obj.getElement(By.xpath("//a[contains(text(),'Place Order')]")).click();
		pageObject.implicitSync();
		obj.findElement("xpath", "//*[@class='form-control']").sendKeys("Random Name");
		obj.findElement("xpath", "//*[@class='form-control card-number']").sendKeys("78943215852");
		obj.findElement("xpath", "//*[@name='cvc']").sendKeys("258");
		obj.findElement("name", "expiry_month").sendKeys("12");
		obj.findElement("name", "expiry_year").sendKeys("1995");
		obj.findElement("id", "submit").click();
		obj.findElement("xpath", "//*[text()='Order Placed!']").isDisplayed();
		obj.delete();
		WebElement deletedAcnt1 = obj.findElement("xpath", "//*[text()='Account Deleted!']");
		softAssertion.assertEquals(deletedAcnt1.isDisplayed(), true);
		obj.continueButton();
	}
}
