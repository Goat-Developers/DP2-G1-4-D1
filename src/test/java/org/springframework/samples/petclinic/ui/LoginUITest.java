package org.springframework.samples.petclinic.ui;


import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.test.annotation.DirtiesContext;

@DirtiesContext
public class LoginUITest {
	private String username;
	private WebDriver driver;
	private String baseUrl;
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();

	@BeforeEach
	public void setUp() throws Exception {

		String pathToGeckoDriver="./target/classes/static/resources/";
		System.setProperty("webdriver.gecko.driver", pathToGeckoDriver + "geckodriver.exe");

		driver = new FirefoxDriver();
		baseUrl = "https://www.google.com/";
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void testLoginAsVet1() throws Exception {

		as("vet1")
		.whenIamLoggedIntheSystem()
		.thenICanSeeMyUsernameInsideTheMenuBar();
	}
	
	private void thenICanSeeMyUsernameInsideTheMenuBar() {
		assertEquals(username, driver.findElement(By.xpath("//div[@id='main-navbar']/ul[2]/li/a/strong")).getText());

	}
	
	private LoginUITest whenIamLoggedIntheSystem() {	
		return this;
	}
	
	private LoginUITest as(String username) {
		this.username = username;

		driver.get("http://localhost:8080");
	    driver.findElement(By.xpath("//div[@id='main-navbar']/ul[2]/li/a")).click();
	    driver.findElement(By.id("password")).clear();
	    driver.findElement(By.id("password")).sendKeys(passwordOfVet(username));
	    driver.findElement(By.id("username")).clear();
	    driver.findElement(By.id("username")).sendKeys(username);
	    driver.findElement(By.xpath("//button[@type='submit']")).click();

		return this;
	}
	
	private CharSequence passwordOfVet(String username) {
		return "v3t";
	}

	@AfterEach
	public void tearDown() throws Exception {
		driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}

	private boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	private boolean isAlertPresent() {
		try {
			driver.switchTo().alert();
			return true;
		} catch (NoAlertPresentException e) {
			return false;
		}
	}	

	private String closeAlertAndGetItsText() {
		try {
			Alert alert = driver.switchTo().alert();
			String alertText = alert.getText();
			if (acceptNextAlert) {
				alert.accept();
			} else {
				alert.dismiss();
			}
			return alertText;
		} finally {
			acceptNextAlert = true;
		}
	}
}
