package Register;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class HappyFlowRegister {
	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\user\\Downloads\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();

		RegisterSevices registerSevices = new RegisterSevices();
		registerSevices.register(driver, "Yardenn", "Tzemah", "yardii", "Ya111111#", true, true);

		// TODO
		// need to press on OK

		try {
			WebElement logoutBtn = driver.findElement(By.id("login"));

			if (logoutBtn.isDisplayed()) {
				logoutBtn.click();
				System.out.println("\"register happy flow - Success\"");
			}
		} catch (Exception e) {
			System.out.println("\"register happy flow - Failed\"");
		}

		driver.quit();
	}
}
