package Pages;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import Classes.DriverMethods;

public class SearchHotels {
	private WebDriver driver;
	private DriverMethods test;

	public SearchHotels(DriverMethods test) {
		this.test = test;
		this.driver = test.getDriver();
	}

	public void searchHotle(String searchText) {
		test.setText(By.name("q"), 10, searchText);
		test.click(By.id("search_icon"), 10);
	}

	public void getLinks() throws IOException {
		WebElement cite = test.findElement(By.xpath("//cite[contains(text(),'www.expedia.com')]"), 10);
		cite.findElement(By.xpath("parent::*")).findElement(By.xpath("parent::*")).findElement(By.xpath("parent::*"))
				.findElement(By.cssSelector("div:first-child")).click();
	}
}
