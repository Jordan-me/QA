package search;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SearchServices {

	public void search(WebDriver driver, String searchString,Logger logger) {
		driver.manage().window().maximize();
		driver.get("https://demoqa.com/books");
		WebElement m = driver.findElement(By.id("searchBox"));
		m.click();
		m.sendKeys(searchString);
		logger.info("\tSearchServices (search) -> Search box filled");
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,250)", "");
	}
}
