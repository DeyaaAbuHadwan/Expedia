package DriverClasses;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class DriverEdge {
	private WebDriver driver;
	private String pathDriver;

	public DriverEdge() {
		this.pathDriver= "./src/test/resources/Browser drivers/msedgedriver.exe";
		setDriver(this.pathDriver);

	}

	private void setDriver(String path) {
		System.setProperty("webdriver.edge.driver", path);
		driver = new EdgeDriver();
	}

	public WebDriver getDriver() {
		return driver;
	}
}
