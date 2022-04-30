package Search;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchHappyFlow {

	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\user\\Downloads\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		SearchServices searchServices =  new SearchServices();
		searchServices.search(driver, "Git");

		WebDriverWait wait = new WebDriverWait(driver, 10);
		try {
			WebElement m = driver.findElement(By.className("rt-noData"));
			wait.until(ExpectedConditions.textToBePresentInElement(m, "No rows found"));
			System.out.println("\"register with without checkbox mark - Failed\"");
		} catch (Exception e) {
			System.out.println("\"register with without checkbox mark - Success\"");
		}
		driver.quit();
	}

}
