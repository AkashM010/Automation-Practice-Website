package Project2;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class data {
	
	static WebDriver driver;
	
	public static void setup() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		launchApp();
	}
	
	public static WebDriver getDriver() {
		return driver;
	}
	
	public static void launchApp() {
		driver.get("https://automationexercise.com/");
	}
	
	public static void close() {
		driver.quit();
	}
	
	public static void implicitSync() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
	}
	
	public static void explicitSync(By locator) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
}
