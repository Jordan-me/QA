package Register;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegisterIneligiblePassword {

	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\user\\Downloads\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();

		RegisterSevices registerSevices = new RegisterSevices();
		registerSevices.register(driver, "Yardenn", "Tzemah", "yardi", "Ya111111", true, true);
		WebDriverWait wait = new WebDriverWait(driver, 10);
		WebElement m = driver.findElement(By.id("name"));

		try {
			wait.until(ExpectedConditions.textToBePresentInElement(m,
					"Passwords must have at least one non alphanumeric character, one digit ('0'-'9'), one uppercase ('A'-'Z'), one lowercase ('a'-'z'), one special character and Password must be eight characters or longer."));
			System.out.println("\"register with without checkbox mark - Success\"");
		} catch (Exception e) {
			System.out.println("\"register with without checkbox mark - Failed\"");
		}

		driver.quit();

	}

}
