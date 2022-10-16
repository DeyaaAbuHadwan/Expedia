package Classes;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import DriverClasses.*;

public class DriverMethods {
	private WebDriver driver;

	public DriverMethods(String browser) {
		switch (browser) {
		case "Chrome":
			this.driver = new DriverChrome().getDriver();
			break;
		case "Firefox":
			this.driver = new DriverFirefox().getDriver();
			break;
		case "Edge":
			this.driver = new DriverEdge().getDriver();
			break;
		default:
			throw new IllegalArgumentException("Please Enter once of the following Browser: Chrome, FireFox and Edge.");
		}
	}

	public WebElement findElement(By by, int time) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));
		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(by));
		return element;
	}

	public void click(By by, int time) {
		findElement(by, time).click();
		;
	}

	public String getText(By by, int time) {
		return findElement(by, time).getText();
	}

	public void setText(By by, int time, String value) {
		findElement(by, time).sendKeys(value);
	}

	public void excuteJS(String script) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript(script);
	}

	public WebDriver getDriver() {
		return driver;
	}

	public void openLink(String link) {
		driver.get(link);
		driver.manage().window().maximize();
	}

	public List<WebElement> getElements(By by, int time) {
		findElement(by, time);
		return driver.findElements(by);
	}

	public String getCurrentURL() {
		return driver.getCurrentUrl();
	}

	public void screenShot(String name) throws IOException, InterruptedException {
		// Screenshot
		Thread.sleep(1000);
		TakesScreenshot scrShot = ((TakesScreenshot) driver);
		File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);
		File DestFile = new File(name + ".png");
		FileUtils.copyFile(SrcFile, DestFile);
//				Allure.addAttachment("ScreenShot", FileUtils.openInputStream(SrcFile));
	}
}
