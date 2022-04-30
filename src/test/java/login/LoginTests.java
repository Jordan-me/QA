package login;

import static org.junit.Assert.fail;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

@TestMethodOrder(OrderAnnotation.class)
public class LoginTests {
	public static WebDriver driver;
	private Map<String, Object> vars;
	private LoginServices loginServices;
	private JavascriptExecutor js;
	private static Logger logger;
	private static final long TIME_INTERVAL = 2000;

	@BeforeAll
	public static void setUpLogger() {
		logger = LogManager.getLogger();
		logger.info("Login testsets - begin\n");
	}

	@AfterAll
	public static void tearLogger() throws InterruptedException {
		logger = LogManager.getLogger();
		logger.info("Login testsets - end");
		Thread.sleep(TIME_INTERVAL);
		driver.quit();
	}
   
	@BeforeEach
	public void setUp() {
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\Yarden\\Downloads\\chromedriver_win32\\chromedriver.exe");

		driver = new ChromeDriver();
		js = (JavascriptExecutor) driver;
		vars = new HashMap<String, Object>();
		loginServices = new LoginServices();
	}

	@AfterEach
	public void tearDown() {
		driver.quit();
	}

	@Test
	@Order(1)
	public void logginTest() {
		
		logger.info("Login happy flow test - begin");
		try {
			loginServices.login(driver, "Yardenale", "Ya111111#");
			WebElement logoutBtn = driver.findElement(By.id("submit"));
			if (logoutBtn.isDisplayed()) {
				logoutBtn.click();

			}
			logger.info("\tLogin happy flow test - Passed");
		} catch (Exception e) {
			logger.error("\tLogin happy flow test - failed:\n\t\t " + e.getMessage());
		} finally {
			logger.info("Login happy flow test - end");
		}

	}

	@Test
	@Order(2)
	public void logginWrongPasswordTest() {
		logger.info("Login with wrong password test - begin");
		String password = "eam";

		try {
			loginServices.login(driver, "Yardenale", password);
			WebDriverWait wait = new WebDriverWait(driver, 10);
			WebElement m = driver.findElement(By.id("name"));
			wait.until(ExpectedConditions.textToBePresentInElement(m, "Invalid username or password!"));
			logger.info("\tLogin with wrong password test - Passed");
		} catch (Exception e) {
			logger.error("\tLogin with wrong password test - failed:\n\t\t " + e.getMessage());
		} finally {
			logger.info("Login with wrong password test - end");
		}
	}

	@Test
	@Order(3)
	public void logginWrongUserNameTest() {
		logger.info("Login with wrong user name test - begin");		String usernam = "eam" ;
		try {
			loginServices.login(driver, usernam, "Ya111111#");
			WebDriverWait wait = new WebDriverWait(driver, 10);
			WebElement m = driver.findElement(By.id("name"));
			wait.until(ExpectedConditions.textToBePresentInElement(m, "Invalid username or password!"));

			logger.info("\tLogin with wrong user name test - Passed");
		} catch (Exception e) {
			logger.error("\tLogin with wrong user name test - failed:\n\t\t " + e.getMessage());
		} finally {
			logger.info("Login with wrong user name test - end\n");
		}

	}

}
