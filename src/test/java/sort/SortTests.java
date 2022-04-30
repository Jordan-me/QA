package sort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import login.LoginServices;
import register.RegisterSevrice;

public class SortTests {
	public static WebDriver driver;
	private Map<String,Object>vars;
	private JavascriptExecutor js;
	private LoginServices loginServices;
	
	@Before
	public void setUp() throws InterruptedException {
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\Yarden\\Downloads\\chromedriver_win32\\chromedriver.exe");
		
		driver = new ChromeDriver();
		js = (JavascriptExecutor)driver;
		vars = new HashMap<String,Object>();
		loginServices = new LoginServices();
		loginServices.login(driver, "Yardenale", "Ya111111#");
		driver.get("https://demoqa.com/books");
	}
	@After
	public void tearDown() {
		driver.quit();
	}
	
	@Test 
	public void sortByTitle() throws InterruptedException {
		boolean fail = false;
		List<WebElement> beforeFilterTitle = driver.findElements(
				By.className("mr-2"));
		//get title string list
		List<String>beforeFilterTitleList = new ArrayList<String>();
		for(WebElement p: beforeFilterTitle){
			System.out.println(p.getText());
			beforeFilterTitleList.add(p.getText());
		}
		WebElement m = driver.findElement(By.xpath("//*[@id=\"app\"]/div/div/div[2]/div[2]/div[2]/div[2]/div[1]/div[1]/div/div[2]"));
		m.click();
		Thread.sleep(5000);
		List<WebElement> afterFilterTitle = driver.findElements(By.className("mr-2"));
		List<String> afterFilterTitleList = new ArrayList<String>();
		for(WebElement p: afterFilterTitle){
			 
			afterFilterTitleList.add(p.getText());
		}
		
		Collections.sort(beforeFilterTitleList);//ascending
		try {
			Assert.assertEquals(beforeFilterTitleList,afterFilterTitleList);
			
		}catch(Exception e) {
			System.out.println("Not Ascending Sort");
			fail = true;
		}
		if(fail) {
			try {
				Collections.reverse(beforeFilterTitleList);//descending
				Assert.assertEquals(beforeFilterTitleList,afterFilterTitleList);
			}catch (Exception e) {
				System.out.println("Not descending Sort");
			}	
		}
		
		
		Thread.sleep(3000);
	}
//	@Test 
//	public void sortByAuthor() {
//		List<WebElement> beforeFilterTitle = driver.findElements(By.className("mr-2"));
//		driver.findElements(By.className("mr-2"));
//		Select sortOption = new Select(driver.findElement(By.className("rt-resizable-header-content")));
//		sortOption.selectByVisibleText("Author");
//		
//		
//	}
//	@Test 
//	public void sortByPublisher() {
//		List<WebElement> beforeFilterTitle = driver.findElements(By.className("mr-2"));
//		driver.findElements(By.className("mr-2"));
//		Select sortOption = new Select(driver.findElement(By.className("rt-resizable-header-content")));
//		sortOption.selectByVisibleText("Publisher");
//	}
//	@Test 
//	public void sortByImage() {
//		List<WebElement> beforeFilterTitle = driver.findElements(By.className("mr-2"));
//		driver.findElements(By.className("mr-2"));
//		Select sortOption = new Select(driver.findElement(By.className("rt-resizable-header-content")));
//		sortOption.selectByVisibleText("Image");
//	}
	
}
