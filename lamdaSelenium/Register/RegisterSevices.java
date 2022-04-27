package Register;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class RegisterSevices {

	public void register(WebDriver driver, String name, String lastName, String userName, String password,
			boolean notRobot, boolean toRegister) throws InterruptedException {
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

			m = driver.findElement(By.id("lastname"));
			m.click();
			m.sendKeys(lastName);

			m = driver.findElement(By.id("userName"));
			m.click();
			m.sendKeys(userName);

			m = driver.findElement(By.id("password"));
			m.click();
			m.sendKeys(password);

			if (notRobot) {
				Thread.sleep(2000);
				driver.switchTo().frame(driver.findElement(By.xpath("//*[@title='reCAPTCHA']")));
				driver.findElement(By.xpath("//span[@id='recaptcha-anchor']")).click();
				driver.switchTo().defaultContent();
				Thread.sleep(8000);
			}

			m = driver.findElement(By.id("register"));
			m.click();
			js.executeScript("window.scrollBy(0,400)", "");
		}
		Thread.sleep(4000);
	}
}
