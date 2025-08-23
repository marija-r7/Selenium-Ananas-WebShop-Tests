package tswprojekatmarija;

import static org.junit.Assert.*;

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

public class RegisterUserTest {

	private static WebDriver driver;
	private static URL registerPageUrl;
	private static FluentWait<WebDriver>wait;
	private WebElement newsletterCheckElement;
	private WebElement privacyPolicyCheckElement;
	private WebElement submitButtonElement;
	
	@Before
	public void setUp()
	{
		driver = new ChromeDriver();
		try {
			registerPageUrl = new URL("https://ananas.rs/registruj-se");
		} catch (MalformedURLException e) {
			System.out.println("Could not create URL for register webpage.");
		}
		wait = new FluentWait<WebDriver>(driver)
	                .withTimeout(Duration.ofSeconds(30))
	                .pollingEvery(Duration.ofSeconds(5))
	                .ignoring(NoSuchElementException.class);
		driver.get(registerPageUrl.toString());
		driver.manage().window().maximize();
	}
	
	@After
	public void cleanUp() {
		driver.quit();
	}
	
	@Test
	public void CheckboxAndButtonsEnabledTest() {
		wait.until(driver->driver.findElement(By.cssSelector("button[class='sc-1rhklln-0 jDfIno']"))).click();
		newsletterCheckElement = wait.until(driver -> driver.findElement(By.name("signUpForNewsletter")));
		newsletterCheckElement.click();
		privacyPolicyCheckElement = driver.findElement(By.name("privacyPolicy"));
		privacyPolicyCheckElement.click();
		submitButtonElement = driver.findElement(By.xpath("//button[text()='Registruj se']"));
		
		assertTrue(submitButtonElement.isDisplayed());
		assertFalse(newsletterCheckElement.isSelected());
		assertTrue(privacyPolicyCheckElement.isSelected());
	}
	
	@Test
	public void RegisteringUserTest() {
	    wait.until(driver -> driver.findElement(By.id("email"))).sendKeys("dummyEmail");
		driver.findElement(By.id("firstName")).sendKeys("Dummy");
		driver.findElement(By.id("lastName")).sendKeys("Dummy");
		driver.findElement(By.name("password")).sendKeys("DummyPass");
		
		newsletterCheckElement = driver.findElement(By.name("signUpForNewsletter"));		
		newsletterCheckElement.click();
		privacyPolicyCheckElement = driver.findElement(By.name("privacyPolicy"));
		privacyPolicyCheckElement.click();
		submitButtonElement = driver.findElement(By.xpath("//button[text()='Registruj se']"));
		
		submitButtonElement.click();
		
		assertEquals(driver.getCurrentUrl(), "https://ananas.rs/registruj-se/uspeh");
	}

}
