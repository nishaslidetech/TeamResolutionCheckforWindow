package testCases;

import java.math.RoundingMode;
import java.text.DecimalFormat;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class PopularPPts extends BaseClass {
	@DataProvider
	public Object[][] windowResolution() {

		return new Object[][] {
<<<<<<< HEAD
			{ 1280, 720 }
				//{ 1920, 1080 } // { 1280, 720 }, { 1920, 1200 }, { 1440, 900 }, { 2560, 1440 },
				// { 1536, 864 },
				// { 1366, 768 },
=======

				{ 1920, 1080 } , { 1280, 720 }, { 1920, 1200 }, { 1440, 900 }, { 2560, 1440 }, { 1536, 864 }, { 1366, 768 },
>>>>>>> 6cc3104d31d490e65547650203bda316e233e19b
		};
	}

	@Test(dataProvider = "windowResolution", enabled = true)
	public void checkResolutionForpopularPPts(int w, int h) throws InterruptedException {

		setDriver(w, h);
		System.out.println("Resolution = " + w + "*" + h);
		driver.get(config.getProperty("testsiteurl"));
		Thread.sleep(2000);
		WebElement popularPPts = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath(OR.getProperty("PopularPPts"))));
		popularPPts.click();
		Thread.sleep(3000);
		log.info("popularPPts is successfully clicked");
		Thread.sleep(3000);
		checkResolutionForNewlyAndPopular(driver, w, h);
		driver.close();

	}
}
