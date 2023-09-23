package Project2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class testCase01 {
	
	static WebDriver driver;
	static SoftAssert softAssertion = new SoftAssert();
	
	@BeforeTest
	public static void setUp() {
		data.setup();
		driver = data.getDriver();
	}
	
	@AfterTest
	public static void teardown() {
		data.close();
	}
	
	@Test (priority=1)
	public static void homePage() {
		String pageTitle = driver.getTitle();
		softAssertion.assertEquals(pageTitle, "Automation Exercise");
	}
	
	@Test (priority=2)
	public static void signupBtn() {
		driver.findElement(By.xpath("//a[@href='/login']")).click();
		WebElement element = driver.findElement(By.xpath("//h2[text()='New User Signup!']"));
		boolean dispalyed = element.isDisplayed();
		softAssertion.assertEquals(dispalyed, true);
	}
	
	@Test (priority=3)
	public static void signUpPage() {
//		data.explicitSync(By.xpath("//input[@placeholder='Name']"));
		driver.findElement(By.xpath("//input[@placeholder='Name']")).sendKeys("Random");
		driver.findElement(By.xpath("//div[@class='col-sm-4']/div/form/input[3]")).sendKeys("random@123");
		driver.findElement(By.xpath("//button[text()='Signup']")).click();
		WebElement acntInfo =	driver.findElement(By.xpath("//b[text()='Enter Account Information']"));
		boolean actualAcntInfo = acntInfo.isDisplayed();
		softAssertion.assertEquals(actualAcntInfo, true);
	}
	
	@Test (priority=4)
	public static void accountntInfo() {
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
	}
	
	@Test (priority=5)
	public static void afterAccountCreation() {
		WebElement acntelement = driver.findElement(By.xpath("//*[text()='Account Created!']"));
		softAssertion.assertEquals(acntelement.isDisplayed(), true);
		driver.findElement(By.xpath("//*[text()='Continue']")).click();
	}
	
	@Test (priority=6)
	public static void loggedInAsUsername() {
//		driver.findElement(By.xpath("//li/a[contains(text(), 'Logged in as')]"));
		String value = driver.findElement(By.xpath("//li/a/b")).getText();
		softAssertion.assertEquals(value, "Random"); 	//checks username value
	}
	
	@Test (priority=7)
	public static void deleteSignedUpAccount() {
		driver.findElement(By.xpath("//a[@href='/delete_account']")).click();
		WebElement deletedAcnt1 = driver.findElement(By.xpath("//*[text()='Account Deleted!']"));
		softAssertion.assertEquals(deletedAcnt1.isDisplayed(), true);
		driver.findElement(By.xpath("//*[text()='Continue']")).click();
	}
}
