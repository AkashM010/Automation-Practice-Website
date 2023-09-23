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

public class testCase15 {
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
	public static void signup() {
		driver.findElement(By.xpath("//a[@href='/login']")).click();
		driver.findElement(By.xpath("//input[@placeholder='Name']")).sendKeys("Random");
		driver.findElement(By.xpath("//div[@class='col-sm-4']/div/form/input[3]")).sendKeys("random@123");
		driver.findElement(By.xpath("//button[text()='Signup']")).click();
		driver.findElement(By.xpath("//input[@value='Mr']")).click();
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys("random@123");
		WebElement day = driver.findElement(By.xpath("//select[@id='days']"));
		Select dayDD = new Select(day);
		dayDD.selectByIndex(1);
		WebElement month = driver.findElement(By.xpath("//select[@id='months']"));
		Select monthDD = new Select(month);
		monthDD.selectByIndex(1);
		WebElement year = driver.findElement(By.xpath("//select[@id='years']"));
		Select yearDD = new Select(year);
		yearDD.selectByIndex(1);
		driver.findElement(By.id("newsletter")).click();
		driver.findElement(By.id("optin")).click();
		driver.findElement(By.id("first_name")).sendKeys("Random");
		driver.findElement(By.id("last_name")).sendKeys("Name");
		driver.findElement(By.id("company")).sendKeys("Abc");
		driver.findElement(By.id("address1")).sendKeys("abc cvcb");
		driver.findElement(By.id("address2")).sendKeys("adsf vgh");
		WebElement country = driver.findElement(By.xpath("//select[@id='country']"));
		Select countryDD = new Select(country);
		countryDD.selectByValue("India");
		driver.findElement(By.id("state")).sendKeys("West Bengal");
		driver.findElement(By.id("city")).sendKeys("Kolkata");
		driver.findElement(By.id("zipcode")).sendKeys("123456");
		driver.findElement(By.id("mobile_number")).sendKeys("7894561230");
		driver.findElement(By.xpath("//button[text()='Create Account']")).click();
		WebElement acntelement = driver.findElement(By.xpath("//*[text()='Account Created!']"));
		softAssertion.assertEquals(acntelement.isDisplayed(), true);
		driver.findElement(By.xpath("//*[text()='Continue']")).click();
		testCase01.loggedInAsUsername();
	}
	
	@Test
	public static void addingProd() {
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
		driver.findElement(By.xpath("//a[contains(text(),' Cart')]")).click();
		data.implicitSync();
		String url =  driver.getCurrentUrl();
		softAssertion.assertEquals(url, "https://automationexercise.com/view_cart");
		driver.findElement(By.xpath("//a[text()='Proceed To Checkout']")).click();
		driver.findElement(By.id("address_delivery")).isDisplayed();
		driver.findElement(By.xpath("//h2[text()='Review Your Order']")).isDisplayed();
		driver.findElement(By.xpath("//textarea[@name='message']")).sendKeys("First Purchase");
		driver.findElement(By.xpath("//a[contains(text(),'Place Order')]")).click();
		driver.findElement(By.xpath("//*[@class='form-control']")).sendKeys("Random Name");
		driver.findElement(By.xpath("//*[@class='form-control card-number']")).sendKeys("78943215852");
		driver.findElement(By.xpath("//*[@name='cvc']")).sendKeys("258");
		driver.findElement(By.name("expiry_month")).sendKeys("12");
		driver.findElement(By.name("expiry_year")).sendKeys("1995");
		driver.findElement(By.id("submit")).click();
		driver.findElement(By.xpath("//*[text()='Order Placed!']")).isDisplayed();
		driver.findElement(By.xpath("//a[@href='/delete_account']")).click();
		WebElement deletedAcnt1 = driver.findElement(By.xpath("//*[text()='Account Deleted!']"));
		softAssertion.assertEquals(deletedAcnt1.isDisplayed(), true);
		driver.findElement(By.xpath("//*[text()='Continue']")).click();
	}
}
