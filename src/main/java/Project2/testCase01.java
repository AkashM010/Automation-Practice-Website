package Project2;

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
	
	@Test (priority=1)
	public static void homePage() {
		String pageTitle = driver.getTitle();
		softAssertion.assertEquals(pageTitle, "Automation Exercise");
	}
	
	@Test (priority=2)
	public static void signupBtn() {
		obj.signLogButton();
		WebElement element = obj.newUser();
		boolean dispalyed = element.isDisplayed();
		softAssertion.assertEquals(dispalyed, true);
	}
	
	@Test (priority=3)
	public static void signUpPage() {
		obj.Name("Random");
		obj.emailID("Random@123");;
		obj.signUpbutton();
		WebElement acntInfo = obj.acntInfo();
		boolean actualAcntInfo = acntInfo.isDisplayed();
		softAssertion.assertEquals(actualAcntInfo, true);
	}
	
	@Test (priority=4)
	public static void accountntInfo() {
		obj.radioBtn();
		obj.password("Random@123");
		WebElement day = obj.dayDropDown();
		Select dayDD = new Select(day);
		dayDD.selectByIndex(1);
		WebElement month = obj.monthDropDown();
		Select monthDD = new Select(month);
		monthDD.selectByIndex(1);
		WebElement year = obj.yearDropDown();
		Select yearDD = new Select(year);
		yearDD.selectByIndex(1);
		obj.newsletter();
		obj.optin();
		obj.firstName("Random");
		obj.lastName("Name");
		obj.Company("Abc");
		obj.Adress1("abc cvcb");
		obj.Adress2("adsf vgh");
		WebElement country = obj.countryDropDown();
		Select countryDD = new Select(country);
		countryDD.selectByValue("India");
		obj.State("West Bengal");
		obj.City("Kolkata");
		obj.Zipcode("123456");
		obj.MobileNumber("7894561230");
		obj.creatAccount();
	}
	
	@Test (priority=5)
	public static void afterAccountCreation() {
		WebElement acntelement = obj.accountCreatedText();
		softAssertion.assertEquals(acntelement.isDisplayed(), true);
		obj.continueButton();
	}
	
	@Test (priority=6)
	public static void loggedInAsUsername() {
//		driver.findElement(By.xpath("//li/a[contains(text(), 'Logged in as')]"));
		String value = obj.usernameVisibility();
		softAssertion.assertEquals(value, "Random"); 	//checks username value
	}
	
	@Test (priority=7)
	public static void deleteSignedUpAccount() {
		obj.delete();
		WebElement deletedAcnt1 = obj.deleteMessage();
		softAssertion.assertEquals(deletedAcnt1.isDisplayed(), true);
		obj.continueButton();
	}
}
