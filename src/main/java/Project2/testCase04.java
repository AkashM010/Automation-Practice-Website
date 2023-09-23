package Project2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class testCase04 {
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
		driver.findElement(By.xpath("//input[@data-qa='login-email']")).sendKeys("arjenrodriguez10@gmail.com");
		driver.findElement(By.name("password")).sendKeys("Qwerty@123");
		driver.findElement(By.xpath("//button[text()='Login']")).click();
		WebElement linkElement	= driver.findElement(By.xpath("//a[contains(text(),' Logged in as ')]"));
		String textInElement = linkElement.getText();
		softAssertion.assertEquals(textInElement, "Logged in as Kapil Kunale");
	}
	
	@Test (priority = 3)
	public static void logOut() {
		driver.findElement(By.xpath("//a[contains(text(),' Logout')]")).click();
		String actualUrl = driver.getCurrentUrl();
		String expectedUrl = "https://automationexercise.com/login";
		softAssertion.assertEquals(actualUrl, expectedUrl);
	}
}
