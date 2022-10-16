package DriverClasses;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverChrome {
	private WebDriver driver;
	private String pathDriver;

	public DriverChrome() {
		this.pathDriver = "./src/test/resources/Browser drivers/chromedriver.exe";
		setDriver(pathDriver);
	}

	private void setDriver(String path) {
		System.setProperty("webdriver.chrome.driver", path);
		driver = new ChromeDriver();
	}

	public WebDriver getDriver() {
		return driver;
	}
}
