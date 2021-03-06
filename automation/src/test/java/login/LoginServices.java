package login;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginServices {
	public void login(WebDriver driver, String userName, String password, Logger logger) throws InterruptedException {
		//1| open|/
		driver.get("https://demoqa.com/books");
		//2 |setWindowSize |1038X680
		driver.manage().window().maximize();  
		driver.findElement(By.id("login")).click();
		logger.info("\tLoginServices (login) -> Login button pressed");
		//3| type| name = login| click
		WebElement inputField = driver.findElement(By.id("userName"));
		inputField.click();
		inputField.sendKeys(userName);    
		inputField = driver.findElement(By.id("password"));
		inputField.click();
		inputField.sendKeys(password);
		logger.info("\tLoginServices (login) -> Username and password fields have been filled");
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,250)", "");
		driver.findElement(By.id("login")).click();
		Thread.sleep(4000);
	}

}
