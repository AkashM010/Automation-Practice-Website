package Project2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class testCase03 {
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
	
	@Test (priority = 1)
	public static void homePage() {
		String pageTitle = driver.getTitle();
		softAssertion.assertEquals(pageTitle, "Automation Exercise");
	}
	
	@Test (priority = 2)
	public static void loginToAccount() {
		driver.findElement(By.xpath("//a[@href='/login']")).click();
		WebElement element = driver.findElement(By.xpath("//h2[text()='Login to your account']"));
		boolean dispalyed = element.isDisplayed();
		softAssertion.assertEquals(dispalyed, true);
		driver.findElement(By.xpath("//input[@data-qa='login-email']")).sendKeys("abc@gmail.com");
		driver.findElement(By.name("password")).sendKeys("abc@123");
		driver.findElement(By.xpath("//button[text()='Login']")).click();
		WebElement errorMsg1	= driver.findElement(By.xpath("//p[contains(text(),'Your email or password is incorrect!')]"));
		softAssertion.assertEquals(errorMsg1.getText(), "Your email or password is incorrect!");
	}
}
