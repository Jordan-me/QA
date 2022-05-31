package register;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
 
import org.openqa.selenium.WebElement;

public class RegisterSevrice {
	public void register(WebDriver driver, String name, String lastName, String userName, String password,
			String btnID,boolean toRegister, boolean notRobot ,Logger logger) throws InterruptedException {
		driver.manage().window().maximize();
		driver.get("https://demoqa.com/books");
		driver.findElement(By.id("login")).click();  
		driver.findElement(By.id("newUser")).click();

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,400)", "");
		if (toRegister) {
			WebElement m = driver.findElement(By.id("firstname"));
			m.click();
			m.sendKeys(name);  
			logger.info("\tRegisterSevrice (register) -> firstname ["+name+"] filled");

			m = driver.findElement(By.id("lastname"));
			m.click();
			m.sendKeys(lastName);
			logger.info("\tRegisterSevrice (register) -> lastName ["+lastName+"] filled");
			
			m = driver.findElement(By.id("userName"));
			m.click();
			m.sendKeys(userName);
			logger.info("\tRegisterSevrice (register) -> userName ["+userName+"] filled");

			m = driver.findElement(By.id("password"));
			m.click();
			m.sendKeys(password);
			logger.info("\tRegisterSevrice (register) -> password ["+password+"] filled");
			
			if (notRobot) {
				Thread.sleep(2000);
				driver.switchTo().frame(driver.findElement(By.xpath("//*[@title='reCAPTCHA']")));
				driver.findElement(By.xpath("//span[@id='recaptcha-anchor']")).click();
				logger.info("\tRegisterSevrice (register) -> recapthca clicked");
				driver.switchTo().defaultContent();
				Thread.sleep(8000);
			}

			m = driver.findElement(By.id(btnID));
			m.click();
			logger.info("\tRegisterSevrice (register) -> "+btnID+" button clicked");
			js.executeScript("window.scrollBy(0,400)", "");
		}
		Thread.sleep(4000);
	}
	
}
 
 