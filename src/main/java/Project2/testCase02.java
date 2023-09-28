package Project2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class testCase02 {
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
	
	@Test (priority = 1)
	public static void homePage() {
		String pageTitle = driver.getTitle();
		softAssertion.assertEquals(pageTitle, "Automation Exercise");
	}
	
	@Test (priority = 2)
	public static void loginToAccount() {
		obj.signLogButton();
		WebElement element = obj.loginTextDisplayed();
		boolean dispalyed = element.isDisplayed();
		softAssertion.assertEquals(dispalyed, true);
		obj.loginEmailID("arjenrodriguez10@gmail.com");
		obj.password("Qwerty@123");
		obj.logInbutton();
		WebElement linkElement	= obj.usernameVisible;
		String textInElement = linkElement.getText();
		softAssertion.assertEquals(textInElement, "Logged in as Kapil Kunale");
	}
	
	@Test (priority = 3)
	public static void deleteLoggedInAccount() {
		obj.delete();
		WebElement deletedAcnt2 = obj.deleteMessage();
		softAssertion.assertEquals(deletedAcnt2.isDisplayed(), true);
	}
}
