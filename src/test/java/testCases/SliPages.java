package testCases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class SliPages extends BaseClass {

	@DataProvider
	public Object[][] windowResolution() {

		return new Object[][] { { 2560, 1440 }, { 1920, 1080 }, { 1280, 720 }, { 1366, 768 }, { 1920, 1200 },
				{ 1440, 900 },{ 1536, 864 },
		};
	}

	@Test(dataProvider = "windowResolution", enabled = true)
	public void checkResolutionForSliPages(int w, int h) throws InterruptedException {
		setDriver(w, h);
		System.out.println("Resolution = " + w + "*"+ h );
		driver.get(config.getProperty("testsiteurl"));
		WebElement strategy = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(OR.getProperty("strategy"))));
		strategy.click();
		Thread.sleep(3000);
		log.info("strategy is successfully clicked");
		BaseClass.checkResolutionSliPages(driver, w, h);

		WebElement proposals = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath(OR.getProperty("proposals"))));
		proposals.click();
		Thread.sleep(3000);
		log.info("proposals is successfully clicked");
		BaseClass.checkResolutionSliPages(driver, w, h);

		/*
		 * WebElement kpiDashooard = wait
		 * .until(ExpectedConditions.elementToBeClickable(By.xpath(OR.getProperty(
		 * "kpiDashooard")))); kpiDashooard.click(); Thread.sleep(3000);
		 * log.info("kpiDashooard is successfully clicked");
		 * BaseClass.checkResolutionSliPages(driver);
		 */

		WebElement professional = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath(OR.getProperty("professional"))));
		professional.click();
		Thread.sleep(3000);
		log.info("professional is successfully clicked");
		BaseClass.checkResolutionSliPages(driver, w, h);

		WebElement management = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath(OR.getProperty("management"))));
		management.click();
		Thread.sleep(3000);
		log.info("management is successfully clicked");
		BaseClass.checkResolutionSliPages(driver, w, h);

		WebElement businessproposals = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath(OR.getProperty("businessproposals"))));
		businessproposals.click();
		Thread.sleep(3000);
		log.info("businessproposals is successfully clicked");
		BaseClass.checkResolutionSliPages(driver, w, h);

		WebElement orgCharts = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath(OR.getProperty("orgCharts"))));
		orgCharts.click();
		Thread.sleep(3000);
		log.info("orgCharts is successfully clicked");
		BaseClass.checkResolutionSliPages(driver, w, h);

		WebElement education = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath(OR.getProperty("education"))));
		education.click();
		Thread.sleep(3000);
		log.info("education is successfully clicked");
		BaseClass.checkResolutionSliPages(driver, w , h);

		WebElement digitalMarketing = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath(OR.getProperty("digitalMarketing"))));
		digitalMarketing.click();
		Thread.sleep(3000);
		log.info("digitalMarketing is successfully clicked");
		BaseClass.checkResolutionSliPages(driver, w, h);

		driver.close();
	}
}
