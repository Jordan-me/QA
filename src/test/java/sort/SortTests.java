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
import org.openqa.selenium.chrome.ChromeDriver;

import login.LoginServices;

@TestMethodOrder(OrderAnnotation.class)
public class SortTests {
	public static WebDriver driver;
	private LoginServices loginServices;
	private SortService sortServices;
	private final static Logger logger = LogManager.getLogger();
	private static final long TIME_INTERVAL = 2000;

	@BeforeAll
	public static void setUpLogger() {
		logger.info("Sort testsets - begin\n");
	}

	@AfterAll
	public static void tearLogger() throws InterruptedException {
		logger.info("Sort testsets - end\n");
		Thread.sleep(TIME_INTERVAL);
		driver.quit();
	}

	@BeforeEach
	public void setUp() throws InterruptedException {
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\Yarden\\Downloads\\chromedriver_win32\\chromedriver.exe");

		driver = new ChromeDriver();
		new HashMap<String, Object>();
		loginServices = new LoginServices();
		loginServices.login(driver, "Yardenale", "Ya111111#", logger);
		driver.get("https://demoqa.com/books");
		sortServices = new SortService();
	}

	@AfterEach
	public void tearDown() {
		driver.quit();
	}

	@Test
	@Order(1)
	public void sortByTitle() {
		sortServices.sortByAttribute(driver, logger, "Title", "//*[@id=\"app\"]/div/div/div[2]/div[2]/div[2]/div[2]/div[1]/div[1]/div/div[2]");

	}

	@Test
	public void sortByAuthor() {
		sortServices.sortByAttribute(driver, logger,"Author", "//*[@id=\"app\"]/div/div/div[2]/div[2]/div[2]/div[2]/div[1]/div[1]/div/div[3]");

	}

	@Test
	public void sortByPublisher() {
		sortServices.sortByAttribute(driver, logger,"Publisher", "//*[@id=\"app\"]/div/div/div[2]/div[2]/div[2]/div[2]/div[1]/div[1]/div/div[4]");
	}

}
