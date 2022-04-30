import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Login.LoginServices;
import addBookToCollection.AddBookServices;

public class DeleteBook {

	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\user\\Downloads\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();

		LoginServices loginServices = new LoginServices();
		loginServices.login(driver, "Yardenale", "Ya111111#");

		AddBookServices addBookServices = new AddBookServices();
		addBookServices.addBook(driver);

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,500)", "");
		WebElement m = driver.findElement(By.xpath("//*[@id=\"item-3\"]/span"));
		m.click();
		
//		Thread.sleep(3000);
//
//		WebElement m = driver.findElement(By.id("item-0"));
//		Thread.sleep(3000);
//
//		m.click();
//		Thread.sleep(3000);
//		m = driver.findElement(By.id("item-3"));
//		m.click();
		js.executeScript("window.scrollBy(0,-250)", "");
		m = driver.findElement(By.id("delete-record-undefined"));
		Thread.sleep(1000);
		m = driver.findElement(By.id("closeSmallModal-ok"));

		WebDriverWait wait = new WebDriverWait(driver, 10);
		try {
			m = driver.findElement(By.className("rt-noData"));
			wait.until(ExpectedConditions.textToBePresentInElement(m, "No rows found"));
			System.out.println("\"register with without checkbox mark - Success\"");
		} catch (Exception e) {
			System.out.println("\"register with without checkbox mark - Failed\"");
		}
	}

}
