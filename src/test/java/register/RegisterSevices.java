package register;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.security.spec.ECField;
 
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
 
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.opencsv.CSVWriter;

public class RegisterSevices {
	public void register(WebDriver driver, String name, String lastName, String userName, String password,
			boolean notRobot, boolean toRegister) throws InterruptedException {
		
		String mainWin = driver.getWindowHandle(); 
		driver.manage().window().maximize();
		driver.get("https://demoqa.com/books");
		driver.findElement(By.id("login")).click();
		driver.findElement(By.id("newUser")).click();

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,400)", "");
		if (toRegister) {
			WebElement inputField = driver.findElement(By.id("firstname"));
			inputField.click();
			inputField.sendKeys(name);

			inputField = driver.findElement(By.id("lastname"));
			inputField.click();
			inputField.sendKeys(lastName);

			inputField = driver.findElement(By.id("userName"));
			inputField.click();
			inputField.sendKeys(userName);

			inputField = driver.findElement(By.id("password"));
			inputField.click();
			inputField.sendKeys(password);
			
			long startTime = System.currentTimeMillis();
			if (notRobot) {
				Thread.sleep(2000);
				driver.switchTo().frame(driver.findElement(By.xpath("//*[@title='reCAPTCHA']")));
				driver.findElement(By.xpath("//span[@id='recaptcha-anchor']")).click();
				Thread.sleep(2000);
				 
				driver.switchTo().defaultContent();
			}

			inputField = driver.findElement(By.id("register"));
			inputField.click();
			driver.switchTo().alert().accept();
			js.executeScript("window.scrollBy(0,400)", "");
		}
		Thread.sleep(4000);
	}
//	public boolean checkExistsByXpath(WebDriver driver,String xpath) {
//		try {
//			driver.findElement(By.xpath(xpath));
//			
//		}catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//			return false;
//		}
//		return true;
//		
//	}
////	main procedure to identify and submit picture solution	
//	public void solveImages(WebDriver driver) throws InterruptedException {
//		(new WebDriverWait(driver,  10)).until(
//				ExpectedConditions.presenceOfElementLocated(
//						By.id("rc-imageselect-target"))
//				);
//		//check if there is a clicked tile
//		boolean isImageSelect = true;
//		if (!checkExistsByXpath(driver, "//div[@id='rc-imageselect-target']/table/tbody/tr/td[@class='rc-imageselect-tileselected']")) {
//			isImageSelect = false;
//		}
//		//wait before click on tiles
//		Thread.sleep(1000);	
//		Dimension dim = driver.manage().window().getSize();	
//		//click on a tile 
//		WebElement tile1 = (new WebDriverWait(driver, 10)).until(
//		        ExpectedConditions.elementToBeClickable(By.xpath(
//		        		"//div[@id='rc-imageselect-target']/table/tbody/tr["+
//		        				(new Random()).ints(1, dim.height).toString()+
//		        				"]/td["+(new Random()).ints(1, dim.width).toString()+"]"
//		        		    
//		        		))); 
//		tile1.click() ;
//		if(isImageSelect) {
//			try {
//				driver.findElement(By.xpath("//div[@id='rc-imageselect-target']/table/tbody/tr["+
//						(new Random()).ints(1, dim.height).toString()+"]/td["
//						+(new Random()).ints(1, dim.width).toString()+"]")).click();
//				
//			}
//			catch(Exception e)
//			{
//				System.out.println("\n\r No Such Element Exception for finding 2nd tile");
//				
//			}
//		}
//		
//	}
	
}
