package tswprojekatmarija;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.FluentWait;

public class AddToCartTest {

	private static WebDriver driver;
	private static URL mainPageUrl;
	private static FluentWait<WebDriver>wait;
	private static String linkPr1, linkPr2, linkPr3;
	
	@Before
	public  void setUp()
	{
		driver = new ChromeDriver();
		try {
			mainPageUrl = new URL("https://ananas.rs");
		} catch (MalformedURLException e) {
			System.out.println("Could not create URL for main webpage.");
		}
		wait = new FluentWait<WebDriver>(driver)
	                .withTimeout(Duration.ofSeconds(30))
	                .pollingEvery(Duration.ofSeconds(5))
	                .ignoring(NoSuchElementException.class);
		driver.get(mainPageUrl.toString());
		driver.manage().window().maximize();
	}
	
	@After
	public void cleanUp() {
		driver.quit();
	}
	
	@Test
	public void AddingToCartTest() throws InterruptedException {
		wait.until(driver->driver.findElement(By.cssSelector("button[class='sc-1rhklln-0 jDfIno']"))).click();
		wait.until(driver->driver.findElement(By.cssSelector("button[class='sc-180pjr6-0 sc-ht0kga-2 jakpRF gWZZIO']"))).click();

		Thread.sleep(2000);
		wait.until(driver->driver.findElement(By.xpath("//a[@href='/kategorije/it-shop/racunari-i-racunarska-oprema']"))).click();

		Thread.sleep(2000);
		linkPr1 = "/proizvod/acer-monitor-ek251qeb-25-ips-1920x1080-100hz-amd-freesync-hdmi-vga-vesa-crni/1577855";
		wait.until(driver->driver.findElement(By.xpath("//a[@href='" + linkPr1 + "']"))).click();
		
		Thread.sleep(2000);
		wait.until(driver->driver.findElement(By.xpath("//button[text()='Dodaj u korpu']"))).click();
		
		Thread.sleep(1000);
		driver.navigate().back();
		
		Thread.sleep(2000);
		linkPr2 = "/proizvod/xiaomi-tablet-redmi-pad-se-11-oc-2-4ghz-4gb-128gb-wifi-8mp-android-zeleni/3156195";
		wait.until(driver->driver.findElement(By.xpath("//a[@href='"+linkPr2+"']"))).click();
		
		Thread.sleep(2000);
		wait.until(driver->driver.findElement(By.xpath("//button[text()='Dodaj u korpu']"))).click();
		
		Thread.sleep(1000);
		driver.navigate().back();
		
		Thread.sleep(2000);
		linkPr3 = "/proizvod/microsoft-licenca-retail-windows-11-pro-64bit/1362935";
		wait.until(driver->driver.findElement(By.xpath("//a[@href='"+linkPr3+"']"))).click();
		
		Thread.sleep(2000);
		wait.until(driver->driver.findElement(By.xpath("//button[text()='Dodaj u korpu']"))).click();
		
		Thread.sleep(1000);
		driver.navigate().back();
		
		Thread.sleep(2000);
		wait.until(driver->driver.findElement(By.xpath("//a[@href='/korpa']"))).click();
		
		Thread.sleep(1000);
		List<WebElement> linkovi = driver.findElements(By.tagName("a"));
		List<String> hrefs = new ArrayList<String>();
		for(WebElement link : linkovi)
		{
			String href = link.getAttribute("href");
			if (href!=null) {
				hrefs.add(href);
			}
		}
		List<String> trazeniHrefs = List.of(linkPr1, linkPr2, linkPr3);
		for (String trazeni : trazeniHrefs) {
		    boolean postoji = hrefs.stream().anyMatch(href -> href.contains(trazeni));
		    assertTrue(postoji, "Link koji sadrži '" + trazeni + "' nije pronađen na stranici");
		}
		
		String cena = wait.until(driver->driver.findElement(By.cssSelector("span.sc-1arj7wv-2.cwZZG"))).getText();
		cena = cena.replace(".", "").replace(",", ".");
		BigDecimal decCena = new BigDecimal(cena);
		assertEquals(52617.00, decCena.doubleValue(), "Ukupna cena artikala nije ispravna.");
	}
}
