package sanityTest;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import addBookToCollection.AddBookServices;
import login.LoginServices;
import search.SearchServices;
import sort.SortService;

@TestMethodOrder(OrderAnnotation.class)
public class SanityTest {
	public static WebDriver driver;
	private static Logger logger;
	private static final long TIME_INTERVAL = 2000;
	
	@BeforeAll
	public static void setUpLogger() throws IOException {
		logger = LogManager.getLogger();
		logger.info("Sanity testsets - begin\n");
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\Yarden\\Downloads\\chromedriver_win32\\chromedriver.exe");
		
		driver = new ChromeDriver();
		ReadExcl.readExcel("", "variables.xlsx", "input",logger);

	}

	@AfterAll
	public static void tearLogger() throws InterruptedException {
		logger = LogManager.getLogger();
		logger.info("Sanity testsets - end\n");
		Thread.sleep(TIME_INTERVAL);
		driver.quit();
	}
	
	@Test
	@Order(1)
	public void sanityTest() {
		try {
			
			LoginServices loginServices = new LoginServices();
			loginServices.login(driver, ReadExcl.getValue(VarsKey.userName.name(), logger)
					, ReadExcl.getValue(VarsKey.password.name(), logger),logger);
			
			SortService sortService = new SortService();
			sortService.sortByAttribute(driver, logger, "Title", "//*[@id=\"app\"]/div/div/div[2]/div[2]/div[2]/div[2]/div[1]/div[1]/div/div[2]");
			
			SearchServices searchServices = new SearchServices();
			searchServices.search(driver
					, ReadExcl.getValue(VarsKey.searchString.name(), logger), logger);
			
			AddBookServices addBookServices = new AddBookServices();
			addBookServices.addBook(driver, logger);
			logger.info("\tSanity test - passed");
		}catch(Exception e){
			logger.error("\tSanity test - failed" + e.getStackTrace());
		}finally {
			logger.info("Sanity test - end");
		}
	}
	
	
	
   

}
