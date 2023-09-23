package Project2;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class testCase05 {
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
	public static void signupBtn() {
		driver.findElement(By.xpath("//a[@href='/login']")).click();
		WebElement element = driver.findElement(By.xpath("//h2[text()='New User Signup!']"));
		boolean dispalyed = element.isDisplayed();
		softAssertion.assertEquals(dispalyed, true);
	}
	
	@Test (priority = 3)
	public static void signUpPage() {
		driver.findElement(By.xpath("//input[@data-qa='signup-name']")).sendKeys("Kapil Kunale");
		driver.findElement(By.xpath("//div[@class='col-sm-4']/div/form/input[3]")).sendKeys("arjenrodriguez10@gmail.com");
		driver.findElement(By.xpath("//button[text()='Signup']")).click();
		WebElement errorMsg2 = driver.findElement(By.xpath("//*[contains(text(),'Email Address already exist!')]"));
		softAssertion.assertEquals(errorMsg2.getText(), "Email Address already exist!");
	}
}
