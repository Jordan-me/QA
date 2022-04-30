package sort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
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

import login.LoginServices;

@TestMethodOrder(OrderAnnotation.class)
public class SortTests {
	public static WebDriver driver;
	private LoginServices loginServices;
	private static Logger logger;
	private static final long TIME_INTERVAL = 2000;
	
	@BeforeAll
	public static void setUpLogger() {
		logger = LogManager.getLogger();
		logger.info("Sort testsets - begin\n");
	}

	@AfterAll
	public static void tearLogger() throws InterruptedException {
		logger = LogManager.getLogger();
		logger.info("Sort testsets - end\n");
		Thread.sleep(TIME_INTERVAL);
		driver.quit();
	}
	@BeforeEach
	public void setUp() throws InterruptedException {
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\Yarden\\Downloads\\chromedriver_win32\\chromedriver.exe");
		
		driver = new ChromeDriver();
		new HashMap<String,Object>();
		loginServices = new LoginServices();
		loginServices.login(driver, "Yardenale", "Ya111111#",logger);
		driver.get("https://demoqa.com/books");
	}
	@AfterEach
	public void tearDown() {  
		driver.quit();
	}
	public void sortByAttribute(String att, String xpath) throws InterruptedException {
		List<WebElement> beforeFilterTitle = driver.findElements(
				By.className("mr-2"));
		//get title string list
		List<String>beforeFilterTitleList = new ArrayList<String>();
		for(WebElement p: beforeFilterTitle){
			beforeFilterTitleList.add(p.getText());
		}  
		WebElement m = driver.findElement(By.xpath(xpath));
		m.click();
		if (m.getClass().toString().indexOf("asc") != -1)
		{
			Collections.sort(beforeFilterTitleList);//ascending			
		}else {
			Collections.reverse(beforeFilterTitleList);//descending
		}
		Thread.sleep(5000);  
		List<WebElement> afterFilterTitle = driver.findElements(By.className("mr-2"));
		List<String> afterFilterTitleList = new ArrayList<String>();
		for(WebElement p: afterFilterTitle){
			afterFilterTitleList.add(p.getText());
		}  
		
		try {  
			Assert.assertEquals(beforeFilterTitleList,afterFilterTitleList);
			//success
			logger.info("\tSort Books By " +att +" test - Passed");
		} catch (Exception e) {
			logger.error("\tSort Books By " +att +" test - failed:\n\t\t " + e.getMessage());
		}catch (AssertionError ae) {
			logger.error("\tSort Books By " +att +" test - failed:\n\t\t " + ae.getMessage());
		}  finally {
			logger.info("Sort Books By " +att +" test - end");
		}  
		
		Thread.sleep(3000);
	}
	@Test 
	@Order(1)
	public void sortByTitle() throws InterruptedException {
		sortByAttribute("Title", "//*[@id=\"app\"]/div/div/div[2]/div[2]/div[2]/div[2]/div[1]/div[1]/div/div[2]");

	}
	
	@Test 
	public void sortByAuthor() throws InterruptedException {
		sortByAttribute("Author", "//*[@id=\"app\"]/div/div/div[2]/div[2]/div[2]/div[2]/div[1]/div[1]/div/div[3]");
  
	}
	@Test 
	public void sortByPublisher() throws InterruptedException {
		sortByAttribute("Publisher", "//*[@id=\"app\"]/div/div/div[2]/div[2]/div[2]/div[2]/div[1]/div[1]/div/div[4]");
	}
 
	
}
