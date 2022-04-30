package addBookToCollection;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AddBookServices {
	public void addBook(WebDriver driver) throws InterruptedException {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,100)", "");
		WebElement m = driver.findElement(By.id("see-book-Git Pocket Guide"));
		m.click();
		js.executeScript("window.scrollBy(0,550)", "");
//		m = driver.findElement(By.xpath("//div[@text='Add To Your Collection']"));
		m = driver.findElement(By.xpath(".//button[contains(text(),'"+"Add To Your Collection"+"')]"));
		m.click();

		Thread.sleep(3000);
		driver.switchTo().alert().accept();
		Thread.sleep(1000);
		driver.switchTo().defaultContent();
		Thread.sleep(1000);



//		m = driver.findElement(By.id("addNewRecordButton"));
	}
}
