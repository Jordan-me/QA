package register;

import static org.junit.Assert.fail;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

 
 
 

public class RegisterTests {
	public static WebDriver driver;
	private Map<String,Object>vars;
	private RegisterSevrice registerServices;
	private JavascriptExecutor js;
	
	@Before
	public void setUp() {
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\Yarden\\Downloads\\chromedriver_win32\\chromedriver.exe");
		
		driver = new ChromeDriver();
		js = (JavascriptExecutor)driver;
		vars = new HashMap<String,Object>();
		registerServices = new RegisterSevrice();
	}
	
	@After
	public void tearDown() {
		driver.quit();
	}
	
	@Test
	public void registerTest() {
		try {
			String userName = UUID.randomUUID().toString();
			registerServices.register(driver, "Yarden", "Dahan", userName, "Ya111111#", true, true);
			driver.switchTo().alert().accept();
			WebElement logoutBtn = driver.findElement(By.id("submit"));
			
			if (logoutBtn.isDisplayed()) {
				logoutBtn.click();
				System.out.println("\"register happy flow - Success\"");
			}
		} catch (Exception e) {
			fail("\"register happy flow - Failed\"");
		}
	}
	
	@Test 
	public void registerAndReturnMain() {
		try {
			registerServices.register(driver, "Yardenn", "Dahan", "yardi", "Ya111111", true, false);
			WebElement m = driver.findElement(By.id("gotologin"));
			m.click();

			WebElement userNameField = driver.findElement(By.id("userName"));

			if (userNameField.isDisplayed()) {
				userNameField.click();
				System.out.println("\"click on register and return to menu - Success\"");
			}
		} catch (InterruptedException e) {
			fail("\"click on register and return to menu - Failed\"");
		}

	}
	@Test 
	public void registerIneligiblePassword() {
		try {
			registerServices.register(driver, "Yarden", "Dahan", "yardi", "Ya111111", true, false);

			WebDriverWait wait = new WebDriverWait(driver, 10);
			WebElement m = driver.findElement(By.id("name"));

			wait.until(ExpectedConditions.textToBePresentInElement(m,
						"Passwords must have at least one non alphanumeric character, one digit ('0'-'9'), one uppercase ('A'-'Z'), one lowercase ('a'-'z'), one special character and Password must be eight characters or longer."));
				System.out.println("\"register with without checkbox mark - Success\"");
				
		} catch (Exception e) {
				fail("\"register with without checkbox mark - Failed\"");
		}

	}
	@Test 
	public void RegisterWithoutCheckbox() {
		try {
			registerServices.register(driver, "Dahan", "Tzemah", "yardi", "Ya111111", true, false);
			WebElement m = driver.findElement(By.id("gotologin"));
			m.click();

			WebDriverWait wait = new WebDriverWait(driver, 20);
			m = driver.findElement(By.id("name"));

			wait.until(ExpectedConditions.textToBePresentInElement(m, "Please verify reCaptcha to register!"));
			System.out.println("\"register with without checkbox mark - Success\"");
		} catch (Exception e) {
			e.printStackTrace();
			fail("\"register with without checkbox mark - Failed\"");
		}

	}

}
 
 