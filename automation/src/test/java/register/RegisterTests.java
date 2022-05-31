package register;

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
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import sanityTest.ReadExcl;
import sanityTest.VarsKey;

@TestMethodOrder(OrderAnnotation.class)
public class RegisterTests {
	public static WebDriver driver;
	private RegisterSevrice registerServices;
	private final static Logger logger = LogManager.getLogger();
	private static final long TIME_INTERVAL = 3000;

	@BeforeAll
	public static void setUpLogger() throws IOException {
		ReadExcl.readExcel("", "variables.xlsx", "input",logger);
		logger.info("Register testsets - begin\n");
	}
	
	@AfterAll  
	public static void tearLogger() throws InterruptedException {
		logger.info("Register testsets - end\n");
		Thread.sleep(TIME_INTERVAL);
		driver.quit();
	}
	
	@BeforeEach
	public void setUp() throws InterruptedException {
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\Yarden\\Downloads\\chromedriver_win32\\chromedriver.exe");
		
		driver = new ChromeDriver();
		registerServices = new RegisterSevrice();
		Thread.sleep(TIME_INTERVAL);

	}
	
	@AfterEach
	public void tearDown() {
		driver.quit();
	}
	
	@Test
	@Order(1)
	public void registerHappyFlowTest() {
		logger.info("Register happy flow test - begin");
		try {
			registerServices.register(driver,ReadExcl.getValue(VarsKey.regFName.name(), logger)
					, ReadExcl.getValue(VarsKey.regLName.name(), logger)
					,ReadExcl.getValue(VarsKey.regUserName.name(), logger)
					, ReadExcl.getValue(VarsKey.regPassword.name(), logger)
					,"register", true, true, logger);
			driver.switchTo().alert().accept();
			WebElement logoutBtn = driver.findElement(By.id("submit"));
			
			if (logoutBtn.isDisplayed()) {
				logoutBtn.click();
			}
			logger.info("\tRegister happy flow test - Passed");
		} catch (Exception e) {
			logger.error("\tRegister happy flow test - failed:\n\t\t " + e.getMessage());
		} finally {
			logger.info("Register happy flow test - end");
		}  
	}
	
	@Test
	@Order(2)
	public void registerAndReturnMainTest() {
		logger.info("Register And Return Main test - begin");
		try {
			registerServices.register(driver,ReadExcl.getValue(VarsKey.regFName.name(), logger)
					, ReadExcl.getValue(VarsKey.regLName.name(), logger)
					,ReadExcl.getValue(VarsKey.regUserName.name(), logger)
					, ReadExcl.getValue(VarsKey.regPassword.name(), logger)
					,"gotologin", true, false, logger);

			Thread.sleep(TIME_INTERVAL);
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollBy(0,400)", "");
			WebElement userNameField = driver.findElement(By.id("userName"));

			if (userNameField.isDisplayed()) {
				userNameField.click();
			}
			logger.info("\tRegister And Return Main test - Passed");
		} catch (Exception e) {
			logger.error("\tRegister And Return Main test - failed:\n\t\t " + e.getMessage());
		} finally {
			logger.info("Register And Return Main test - end");
		}  

	}
	
	@Test 
	@Order(3)
	public void registerIneligiblePasswordTest() {
		logger.info("Register Ineligible Password test - begin");
		try {
			registerServices.register(driver,ReadExcl.getValue(VarsKey.regFName.name(), logger)
					, ReadExcl.getValue(VarsKey.regLName.name(), logger)
					,ReadExcl.getValue(VarsKey.regUserName.name(), logger)
					, ReadExcl.getValue(VarsKey.regWrongPassword.name(), logger)
					,"register", true, true, logger);
			WebDriverWait wait = new WebDriverWait(driver, 10);
			WebElement m = driver.findElement(By.id("name"));

			wait.until(ExpectedConditions.textToBePresentInElement(m,
					ReadExcl.getValue(VarsKey.regWrongPasswordMSG.name(), logger)));
			logger.info("\tRegister Ineligible Password test - Passed");
		} catch (Exception e) {
			logger.error("\tRegister Ineligible Password test - failed:\n\t\t " + e.getMessage());
		} finally {
			logger.info("Register Ineligible Password test - end");
		}  

	}
	
	@Test 
	@Order(4)
	public void RegisterWithoutCheckboxTest() {
		logger.info("Register Without Checkbox test - begin");
		try {
			registerServices.register(driver,ReadExcl.getValue(VarsKey.regFName.name(), logger)
					, ReadExcl.getValue(VarsKey.regLName.name(), logger)
					,ReadExcl.getValue(VarsKey.regUserName.name(), logger)
					, ReadExcl.getValue(VarsKey.regPassword.name(), logger)
					,"register", true, false, logger);		
			
			WebDriverWait wait = new WebDriverWait(driver, 10);
			WebElement m = driver.findElement(By.id("name"));

			wait.until(ExpectedConditions.textToBePresentInElement(m, 
					ReadExcl.getValue(VarsKey.regCaptchaMSG.name(), logger)));
			logger.info("\tRegister Without Checkbox test - Passed");
		} catch (Exception e) {
			logger.error("\tRegister Without Checkbox test - failed:\n\t\t " + e.getMessage());
		} finally {
			logger.info("Register Without Checkbox test - end");
		}  

	}  

}
 
 
 

 

 