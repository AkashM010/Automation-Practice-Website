package Project2;

import java.awt.Desktop.Action;
import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
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
	public static void contactUs() {
		driver.findElement(By.xpath("//a[contains(text(),' Contact us')]")).click();
	}
	
	@Test (priority = 3)
	public static void getInTouch() throws Exception {
		boolean isDisplayed = driver.findElement(By.xpath("//*[contains(text(),'Get In Touch')]")).isDisplayed();
		softAssertion.assertEquals(isDisplayed, true);
		driver.findElement(By.name("name")).sendKeys("Kapil Kunale");
		driver.findElement(By.name("email")).sendKeys("arjenrodriguez10@gmail.com");
		driver.findElement(By.name("subject")).sendKeys("Testing");
		driver.findElement(By.name("message")).sendKeys("Just testing this field");
		WebElement fileUploadElement = driver.findElement(By.name("upload_file"));
		String filePath = "C:\\Users\\Akash\\OneDrive\\Desktop\\locators.txt";
		fileUploadElement.sendKeys(filePath);
		WebElement iframe = driver.findElement(By.id("aswift_1"));
		driver.switchTo().frame(iframe);
//		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='submit']")));
		WebElement submitBtn = driver.findElement(By.xpath("//input[@name='submit']"));
		submitBtn.click();
		driver.switchTo().defaultContent();
//		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//		Alert alert = wait.until(ExpectedConditions.alertIsPresent());
		Alert alert = driver.switchTo().alert();
		System.out.println(alert.getText());
		alert.accept();
		WebElement msg = driver.findElement(By.xpath("//div[@class='status alert alert-success']"));
		softAssertion.assertEquals(msg.getText(), "Success! Your details have been submitted successfully.");
		driver.findElement(By.xpath("//span[contains(text(),'Home')]")).click();
		homePage();
	}
}
