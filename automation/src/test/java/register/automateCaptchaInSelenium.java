package register;

import static org.junit.Assert.fail;

import java.time.Duration;
import java.util.UUID;

import org.testng.Reporter;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class automateCaptchaInSelenium {
	public static WebDriver driver;

 
    @BeforeClass
    public static void setUp() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("build", "CaptchaInSelenium");
        capabilities.setCapability("name", "TCaptchaInSeleniumSample");
        
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\Yarden\\Downloads\\chromedriver_win32\\chromedriver.exe");
		
		driver = new ChromeDriver();
 
        driver.get("https://demoqa.com/register");
 
 
    }
 
    @Test
    public void manuallySolveCaptchaWithDelayInSelenium() {
        try {
            System.out.println("Let's start with fresh registration");
            WebElement firstName = driver.findElement(By.id("firstname"));
            firstName.sendKeys("bla");
            
            WebElement lastName = driver.findElement(By.id("lastname"));
            lastName.sendKeys("bla");
            
            WebElement username = driver.findElement(By.id("userName"));
            username.sendKeys(UUID.randomUUID().toString());
 
            WebElement password = driver.findElement(By.id("password"));
            password.sendKeys("SuperStrongP@ssw0rd");
 
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30).getSeconds());
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(
                    By.xpath("//iframe[starts-with(@name, 'a-') and starts-with(@src, 'https://www.google.com/recaptcha')]")));
            
             wait.until(ExpectedConditions.elementToBeClickable(
                        By.xpath("//div[@class='recaptcha-checkbox-border']"))).click();
 
            System.out.println("Please fill the captcha images");
            
            wait.until(ExpectedConditions.elementToBeClickable(
            		By.id("register"))).click();
            WebElement inputField = driver.findElement(By.id("register"));
			inputField.click();
            System.out.println("\"register happy flow - Success\"");
        } catch (Exception e) {
        	System.out.println(e.getStackTrace());
        	fail("\"register happy flow - Failed\"");
        }
   
 
    }
 
 
    @AfterClass
    public static void closeBrowser() {
        Reporter.log("Closing the browser", true);
 
    }
 
}
