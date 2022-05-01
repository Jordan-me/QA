package login;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import sanityTest.ReadExcl;
import sanityTest.VarsKey;

@TestMethodOrder(OrderAnnotation.class)
public class LoginTests {
	public static WebDriver driver;
	private LoginServices loginServices;  
	private final static Logger logger = LogManager.getLogger();
	private static final long TIME_INTERVAL = 2000;

	@BeforeAll
	public static void setUpLogger() throws IOException {
		ReadExcl.readExcel("", "variables.xlsx", "input",logger);
		logger.info("Login testsets - begin\n");
	}

	@AfterAll
	public static void tearLogger() throws InterruptedException {
		logger.info("Login testsets - end\n");
		Thread.sleep(TIME_INTERVAL);
		driver.quit();
	}
     
	@BeforeEach
	public void setUp() throws InterruptedException {
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\Yarden\\Downloads\\chromedriver_win32\\chromedriver.exe");

		driver = new ChromeDriver();
		loginServices = new LoginServices();
		Thread.sleep(TIME_INTERVAL);
	}

	@AfterEach
	public void tearDown() {
		driver.quit();
	}

	@Test
	@Order(1)
	public void logginHappyFlowTest() {
		
		logger.info("Login happy flow test - begin");
		try {
			loginServices.login(driver, ReadExcl.getValue(VarsKey.userName.name(), logger)
					, ReadExcl.getValue(VarsKey.password.name(), logger),logger);
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
		try {
			loginServices.login(driver, ReadExcl.getValue(VarsKey.userName.name(), logger)
					, ReadExcl.getValue(VarsKey.wrongPassword.name(), logger),logger);
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
		logger.info("Login with wrong user name test - begin");		
		try {
			loginServices.login(driver, ReadExcl.getValue(VarsKey.wrongUserName.name(), logger)
					, ReadExcl.getValue(VarsKey.password.name(), logger),logger);

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
