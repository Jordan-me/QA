package login;

import static org.junit.Assert.fail;

import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginTests {
	public static WebDriver driver;
	private Map<String,Object>vars;
	private LoginServices loginServices;
	private JavascriptExecutor js;
	
	@Before
	public void setUp() {
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\Yarden\\Downloads\\chromedriver_win32\\chromedriver.exe");
		
		driver = new ChromeDriver();
		js = (JavascriptExecutor)driver;
		vars = new HashMap<String,Object>();
		loginServices = new LoginServices();
	}
	
	@After
	public void tearDown() {
		driver.quit();
	}
	@Test
	public void logginTest() {
		try {
			loginServices.login(driver, "Yardenale", "Ya111111#");
			WebElement logoutBtn = driver.findElement(By.id("submit"));
			if (logoutBtn.isDisplayed()) {
				logoutBtn.click();
				
				System.out.println("\"login happy flow - Success\"");
			}
		} catch (Exception e) {
			fail("\"login happy flow - Failed\"");
		}
	
	}
	@Test
	public void logginWrongPasswordTest() {
		//TODO: Add null password check
		String[] passwords = {"eam"};
		for (int i = 0; i < passwords.length; i++) {
			
			try {
				loginServices.login(driver, "Yardenale", passwords[i]);
				WebDriverWait wait = new WebDriverWait(driver, 10);
				WebElement m = driver.findElement(By.id("name"));
				wait.until(ExpectedConditions.textToBePresentInElement(m, "Invalid username or password!"));
				System.out.println("\"login with wrong password - Success\"");
			} catch (Exception e) {
				System.out.println(e);
				fail("\"login with wrong password - Failed\"");
			}
		}

	}
	@Test
	public void logginWrongUserNameTest() {
		//TODO: Add null username check
		String[] usernams = {"eam"};
		for (int i = 0; i < usernams.length; i++) {
			try {
				loginServices.login(driver, usernams[i], "Ya111111#");
				WebDriverWait wait = new WebDriverWait(driver, 10);
				WebElement m = driver.findElement(By.id("name"));
				wait.until(ExpectedConditions.textToBePresentInElement(m, "Invalid username or password!"));
				System.out.println("\"login with wrong user name - Success\"");
			} catch (Exception e) {
				fail("\"login with wrong user name - Failed\"");
			}			
		}

	}

	


}
