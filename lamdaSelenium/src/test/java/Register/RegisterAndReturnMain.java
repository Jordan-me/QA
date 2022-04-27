package Register;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class RegisterAndReturnMain {

	public static void main(String[] args) throws InterruptedException {

		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\user\\Downloads\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		
		RegisterSevices registerSevices = new RegisterSevices();
		registerSevices.register(driver, "Yardenn", "Tzemah", "yardi", "Ya111111", true, false);

		WebElement m = driver.findElement(By.id("gotologin"));
		m.click();
		
		try {
			WebElement userNameField = driver.findElement(By.id("userName"));

			if (userNameField.isDisplayed()) {
				userNameField.click();
				System.out.println("\"click on register and return to menu - Success\"");
			}
		} catch (Exception e) {
			System.out.println("\"click on register and return to menu - Failed\"");
		}

		driver.quit();
		
		
		
		
	}

}
