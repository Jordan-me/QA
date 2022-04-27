package Login;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginServices {
	public void login(WebDriver driver, String userName, String password) throws InterruptedException {
		driver.manage().window().maximize();
		driver.get("https://demoqa.com/books");
		driver.findElement(By.id("login")).click();
		WebElement m = driver.findElement(By.id("userName"));
		m.click();
		m.sendKeys(userName);
		m = driver.findElement(By.id("password"));
		m.click();
		m.sendKeys(password);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,250)", "");
		driver.findElement(By.id("login")).click();
		Thread.sleep(4000);
	}

}
