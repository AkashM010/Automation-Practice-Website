package Project2;

import java.awt.Desktop.Action;
import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class testCase06 {
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
	public static void contactUs() {
		obj.contactUs();
	}

	@Test(priority = 3)
	public static void getInTouch() throws Exception {
		WebElement contactustext = obj.contactUsText();
		boolean isDisplayed = contactustext.isDisplayed();
		softAssertion.assertEquals(isDisplayed, true);
		obj.contactUsName("Kapil Kunale");
		obj.contactUsEmail("arjenrodriguez10@gmail.com");
		obj.contactUsSubject("Testing");
		obj.contactUsMessage("Just testing this field");
		String filePath = "C:\\Users\\Akash\\OneDrive\\Desktop\\locators.txt";
		obj.fileUpload(filePath);
		WebElement submitBtn = obj.submitButton();
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", submitBtn);
		submitBtn.click();
		Alert alert = driver.switchTo().alert();
		alert.accept();
		WebElement msg = obj.successMessage();
		softAssertion.assertEquals(msg.getText(), "Success! Your details have been submitted successfully.");
		obj.homeButton();
		homePage();
	}
}
