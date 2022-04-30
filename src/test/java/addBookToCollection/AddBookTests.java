package addBookToCollection;

import java.util.HashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import login.LoginServices;

public class AddBookTests {
	public static WebDriver driver;
	private LoginServices loginServices;
	private AddBookServices addBookServices;
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
		logger.info("Login testsets - end\n");
		Thread.sleep(TIME_INTERVAL);
		driver.quit();
	}
   
	@BeforeEach
	public void setUp() {
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\Yarden\\Downloads\\chromedriver_win32\\chromedriver.exe");

		driver = new ChromeDriver();
		new HashMap<String, Object>();
		loginServices = new LoginServices();
		addBookServices = new AddBookServices();
	}

	@AfterEach
	public void tearDown() {
		driver.quit();
	}
	@Test
	public void addBookTest() {
		
		logger.info("Add Book happy flow test - begin");
		try {
			loginServices.login(driver, "Yardenale", "Ya111111#",logger);
			Thread.sleep(3000);
			addBookServices.addBook(driver,logger);
			WebElement logoutBtn = driver.findElement(By.id("submit"));

			if (logoutBtn.isDisplayed()) {
				logoutBtn.click();
			}
			logger.info("\tAdd Book happy flow test - Passed");
		} catch (Exception e) {
			logger.error("\tAdd Book happy flow test - failed:\n\t\t " + e.getMessage());
		} finally {
			logger.info("Add Book happy flow test - end\n");
		}  

	}
	

}