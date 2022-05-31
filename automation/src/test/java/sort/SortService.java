package sort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SortService {
	public void sortByAttribute(WebDriver driver, Logger logger, String att, String xpath) {
		try {
			List<WebElement> beforeFilterTitle = driver.findElements(By.className("mr-2"));
			// get title string list
			List<String> beforeFilterTitleList = new ArrayList<String>();
			for (WebElement p : beforeFilterTitle) {
				beforeFilterTitleList.add(p.getText());
			}
			WebElement m = driver.findElement(By.xpath(xpath));
			m.click();
			if (m.getClass().toString().indexOf("asc") != -1) {
				Collections.sort(beforeFilterTitleList);// ascending
			} else {
				Collections.reverse(beforeFilterTitleList);// descending
			}
			Thread.sleep(5000);
			List<WebElement> afterFilterTitle = driver.findElements(By.className("mr-2"));
			List<String> afterFilterTitleList = new ArrayList<String>();
			for (WebElement p : afterFilterTitle) {
				afterFilterTitleList.add(p.getText());
			}

			Assert.assertEquals(beforeFilterTitleList, afterFilterTitleList);
			// success
			logger.info("\tSort Books By " + att + " test - Passed");
			Thread.sleep(3000);
		} catch (Exception e) {
			logger.error("\tSort Books By " + att + " test - failed:\n\t\t " + e.getMessage());
		} catch (AssertionError ae) {
			logger.error("\tSort Books By " + att + " test - failed:\n\t\t " + ae.getMessage());
		} finally {
			logger.info("Sort Books By " + att + " test - end");
		}

	}
}
