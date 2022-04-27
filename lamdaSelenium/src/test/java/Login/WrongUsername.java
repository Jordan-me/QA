package Login;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WrongUsername {

	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\user\\Downloads\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();

		LoginServices loginServices = new LoginServices();
		loginServices.login(driver, "ram", "Ya111111#");
		WebDriverWait wait = new WebDriverWait(driver, 10);
		WebElement m = driver.findElement(By.id("name"));

		try {
			wait.until(ExpectedConditions.textToBePresentInElement(m, "Invalid username or password!"));
			System.out.println("\"login with wrong user name - Success\"");
		} catch (Exception e) {
			System.out.println("\"login with wrong user name - Failed\"");
		}
		driver.quit();
	}
}
