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
	public static void loginToAccount() {
		obj.signLogButton();
		WebElement element = obj.loginTextDisplayed();
		boolean dispalyed = element.isDisplayed();
		softAssertion.assertEquals(dispalyed, true);
		obj.loginEmailID("abc@gmail.com");
		obj.password("abc@123");
		obj.logInbutton();
		WebElement errorMsg1 = obj.errorMessage();
		softAssertion.assertEquals(errorMsg1.getText(), "Your email or password is incorrect!");
	}
}
