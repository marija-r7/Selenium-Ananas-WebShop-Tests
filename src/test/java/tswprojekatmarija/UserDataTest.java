package tswprojekatmarija;

import static org.junit.Assert.assertEquals;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.NoSuchElementException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.FluentWait;

public class UserDataTest {

	private static WebDriver driver;
	private static URL loginPageUrl;
	private static URL userAccountPageUrl;
	private static FluentWait<WebDriver>wait;
	
	@Before
	public void setUp()
	{
		driver = new ChromeDriver();
		try {
			loginPageUrl = new URL("https://ananas.rs/login");
			userAccountPageUrl = new URL("https://ananas.rs/profil/podesavanje-naloga");
		} catch (MalformedURLException e) {
			System.out.println("Could not create URL for login webpage.");
		}
		wait = new FluentWait<WebDriver>(driver)
	                .withTimeout(Duration.ofSeconds(30))
	                .pollingEvery(Duration.ofSeconds(5))
	                .ignoring(NoSuchElementException.class);
		driver.get(loginPageUrl.toString());
		driver.manage().window().maximize();
	}
	
	@After
	public void cleanUp() {
		driver.quit();
	}
	
	@Test
	public void UserData_Test() throws InterruptedException {
		wait.until(driver->driver.findElement(By.cssSelector("button[class='sc-1rhklln-0 jDfIno']"))).click();
		wait.until(driver -> driver.findElement(By.id("username"))).sendKeys("dummyEmail");
		driver.findElement(By.id("password")).sendKeys("dummyPass");
		driver.findElement(By.id("login-submit")).click();

		Thread.sleep(2000);
		
		driver.get(userAccountPageUrl.toString());
		
		Thread.sleep(2000);
		
		WebElement username = wait.until(driver -> driver.findElement(By.xpath("//p[text()='Ime i prezime']/following-sibling::p[contains(@class, 'sc-79pcpx-0') and contains(@class, 'sc-1kgnu5b-4')]")));
		WebElement email = driver.findElement(By.xpath("//p[text()='Email']/following-sibling::p[contains(@class, 'sc-79pcpx-0') and contains(@class, 'sc-1kgnu5b-4')]"));

		assertEquals(username.getText(), "Dummy Dummy");
		assertEquals(email.getText(), "dummyEmail");
	}
}
