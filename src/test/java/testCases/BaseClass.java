package testCases;

import static org.testng.Assert.assertTrue;

import java.io.FileInputStream;
import java.io.IOException;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Properties;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.testng.annotations.BeforeSuite;

import io.github.bonigarcia.wdm.WebDriverManager;
import java.io.FileInputStream;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BaseClass {

	public static Properties config = new Properties();
	public static Properties OR = new Properties();

	public static Logger log = Logger.getLogger(BaseClass.class);

	public static JavascriptExecutor js;
	public static FileInputStream fis;
	public static WebDriverWait wait;
	public static WebDriver driver;

	@BeforeSuite
	public void setUp() {

		PropertyConfigurator
				.configure(System.getProperty("user.dir") + "\\src\\test\\resources\\teamProperties\\log4j.properties");

		try {
			fis = new FileInputStream(
					System.getProperty("user.dir") + "\\src\\test\\resources\\teamProperties\\Config.properties");

			config.load(fis);
			log.info("Config file loaded !!!");
			fis = new FileInputStream(
					System.getProperty("user.dir") + "\\src\\test\\resources\\teamProperties\\OR.properties");
			OR.load(fis);
			log.info("OR file loaded !!!");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void setDriver(int w, int h) throws InterruptedException {

		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();

		driver.manage().window().setSize(new Dimension(w, h));
		// driver.manage().window().maximize();
		js = (JavascriptExecutor) driver;
		wait = new WebDriverWait(driver, 30);

	}

	public static void checkResolutionSliPages(WebDriver driver) throws InterruptedException {

		List<WebElement> sizeofPagination = driver.findElements(By.xpath(OR.getProperty("SliPagination")));

		System.out.println(sizeofPagination.size() + " = size");

		if (sizeofPagination.size() > 0) {
			System.out.println("pagination exists");

			// click on pagination link

			for (int j = 1; j < 2; j++) {
				List<WebElement> listofImages = driver.findElements(By.xpath(OR.getProperty("SliImages")));
				System.out.println("Number of elements:" + listofImages.size());

				for (int i = 0; i < listofImages.size(); i++) {

					float width = listofImages.get(i).getSize().getWidth();
					float hight = listofImages.get(i).getSize().getHeight();

					// System.out.println(listofImages.get(i).getAttribute("title") + " -" + width +
					// "-" + hight);

					float roundedValue = width / hight;
					DecimalFormat df = new DecimalFormat("#.##");
					df.setRoundingMode(RoundingMode.DOWN);
					// System.out.println(df.format(roundedValue));
					float f = Float.parseFloat(df.format(roundedValue));
					 System.out.println(f + " = float value");
					if ((f > 1.79) || (f <= 1.47) || (f >= 1.49 && f <= 1.74)) {
						System.out.println("URL = " + driver.getCurrentUrl() + "\n" + "PPtName = "
								+ listofImages.get(i).getAttribute("title") + " -" + width + "-" + hight + "\n"
								+ df.format(roundedValue));

					}

					assertTrue(
							df.format(roundedValue).equals("1.77") || df.format(roundedValue).equals("1.79")
									|| df.format(roundedValue).equals("1.76") || df.format(roundedValue).equals("1.78"),
							"image is not displayed properly");

				}

				if (!driver.findElements(By.xpath(OR.getProperty("SliPaginationNextButton"))).isEmpty()) {
					WebElement nextButton = driver.findElement(By.xpath(OR.getProperty("SliPaginationNextButton")));
					nextButton.click();
					Thread.sleep(4000);
				} else

				{
					break;
				}
			}
		} else {
			System.out.println("No pagination exists");
		}
	}

	public static void checkResolutionForNewlyAndPopular(WebDriver driver, int w, int h) throws InterruptedException {

		List<WebElement> sizeofPagination = driver.findElements(By.xpath(OR.getProperty("Pagination1")));

		System.out.println(sizeofPagination.size() + " = size");

		if (sizeofPagination.size() > 0) {
			System.out.println("pagination exists");

			// click on pagination link

			for (int j = 1; j < 2; j++) {
				List<WebElement> listofImages = driver.findElements(By.xpath(OR.getProperty("images1")));
				System.out.println("Number of elements:" + listofImages.size());

				for (int i = 0; i < listofImages.size(); i++) {

					float width = listofImages.get(i).getSize().getWidth();
					float hight = listofImages.get(i).getSize().getHeight();

					// System.out.println(listofImages.get(i).getAttribute("title") + " -" + width +
					// "-" + hight);

					float roundedValue = width / hight;
					DecimalFormat df = new DecimalFormat("#.##");
					df.setRoundingMode(RoundingMode.DOWN);
					// System.out.println(df.format(roundedValue));
					float f = Float.parseFloat(df.format(roundedValue));
					if (f >= 1.78 || f <= 1.47 || f >= 1.49 && f <= 1.74) {
						System.out.println("URL = " + driver.getCurrentUrl() + "\n" + "PPtName = "
								+ listofImages.get(i).getAttribute("title") + " -" + width + "-" + hight + "\n"
								+ df.format(roundedValue) + "Resolution = " + w + "*" + h);

					}

					/*
					 * if (f <= 1.32 && f >= 0.70 || (f >= 1.34) && (f <= 1.75) || f >= 1.79 || (f
					 * >= 0.70) && (f <= 1.32) || f <= 0.68) { System.out.println("URL = " +
					 * driver.getCurrentUrl() + "\n" + "PPtName = " +
					 * listofImages.get(i).getAttribute("title") + " -" + width + "-" + hight + "\n"
					 * + df.format(roundedValue) + "Resolution = "+ w + "*" +h);
					 * 
					 * }
					 */

					assertTrue(
							df.format(roundedValue).equals("1.77") || df.format(roundedValue).equals("1.75")
									|| df.format(roundedValue).equals("1.76") || df.format(roundedValue).equals("1.48"),
							"image is not displayed properly");

				}
				if (!driver.findElements(By.xpath(OR.getProperty("NextButton1"))).isEmpty()) {
					WebElement nextButton = driver.findElement(By.xpath(OR.getProperty("NextButton1")));
					nextButton.click();
					Thread.sleep(3000);
				} else

				{
					break;
				}
			}

		} else {
			System.out.println("No pagination exists");
		}
	}

	public static void checkResolutionForA4Pages(WebDriver driver, int w, int h) {

		List<WebElement> listofImages = driver.findElements(By.xpath(OR.getProperty("A4images")));
		System.out.println("Number of elements:" + listofImages.size());

		for (int i = 0; i < listofImages.size(); i++) {

			float width = listofImages.get(i).getSize().getWidth();
			float hight = listofImages.get(i).getSize().getHeight();
			float roundedValue = width / hight;
			DecimalFormat df = new DecimalFormat("#.##");
			df.setRoundingMode(RoundingMode.DOWN);
			// System.out.println(df.format(roundedValue));
			float f = Float.parseFloat(df.format(roundedValue));
			// System.out.println(f + " = float value");
			if (f >= 0.70 || f <= 0.68) {
				System.out.println("URL = " + driver.getCurrentUrl() + "\n" + "PPtName = "
						+ listofImages.get(i).getAttribute("title") + " -" + width + "-" + hight + "\n"
						+ df.format(roundedValue) + "Resolution = " + w + "*" + h);

			}
			assertTrue(df.format(roundedValue).equals("0.69"), "image is not displayed properly");

		}
	}

}
