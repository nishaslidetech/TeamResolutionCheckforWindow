package teamListener;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import testCases.BaseClass;
import testCases.WindowResolutions;

public class TakeScreenshots extends BaseClass{

	public static String screenshotName;

	public static void CaptureScreenshot() throws IOException

	{
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);

		Date d = new Date();

		//screenshotName = "error_" + d.toString().replace(":", "_").replace(" ", "_") + ".png";

		screenshotName = "FailedScreenShot"  + ".png";


		// System.out.println(System.getProperty("user.dir"));

		FileUtils.copyFile(scrFile, new File(System.getProperty("user.dir") + "\\reports\\" + screenshotName));

	}

}
