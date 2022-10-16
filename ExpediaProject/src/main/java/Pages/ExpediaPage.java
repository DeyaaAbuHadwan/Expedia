package Pages;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import Classes.DriverMethods;

public class ExpediaPage {
	private WebDriver driver;
	private DriverMethods test;

	public ExpediaPage(DriverMethods test) {
		this.test = test;
		this.driver = test.getDriver();
	}

	public void checkIn_Out(String id) throws IOException, InterruptedException {
		test.screenShot(id + "_mainExpedia1");
		test.excuteJS("document.getElementById(\"hotels-check-in-btn\").click()");
		test.click(By.xpath("//button[contains(@data-day,'25')]"), 10);
		test.excuteJS("document.getElementById(\"hotels-check-out-btn\").textContent=\"Oct 27\"");
		test.click(By.xpath("//button[contains(@data-day,'27')]"), 10);
		test.click(By.xpath("//button[contains(@data-stid,'apply-date-picker')]"), 10);
		test.screenShot(id + "_mainExpedia2");
	}

	public String getRate() {
		return test.findElement(By.xpath("*//div[@itemprop=\"aggregateRating\"]//h3"), 10).getText();
	}

}
