package search;

import java.io.IOException;
import java.util.HashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import sanityTest.ReadExcl;
import sanityTest.VarsKey;


@TestMethodOrder(OrderAnnotation.class)
public class SearchTests {
	public static WebDriver driver;
	private final static Logger logger = LogManager.getLogger();
	private static final long TIME_INTERVAL = 2000;
	private SearchServices searchServices;

	@BeforeAll
	public static void setUpLogger() throws IOException {
		ReadExcl.readExcel("", "variables.xlsx", "input",logger);
		logger.info("Search testsets - begin\n");
	}

	@AfterAll
	public static void tearLogger() throws InterruptedException {
		logger.info("Search testsets - end\n");
		Thread.sleep(TIME_INTERVAL);
		driver.quit();
	}
   
	@BeforeEach
	public void setUp() {
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\Yarden\\Downloads\\chromedriver_win32\\chromedriver.exe");

		driver = new ChromeDriver();
		new HashMap<String, Object>();
		searchServices =  new SearchServices();
	}

	@AfterEach
	public void tearDown() {
		driver.quit();
	}

	@Test
	@Order(1)
	public void searchHappyFlowTest() {
		searchServices.search(driver, 
				ReadExcl.getValue(VarsKey.searchString.name(), logger)
				,logger);
		WebDriverWait wait = new WebDriverWait(driver, 10);
		try {
			WebElement m = driver.findElement(By.className("rt-noData"));
			wait.until(ExpectedConditions.textToBePresentInElement(m, "No rows found"));
			logger.error("\tSearch happy flow test - failed");
		} catch (Exception e) {
			logger.info("\tSearch happy flow test - passed\n\t\t ");
		} finally {
			logger.info("Search happy flow test - end");
		}  
	}
	
	@Test
	@Order(2)
	public void emptySearchTest() {
		searchServices.search(driver, "",logger);
		WebDriverWait wait = new WebDriverWait(driver, 10);
		try {
			WebElement m = driver.findElement(By.className("rt-noData"));
			wait.until(ExpectedConditions.textToBePresentInElement(m, "No rows found"));
			logger.error("\tEmpty Search test - failed");
		} catch (Exception e) {
			logger.info("\tEmpty Search test- passed\n\t\t ");
		} finally {
			logger.info("Empty Search test - end");
		}  
	}
	
	@Test
	@Order(3)
	public void unauthorizedSearchTest() {
		searchServices.search(driver, "vssvsvs",logger);
		WebDriverWait wait = new WebDriverWait(driver, 10);
		try {
			WebElement m = driver.findElement(By.className("rt-noData"));
			wait.until(ExpectedConditions.textToBePresentInElement(m, "No rows found"));
			logger.info("\tEmpty Search test - passed");
		} catch (Exception e) {
			logger.error("\tEmpty Search test- failed:\n\t\t " + e.getMessage());
		} finally {
			logger.info("Empty Search test - end");
		}  
	}
}
 