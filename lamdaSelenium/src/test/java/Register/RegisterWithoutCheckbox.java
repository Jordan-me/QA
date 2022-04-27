package Register;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegisterWithoutCheckbox {

	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\user\\Downloads\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();

		RegisterSevices registerSevices = new RegisterSevices();
		registerSevices.register(driver, "Yardenn", "Tzemah", "yardi", "Ya111111#", false, true);
		WebDriverWait wait = new WebDriverWait(driver, 10);
		WebElement m = driver.findElement(By.id("name"));

		try {
			wait.until(ExpectedConditions.textToBePresentInElement(m, "Please verify reCaptcha to register!"));
			System.out.println("\"register with without checkbox mark - Success\"");
		} catch (Exception e) {
			System.out.println("\"register with without checkbox mark - Failed\"");
		}

		driver.quit();
	}

}
