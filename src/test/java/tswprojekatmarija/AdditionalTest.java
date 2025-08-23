package tswprojekatmarija;

import static org.junit.jupiter.api.Assertions.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

public class AdditionalTest {
	private static WebDriver driver;
	private static URL mainPageUrl;
	private static FluentWait<WebDriver>wait;
	private static ChromeOptions options;
	
	@Before
	public void setUp()
	{
		options = new ChromeOptions();
		options.addArguments("--disable-cookie-encryption");
	    options.addArguments("--disable-site-isolation-trials");
	    java.util.Map<String, Object> prefs = new java.util.HashMap<>();
        prefs.put("profile.default_content_settings.cookies", 2);
        options.setExperimentalOption("prefs", prefs);
		driver = new ChromeDriver(options);
		try {
			mainPageUrl = new URL("https://ananas.rs");
		} catch (MalformedURLException e) {
			System.out.println("Could not create URL for login webpage.");
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
	public void MainPageOpen_Test() {
		String expectedTitle = "Ananas E-Commerce | Platforma za online kupovinu";
		assertEquals(expectedTitle, driver.getTitle(), "Naslov stranice nije ispravan!");
		
		WebElement logo = driver.findElement(By.xpath("//img[@alt='Ananas E-Commerce']"));
		assertTrue(logo.isDisplayed(),"Logo stranice nije prikazan!");
	}
	
	@Test
	public void EcommTrustMarkLink_Test() throws InterruptedException{
		String originalTab = driver.getWindowHandle();
		wait.until(driver->driver.findElement(By.cssSelector("button[class='sc-1rhklln-0 jDfIno']"))).click();
	    JavascriptExecutor js = (JavascriptExecutor) driver;
	    js.executeScript("window.scrollTo(0, document.body.scrollHeight);");

	    WebElement trustLink = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[href='/trust']")));
	    trustLink.click();
	    
	    wait.until(driver -> driver.getWindowHandles().size() > 1);

		 for (String tabHandle : driver.getWindowHandles()) {
		     if (!tabHandle.equals(originalTab)) {
		         driver.switchTo().window(tabHandle);
		         break;
		     }
		 }
	    Thread.sleep(2000);
	    assertEquals("https://verify.etrustmark.rs/cert/cert.php", driver.getCurrentUrl(), 
	        "Link za E-Commerce trust nije usmerio na pravu stranicu!");
	}

	@Test
	public void SearchFunction_Test() throws InterruptedException{
		wait.until(driver->driver.findElement(By.cssSelector("button[class='sc-1rhklln-0 jDfIno']"))).click();

	    WebElement searchInput = wait.until(
	            ExpectedConditions.elementToBeClickable(By.cssSelector("input.aa-Input"))
	        );
	        searchInput.sendKeys("bazen");
	        searchInput.sendKeys(Keys.ENTER);
		Thread.sleep(2000);
		assertEquals("https://ananas.rs/search?query=bazen", driver.getCurrentUrl(), "Search nije otvorio dobru stranicu!");
	}
	
	@Test
	public void SearchRandomFunction_Test() throws InterruptedException{
		wait.until(driver->driver.findElement(By.cssSelector("button[class='sc-1rhklln-0 jDfIno']"))).click();

	    WebElement searchInput = wait.until(
	            ExpectedConditions.elementToBeClickable(By.cssSelector("input.aa-Input"))
	        );
	        searchInput.sendKeys("dummyTest");
	        searchInput.sendKeys(Keys.ENTER);
		Thread.sleep(2000);
		assertEquals("https://ananas.rs/search?query=marijaradakovictest", driver.getCurrentUrl(), "Search nije otvorio dobru stranicu!");
		
		WebElement tekst = driver.findElement(By.cssSelector("h6.sc-4ai9u1-0.bKeMmB"));
		assertEquals("Neverovatno, ali izgleda da to što tražiš nemamo na našem sajtu :O",tekst.getText(), "Tekst za nepostojeci proizvod se ne prikazuje!");
	}
}
