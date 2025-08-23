package tswprojekatmarija;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.NoSuchElementException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.FluentWait;

public class LoginUserTest {

	private static WebDriver driver;
	private static URL loginPageUrl;
	private static FluentWait<WebDriver>wait;
	
	@Before
	public void setUp()
	{
		driver = new ChromeDriver();
		try {
			loginPageUrl = new URL("https://ananas.rs/login");
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
	public void LoginUser_Test() throws InterruptedException {
		wait.until(driver->driver.findElement(By.cssSelector("button[class='sc-1rhklln-0 jDfIno']"))).click();
		wait.until(driver -> driver.findElement(By.id("username"))).sendKeys("dummyEmail");
		driver.findElement(By.id("password")).sendKeys("dummyPass");
		driver.findElement(By.id("login-submit")).click();

		Thread.sleep(3000);
		
		WebElement loggedInUsername = driver.findElement(By.cssSelector("span[class='sc-79pcpx-0 ieekjL']"));

		assertEquals(driver.getCurrentUrl(), "https://ananas.rs/");
		assertEquals(loggedInUsername.getText(), "Dummy");
	}

}
