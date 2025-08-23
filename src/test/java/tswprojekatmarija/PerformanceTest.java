package tswprojekatmarija;

import java.text.DecimalFormat;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class PerformanceTest {

	private static WebDriver driver;
	private static final DecimalFormat df = new DecimalFormat("0.00");
	
	@Before
	public void setUp()
	{
		driver = new ChromeDriver();
	}
	
	@After
	public void cleanUp() {
		driver.quit();
	}
	
	@Test
	public void Performance_Test()	{
		double avg=0.0;
		
		ArrayList<String> urls = new ArrayList<String>();
		
		urls.add("https://ananas.rs/");
		urls.add("https://ananas.rs/login");
		urls.add("https://ananas.rs/best-seller");
		urls.add("https://ananas.rs/promo/ananas-plus");
		urls.add("https://ananas.rs/korisnicka-podrska");
		
		for (String url : urls) {
			double start = System.currentTimeMillis();
			driver.get(url);
			double end = System.currentTimeMillis();
			double result = end - start;
			avg += result;
			System.out.println(result);
		}
		System.out.println("Srednja vrednost: " + df.format(avg / 5.0) + 
                "\nSekundi: " + df.format((avg / 5.0) / 1000));
	}

}
