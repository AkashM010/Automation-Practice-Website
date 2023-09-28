package Project2;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class pageObject {

	static WebDriver driver;

	public static void setup() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		launchApp();
	}

	public static WebDriver getDriver() {
		return driver;
	}

	public pageObject(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public WebElement getElement(By locator) {
		return driver.findElement(locator);
	}
	
	public WebElement findElement(String locatorStrategy, String locatorValue) {
		WebElement element = null;
		By locator = null;
		switch(locatorStrategy.toLowerCase()) {
		 case "id":
             locator = By.id(locatorValue);
             break;
         case "name":
             locator = By.name(locatorValue);
             break;
         case "xpath":
             locator = By.xpath(locatorValue);
             break;
         case "css":
             locator = By.cssSelector(locatorValue);
             break;
		}
		element = driver.findElement(locator);
		return element;
	}

	@FindBy(xpath = "//a[@href='/login']")
	WebElement signUpBtn;

	@FindBy(xpath = "//h2[text()='New User Signup!']")
	WebElement newuser;

	@FindBy(xpath = "//h2[text()='Login to your account']")
	WebElement loginText;

	@FindBy(xpath = "//input[@placeholder='Name']")
	WebElement name;

	@FindBy(xpath = "//div[@class='col-sm-4']/div/form/input[3]")
	WebElement email;

	@FindBy(xpath = "//button[text()='Signup']")
	WebElement btn;

	@FindBy(xpath = "//button[text()='Login']")
	WebElement loginbtn;

	@FindBy(xpath = "//b[text()='Enter Account Information']")
	WebElement accountinfo;

	@FindBy(xpath = "//input[@value='Mr']")
	WebElement radio;

	@FindBy(xpath = "//input[@id='password']")
	WebElement pwd;

	@FindBy(xpath = "//select[@id='days']")
	WebElement days;

	@FindBy(xpath = "//select[@id='months']")
	WebElement months;

	@FindBy(xpath = "//select[@id='years']")
	WebElement years;

	@FindBy(xpath = "newsletter")
	WebElement news;

	@FindBy(xpath = "optin")
	WebElement opt;

	@FindBy(id = "first_name")
	WebElement firstname;

	@FindBy(id = "last_name")
	WebElement lastname;

	@FindBy(id = "company")
	WebElement company;

	@FindBy(id = "address1")
	WebElement adress1;

	@FindBy(id = "address2")
	WebElement adress2;

	@FindBy(xpath = "//select[@id='country']")
	WebElement country;

	@FindBy(id = "state")
	WebElement state;

	@FindBy(id = "city")
	WebElement city;

	@FindBy(id = "zipcode")
	WebElement zipcode;

	@FindBy(id = "mobile_number")
	WebElement mobile_number;

	@FindBy(xpath = "//button[text()='Create Account']")
	WebElement creatAcntBtn;

	@FindBy(xpath = "//*[text()='Account Created!']")
	WebElement acntCreated;

	@FindBy(xpath = "//*[text()='Continue']")
	WebElement continueBtn;

	@FindBy(xpath = "//li/a[contains(., 'Logged in as')]")
	WebElement usernameVisible;

	@FindBy(xpath = "//a[@href='/delete_account']")
	WebElement deletebtn;

	@FindBy(xpath = "//*[text()='Account Deleted!']")
	WebElement deleteMsg;

	@FindBy(xpath = "//input[@data-qa='login-email']")
	WebElement loginEmail;

	@FindBy(xpath = "//p[contains(text(),'Your email or password is incorrect!')]")
	WebElement errormsg;

	@FindBy(xpath = "//a[contains(text(),' Logout')]")
	WebElement logoutBtn;

	@FindBy(xpath = "//*[contains(text(),'Email Address already exist!')]")
	WebElement signUperrormsg;

	@FindBy(xpath = "//a[contains(text(),' Contact us')]")
	WebElement contactus;

	@FindBy(xpath = "//*[contains(text(),'Get In Touch')]")
	WebElement contactustext;

	@FindBy(name = "name")
	WebElement Name;

	@FindBy(name = "email")
	WebElement Email;

	@FindBy(name = "subject")
	WebElement Subject;

	@FindBy(name = "message")
	WebElement Message;

	@FindBy(name = "upload_file")
	WebElement fileupload;

	@FindBy(xpath = "//input[@name='submit']")
	WebElement submitbtn;

	@FindBy(xpath = "//div[@class='status alert alert-success']")
	WebElement successmsg;

	@FindBy(xpath = "//span[contains(text(),'Home')]")
	WebElement homebtn;

	@FindBy(xpath = "//button[contains(text(),'Test Cases')]")
	WebElement testcases;

	@FindBy(xpath = "//a[@href='/products']")
	WebElement productBtn;

	@FindBy(xpath = "//h2[text()='All Products']")
	WebElement allprodDisplay;

	@FindBy(className = "features_items")
	WebElement prodList;

	@FindBy(xpath = "//a[contains(@href,'/product_details/1') and contains(@style,'color: brown;')]")
	WebElement viewprod;

	@FindBy(xpath = "//h2[contains(text(),'Blue Top')]")
	WebElement firstProd;

	@FindBy(xpath = "//p[contains(text(),'Category: Women > Tops')]")
	WebElement firstProdCat;

	@FindBy(xpath = "//span[contains(text(),'Rs. 500')]")
	WebElement firstProdPrice;

	@FindBy(xpath = "//b[contains(text(),'Availability:')]")
	WebElement firstProdAvail;

	@FindBy(xpath = "//b[contains(text(),'Condition:')]")
	WebElement firstProdCond;

	@FindBy(xpath = "//p[contains(text(),'Polo')]")
	WebElement firstProdBrand;

	@FindBy(name = "search")
	WebElement searchProd;

	@FindBy(xpath = "//button[@id='submit_search']")
	WebElement searchBtn;

	@FindBy(xpath = "//h2[text()='Searched Products']")
	WebElement searchProdtext;

	@FindBy(className = "single-products")
	List<WebElement> eachProductelement;

	@FindBy(tagName = "footer")
	WebElement footerElement;

	@FindBy(xpath = "//h2[text()='Subscription']")
	WebElement subscriptionElement;

	@FindBy(id = "susbscribe_email")
	WebElement subscriptionEmailElement;

	@FindBy(xpath = "//button[@id='subscribe']")
	WebElement subscriptionBtnElement;
	
	@FindBy(xpath = "//div[text()='You have been successfully subscribed!']")
	WebElement subscriptionSuccess;
	
	@FindBy(xpath = "//a[contains(text(),' Cart')]")
	WebElement cartBtnElement;
	
	@FindBy(css = "div.productinfo.text-center")
	List<WebElement> overlayElements;
	
	@FindBy(css = "a.btn.btn-default.add-to-cart")
	WebElement addtocartElement;
	
//	@FindBy(xpath = "//a[@data-product-id='2']")
//	WebElement addtocartElement;
	
	@FindBy(xpath = "//button[text()='Continue Shopping']")
	WebElement continueShopElement;

	public static void launchApp() {
		driver.get("https://automationexercise.com/");
	}

	public void signLogButton() {
		signUpBtn.click();
	}

	public WebElement newUser() {
		return newuser;
	}

	public WebElement loginTextDisplayed() {
		return loginText;
	}

	public void Name(String username) {
		name.sendKeys(username);
	}

	public void emailID(String emailid) {
		email.sendKeys(emailid);
	}

	public void signUpbutton() {
		btn.click();
	}

	public WebElement acntInfo() {
		return accountinfo;
	}

	public void radioBtn() {
		radio.click();
	}

	public void password(String pass) {
		pwd.sendKeys(pass);
	}

	public WebElement dayDropDown() {
		return days;
	}

	public WebElement monthDropDown() {
		return months;
	}

	public WebElement yearDropDown() {
		return years;
	}

	public void newsletter() {
		news.click();
	}

	public void optin() {
		opt.click();
	}

	public void firstName(String first) {
		firstname.sendKeys(first);
	}

	public void lastName(String last) {
		lastname.sendKeys(last);
	}

	public void Company(String cmpny) {
		company.sendKeys(cmpny);
	}

	public void Adress1(String adrs1) {
		adress1.sendKeys(adrs1);
	}

	public void Adress2(String adrs2) {
		adress2.sendKeys(adrs2);
	}

	public WebElement countryDropDown() {
		return country;
	}

	public void State(String stateName) {
		state.sendKeys(stateName);
	}

	public void City(String cityName) {
		city.sendKeys(cityName);
	}

	public void Zipcode(String zipCode) {
		zipcode.sendKeys(zipCode);
	}

	public void MobileNumber(String mobile) {
		mobile_number.sendKeys(mobile);
	}

	public void creatAccount() {
		creatAcntBtn.click();
	}

	public WebElement accountCreatedText() {
		return acntCreated;
	}

	public String usernameVisibility() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // 10 seconds timeout
        WebElement loggedInElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li/a[contains(., 'Logged in as')]")));
		return loggedInElement.getText();
	}

	public void continueButton() {
		continueBtn.click();
	}

	public void delete() {
		deletebtn.click();
	}

	public WebElement deleteMessage() {
		return deleteMsg;
	}

	public void loginEmailID(String logemail) {
		loginEmail.sendKeys(logemail);
	}

	public void logInbutton() {
		loginbtn.click();
	}

	public WebElement errorMessage() {
		return errormsg;
	}

	public void logOutButton() {
		logoutBtn.click();
	}

	public WebElement signUpErrorMessage() {
		return signUperrormsg;
	}

	public void contactUs() {
		contactus.click();
	}

	public WebElement contactUsText() {
		return contactustext;
	}

	public void contactUsName(String name) {
		Name.sendKeys(name);
	}

	public void contactUsEmail(String email) {
		Email.sendKeys(email);
	}

	public void contactUsSubject(String subject) {
		Subject.sendKeys(subject);
	}

	public void contactUsMessage(String message) {
		Message.sendKeys(message);
	}

	public void fileUpload(String filePath) {
		fileupload.sendKeys(filePath);
	}

	public WebElement submitButton() {
		return submitbtn;
	}

	public WebElement successMessage() {
		return successmsg;
	}

	public void homeButton() {
		homebtn.click();
	}

	public void testCases() {
		testcases.click();
	}

	public void productsButton() {
		productBtn.click();
	}

	public WebElement allaproductsDisplay() {
		return allprodDisplay;
	}

	public WebElement prodListDisplay() {
		return prodList;
	}

	public WebElement viewProduct() {
		return viewprod;
	}

	public WebElement prodDetail() {
		return firstProd;
	}

	public WebElement ProductCategory() {
		return firstProdCat;
	}

	public WebElement ProductPrice() {
		return firstProdPrice;
	}

	public WebElement ProductAvailability() {
		return firstProdAvail;
	}

	public WebElement ProductCondition() {
		return firstProdCond;
	}

	public WebElement ProductBrand() {
		return firstProdBrand;
	}

	public void searchProduct(String productName) {
		searchProd.sendKeys(productName);
	}

	public void searchButton() {
		searchBtn.click();
	}

	public WebElement searchedProductText() {
		return searchProdtext;
	}

	public List<WebElement> eachSearchProduct() {
		return eachProductelement;
	}

	public WebElement footer() {
		return footerElement;
	}

	public WebElement subscription() {
		return subscriptionElement;
	}

	public void subscriptionEmail(String email) {
		subscriptionEmailElement.sendKeys(email);
	}

	public void subscribeButton() {
		subscriptionBtnElement.click();
	}
	
	public WebElement subscriptionSuccessMessage() {
		return subscriptionSuccess;
	}
	
	public void cartButton() {
		cartBtnElement.click();
	}
	
	public List<WebElement> producthover() {
		return overlayElements;
	}
	
	public WebElement addtoCart() {
		return addtocartElement;
	}
	
	public WebElement continueShoppng() {
		return continueShopElement;
	}
	
	public int tableSize() {
		WebElement table = driver.findElement(By.tagName("tbody"));
		List<WebElement> rows = table.findElements(By.tagName("tr"));
		int productCount = rows.size();
		return productCount;
	}
	
	public WebElement getProductRowId(String prodID) {
		return driver.findElement(By.id(prodID));
	}
	
	public WebElement getProductPrice(WebElement prodRow) {
		return prodRow.findElement(By.cssSelector("td.cart_price p"));
	}
	
	public WebElement getProductQty(WebElement prodRow) {
		return prodRow.findElement(By.cssSelector("td.cart_quantity button"));
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
