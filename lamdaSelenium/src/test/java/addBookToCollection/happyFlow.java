package addBookToCollection;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import Login.LoginServices;

public class happyFlow {
	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\user\\Downloads\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();

		LoginServices loginServices = new LoginServices();
		loginServices.login(driver, "Yardenale", "Ya111111#");
		Thread.sleep(3000);

		AddBookServices addBookServices = new AddBookServices();
		addBookServices.addBook(driver);
		
		try {
			WebElement logoutBtn = driver.findElement(By.id("submit"));

			if (logoutBtn.isDisplayed()) {
				logoutBtn.click();
				System.out.println("\"login happy flow - Success\"");
			}
		} catch (Exception e) {
			System.out.println("\"login happy flow - Failed\"");
		}

		driver.quit();

	}
}
