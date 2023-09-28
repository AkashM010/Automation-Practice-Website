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

public class testCase23 {

	static WebDriver driver;
	static SoftAssert softAssertion = new SoftAssert();
	static boolean flag;
	
	@BeforeTest
	public static void setUp() {
		pageObject.setup();
		driver = pageObject.getDriver();
	}
	
	@AfterTest
	public static void teardown() {
		pageObject.close();
	}
	
	@Test (priority=1)
	public static void homePage() {
		String pageTitle = driver.getTitle();
		softAssertion.assertEquals(pageTitle, "Automation Exercise");
	}
	
	@Test (priority=2)
	public static void signupBtn() {
		driver.findElement(By.xpath("//a[@href='/login']")).click();
	}
	
	@Test (priority=3)
	public static void signUpPage() {
//		data.explicitSync(By.xpath("//input[@placeholder='Name']"));
		driver.findElement(By.xpath("//input[@placeholder='Name']")).sendKeys("Random");
		driver.findElement(By.xpath("//div[@class='col-sm-4']/div/form/input[3]")).sendKeys("random@13");
		driver.findElement(By.xpath("//button[text()='Signup']")).click();
	}
	
	@Test (priority=4)
	public static void accountntInfo() {
		pageObject.implicitSync();
		driver.findElement(By.xpath("//input[@value='Mr']")).click();
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys("random@13");
		WebElement day = driver.findElement(By.xpath("//select[@id='days']"));
		Select dayDD = new Select(day);
		dayDD.selectByIndex(1);
		WebElement month = driver.findElement(By.xpath("//select[@id='months']"));
		Select monthDD = new Select(month);
		monthDD.selectByIndex(1);
		WebElement year = driver.findElement(By.xpath("//select[@id='years']"));
		Select yearDD = new Select(year);
		yearDD.selectByIndex(1);
		driver.findElement(By.id("first_name")).sendKeys("Random");
		driver.findElement(By.id("last_name")).sendKeys("Name");
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
	}
	
	@Test (priority=5)
	public static void afterAccountCreation() {
		WebElement acntelement = driver.findElement(By.xpath("//*[text()='Account Created!']"));
		softAssertion.assertEquals(acntelement.isDisplayed(), true);
		driver.findElement(By.xpath("//*[text()='Continue']")).click();
	}
	
	@Test (priority=6)
	public static void loggedInAsUsername() {
		pageObject.implicitSync();
//		driver.findElement(By.xpath("//li/a[contains(text(), 'Logged in as')]"));
		String value = driver.findElement(By.xpath("//li/a/b")).getText();
		softAssertion.assertEquals(value, "Random"); 	//checks username value
	}
	
	@Test (priority = 7)
	public static void cart() {
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
		pageObject.implicitSync();
		String url = driver.getCurrentUrl();
		String expectedUrl = "https://automationexercise.com/view_cart";
		softAssertion.assertEquals(url, expectedUrl);
		driver.findElement(By.xpath("//a[text()='Proceed To Checkout']")).click();
		pageObject.implicitSync();
		WebElement addressDetails = driver.findElement(By.cssSelector("li.address_city.address_state_name.address_postcode"));
		addressDetails.getText();
		String enteredAddress = "Kolkata West Bengal 123456";
		if(addressDetails.equals(enteredAddress)) {
			flag = true;
		}
		softAssertion.assertEquals(flag, true);
	}
	
	@Test (priority=8)
	public static void deleteSignedUpAccount() {
		driver.findElement(By.xpath("//a[@href='/delete_account']")).click();
		WebElement deletedAcnt1 = driver.findElement(By.xpath("//*[text()='Account Deleted!']"));
		softAssertion.assertEquals(deletedAcnt1.isDisplayed(), true);
		driver.findElement(By.xpath("//*[text()='Continue']")).click();
	}
}
