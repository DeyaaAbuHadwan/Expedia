package TestCases;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Classes.*;
import Pages.*;
import net.bytebuddy.build.Plugin.Factory.UsingReflection.Priority;

public class SearchHotelTest {
	SearchHotels hotel;
	DriverMethods test;
	ExpediaPage expedia;
	String currentPage;
	String rate;
	List<String[]> data;
	String[] line = new String[3];

	@BeforeTest
	public void beforeTest() {
		data = new ArrayList<String[]>();
		test = new DriverMethods("Chrome");
		hotel = new SearchHotels(test);
		expedia = new ExpediaPage(test);
	}

	@DataProvider(name = "Data")
	public static Object[][] getData() throws Exception {
		List<String[]> lines = ReadCsvFile.readAllLines("src\\main\\resources\\input.csv");
		lines.remove(0);
		Object[][] data = new Object[lines.size()][lines.get(0).length];
		int index = 0;
		for (String[] line : lines) {
			data[index] = line;
			index++;
		}
		return data;
	}

//1098020,Roda Amwaj Suites Jumeirah Beach Residence,Dubai,The Walk
	@Test(dataProvider = "Data")
	public void test(String id, String name, String city, String address) throws IOException, InterruptedException {
		test.openLink("https://www.bing.com/");
		hotel.searchHotle(name + " " + city + " expedia");
		hotel.getLinks();
		currentPage = test.getCurrentURL();
		System.out.println("Current URL: " + currentPage);
		line[0] = id;
		line[1] = currentPage;
		expediaTest(id);
	}

	public void expediaTest(String id) throws IOException, InterruptedException {
		expedia.checkIn_Out(id);
		rate = expedia.getRate();
		System.out.println("Rate: " + rate);
		line[2] = rate;
		data.add(line);
	}

	@AfterTest
	public void afterTest() {
		String[] header = { "id: URL , Rate " };
		WriteCsvFile saveData = new WriteCsvFile();
		saveData.writeDataLineByLine("src\\main\\resources\\output.csv", data, header);
		test.getDriver().close();
	}

}
