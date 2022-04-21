package testCases;

import static org.testng.Assert.assertTrue;

import java.io.FileInputStream;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WindowResolutions extends BaseClass {

	public static JavascriptExecutor js;
	public static FileInputStream fis;
	public static WebDriverWait wait;
	public static WebDriver driver;

	@DataProvider
	public Object[][] windowResolution() {

		return new Object[][] { { 2560, 1440 }, { 1920, 1080 }, { 1280, 720 }, { 1536, 864 }, { 1366, 768 },
				{ 1680, 1050 }, { 1920, 1200 }, { 1440, 900 } };
	}

	@Test(dataProvider = "windowResolution", enabled = false)
	public void checkResolution(int w, int h) throws InterruptedException {

		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();

		driver.manage().window().setSize(new Dimension(w, h));
		// driver.manage().window().maximize();
		js = (JavascriptExecutor) driver;
		wait = new WebDriverWait(driver, 30);

		// check resolution testing the Newly Products

		driver.get(config.getProperty("testsiteurl")); //
		log.info("Url is successfully opened");
		Thread.sleep(3000);
		WebElement newlyAdded = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath(OR.getProperty("NewlyAdded"))));

		js.executeScript("arguments[0].click();", newlyAdded);
		log.info("newlyAdded is successfully clicked");
		Thread.sleep(3000);
		checkResolutionForNewlyAndPopular(driver, w, h);

		// check resolution testing for popular PPts

		driver.get(config.getProperty("testsiteurl"));
		Thread.sleep(2000);
		WebElement popularPPts = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath(OR.getProperty("PopularPPts"))));
		popularPPts.click();
		Thread.sleep(3000);
		log.info("popularPPts is successfully clicked");
		Thread.sleep(3000);
		BaseClass.checkResolutionForNewlyAndPopular(driver, w, h);

		// Check Resolution testing for the A4 Products

		driver.get(config.getProperty("testsiteurl"));
		WebElement onePager = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(OR.getProperty("onePager"))));
		onePager.click();
		log.info("onePager is successfully clicked");
		Thread.sleep(3000);

		List<WebElement> sizeofPagination = driver.findElements(By.xpath(OR.getProperty("Pagination")));

		System.out.println(sizeofPagination.size() + " = size");

		if (sizeofPagination.size() > 0) {
			System.out.println("pagination exists");

			// click on pagination link

			do {
				BaseClass.checkResolutionForA4Pages(driver, w, h);
				if (!driver.findElements(By.xpath(OR.getProperty("NextButton"))).isEmpty()) {
					WebElement nextButton = driver.findElement(By.xpath(OR.getProperty("NextButton")));
					log.info("nextButton is successfully clicked");
					nextButton.click();
				} else

				{
					break;
				}
			} while (true);
		} else {
			System.out.println("No pagination exists");
		}

		// Check Resolution for Emarsys pages

		driver.get(config.getProperty("testsiteurl"));
		Thread.sleep(5000);
		List<WebElement> listofImages = driver.findElements(By.xpath(OR.getProperty("EmarsysImages")));
		System.out.println("Number of elements:" + listofImages.size());

		for (int i = 0; i < listofImages.size(); i++) {

			float width = listofImages.get(i).getSize().getWidth();
			float hight = listofImages.get(i).getSize().getHeight();
			System.out.println(listofImages.get(i).getAttribute("title") + " -" + width + "-" + hight);
			float roundedValue = width / hight;
			System.out.println((roundedValue) + "roundedValue");
			DecimalFormat df = new DecimalFormat("#.##");
			df.setRoundingMode(RoundingMode.DOWN);
			System.out.println(df.format(roundedValue));
			assertTrue(df.format(roundedValue).equals("1.33"), "image is not displayed properly");

		}

		// check Resolution testing for the sLi Pages
		/*
		 * driver.get(config.getProperty("testsiteurl")); WebElement strategy = wait
		 * .until(ExpectedConditions.elementToBeClickable(By.xpath(OR.getProperty(
		 * "strategy")))); strategy.click(); Thread.sleep(3000);
		 * log.info("strategy is successfully clicked");
		 * BaseClass.checkResolutionSliPages(driver);
		 * 
		 * WebElement proposals = wait
		 * .until(ExpectedConditions.elementToBeClickable(By.xpath(OR.getProperty(
		 * "proposals")))); proposals.click(); Thread.sleep(3000);
		 * log.info("proposals is successfully clicked");
		 * BaseClass.checkResolutionSliPages(driver);
		 * 
		 * WebElement kpiDashooard = wait
		 * .until(ExpectedConditions.elementToBeClickable(By.xpath(OR.getProperty(
		 * "kpiDashooard")))); kpiDashooard.click(); Thread.sleep(3000);
		 * log.info("kpiDashooard is successfully clicked");
		 * BaseClass.checkResolutionSliPages(driver);
		 * 
		 * WebElement professional = wait
		 * .until(ExpectedConditions.elementToBeClickable(By.xpath(OR.getProperty(
		 * "professional")))); professional.click(); Thread.sleep(3000);
		 * log.info("professional is successfully clicked");
		 * BaseClass.checkResolutionSliPages(driver);
		 * 
		 * WebElement management = wait
		 * .until(ExpectedConditions.elementToBeClickable(By.xpath(OR.getProperty(
		 * "management")))); management.click(); Thread.sleep(3000);
		 * log.info("management is successfully clicked");
		 * BaseClass.checkResolutionSliPages(driver);
		 * 
		 * WebElement businessproposals = wait
		 * .until(ExpectedConditions.elementToBeClickable(By.xpath(OR.getProperty(
		 * "businessproposals")))); businessproposals.click(); Thread.sleep(3000);
		 * log.info("businessproposals is successfully clicked");
		 * BaseClass.checkResolutionSliPages(driver);
		 * 
		 * WebElement orgCharts = wait
		 * .until(ExpectedConditions.elementToBeClickable(By.xpath(OR.getProperty(
		 * "orgCharts")))); orgCharts.click(); Thread.sleep(3000);
		 * log.info("orgCharts is successfully clicked");
		 * BaseClass.checkResolutionSliPages(driver);
		 * 
		 * WebElement education = wait
		 * .until(ExpectedConditions.elementToBeClickable(By.xpath(OR.getProperty(
		 * "education")))); education.click(); Thread.sleep(3000);
		 * log.info("education is successfully clicked");
		 * BaseClass.checkResolutionSliPages(driver);
		 * 
		 * WebElement digitalMarketing = wait
		 * .until(ExpectedConditions.elementToBeClickable(By.xpath(OR.getProperty(
		 * "digitalMarketing")))); digitalMarketing.click(); Thread.sleep(3000);
		 * log.info("digitalMarketing is successfully clicked");
		 * BaseClass.checkResolutionSliPages(driver);
		 */
		// driver.close();
	}

}
