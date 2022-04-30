package Search;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchServices {

	public void search(WebDriver driver, String searchString) {
		driver.manage().window().maximize();
		driver.get("https://demoqa.com/books");
		WebElement m = driver.findElement(By.id("searchBox"));
		m.click();
		m.sendKeys(searchString);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,250)", "");
	}
}
