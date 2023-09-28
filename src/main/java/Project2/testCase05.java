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
	static pageObject obj;

	@BeforeTest
	public static void setUp() {
		pageObject.setup();
		driver = pageObject.getDriver();
		obj = new pageObject(driver);
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
	public static void signupBtn() {
		obj.signLogButton();
		WebElement element = obj.newUser();
		boolean dispalyed = element.isDisplayed();
		softAssertion.assertEquals(dispalyed, true);
	}

	@Test(priority = 3)
	public static void signUpPage() {
		obj.Name("Random");
		obj.emailID("Random@123");;
		obj.signUpbutton();
		WebElement errorMsg2 = obj.signUpErrorMessage();
		softAssertion.assertEquals(errorMsg2.getText(), "Email Address already exist!");
	}
}
